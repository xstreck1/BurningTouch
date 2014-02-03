package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BurningTouch implements ApplicationListener {
    private DrawingManager drawing_manager;
    private UpdateManager update_manager;
    private TouchManager touch_manager;
    private HashMap<String, SceneObject> scene_objects;
    private FPSLogger logger;

    private AssetManager asset_manager = new AssetManager();
    private Stage stage;
    private Skin skin;
    private Table table;
    private BitmapFont buttonFont;
    private int time_delay = 120;

    void setUpStage() {
	stage = new Stage();
	Gdx.input.setInputProcessor(stage);

	table = new Table();
	table.setFillParent(true);
	stage.addActor(table);

	skin = new Skin();
	buttonFont = new BitmapFont();
    }

    void showError(String error) {
	Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
	pixmap.setColor(Color.WHITE);
	pixmap.fill();
	skin.add(Messages.getString("BurningTouch.0"), new Texture(pixmap)); //$NON-NLS-1$

	TextButtonStyle textButtonStyle = new TextButtonStyle();
	textButtonStyle.up = skin.newDrawable(Messages.getString("BurningTouch.0"), Color.DARK_GRAY); //$NON-NLS-1$
	textButtonStyle.down = skin.newDrawable(Messages.getString("BurningTouch.0"), Color.DARK_GRAY); //$NON-NLS-1$
	textButtonStyle.checked = skin.newDrawable(Messages.getString("BurningTouch.0"), Color.BLUE); //$NON-NLS-1$
	textButtonStyle.over = skin.newDrawable(Messages.getString("BurningTouch.0"), Color.LIGHT_GRAY); //$NON-NLS-1$
	textButtonStyle.font = buttonFont;
	TextButton button1 = new TextButton(error, textButtonStyle);
	button1.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		Gdx.app.exit();
	    }
	});
	table.add(button1).size(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	pixmap.dispose();
    }
    
    void loadMusic() {
	if (GameState.succ == null)
	    GameState.succ = Gdx.audio.newSound(Gdx.files.internal(Sources.SUCC_SOUND));
	if (GameState.burn == null)
	    GameState.burn = Gdx.audio.newSound(Gdx.files.internal(Sources.BURN_SOUND));
	if (GameState.background_music == null)
	    asset_manager.load(Sources.BG_MUSIC, Music.class);
    }

    @Override
    public void create() {
	
	setUpStage();

	try {
	    LayoutManager.testResolution();
	} catch (Exception error) {
	    showError(Messages.getString("BurningTouch.1") + error.getMessage() + Messages.getString("BurningTouch.2")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	UpdateManager.loadWorkingMask();

	scene_objects = new HashMap<String, SceneObject>();
	scene_objects.put(Constants.BG_OBJ_STR, new BackgroundGraphic());
	BasicPaper current_paper;
	if (GameState.current_paper == GameState.latest_paper)
	    current_paper = new DynamicPaper(GameState.working_mask);
	else
	    current_paper = new StaticPaper(GameState.current_paper);
	scene_objects.put(Constants.PPR_OBJ_STR, current_paper);
	scene_objects.put(Constants.BTN_OBJ_STR, new Buttons());

	drawing_manager = new DrawingManager(scene_objects);
	update_manager = new UpdateManager(scene_objects);
	touch_manager = new TouchManager(scene_objects);

	logger = new FPSLogger();
	
	loadMusic();
	GameState.reset();
    }

    @Override
    public void resize(int width, int height) {
	stage.setViewport(width, height, true);
    }

    @Override
    public void render() {
	touch_manager.update();
	update_manager.update();
	drawing_manager.update();
	logger.log();
	stage.act(Gdx.graphics.getDeltaTime());
	stage.draw();
	if (GameState.background_music == null) {
	    if (asset_manager.isLoaded(Sources.BG_MUSIC)) {
                GameState.background_music = asset_manager.get(Sources.BG_MUSIC);
                GameState.background_music.play();
                GameState.background_music.setVolume(Constants.BG_VOLUME);
	    } else {
		asset_manager.update();
	    }
	}
    }

    @Override
    public void pause() {
	if (GameState.current_paper == GameState.latest_paper && GameState.current_paper != Constants.PAPER_COUNT)
	    ((DynamicPaper) scene_objects.get(Constants.PPR_OBJ_STR)).pause();
	if (GameState.background_music != null)
	    GameState.background_music.pause();
    }

    @Override
    public void resume() {
	if (GameState.current_paper == GameState.latest_paper && GameState.current_paper != Constants.PAPER_COUNT)
	    ((DynamicPaper) scene_objects.get(Constants.PPR_OBJ_STR)).resume();
	if (GameState.play_sound && GameState.background_music != null)
	    GameState.background_music.play();
    }

    @Override
    public void dispose() {
	stage.dispose();
	buttonFont.dispose();
	skin.dispose();
	asset_manager.unload(Sources.BG_MUSIC);
	asset_manager.dispose();
	GameState.burn.dispose();
	GameState.burn = null;
	GameState.succ.dispose();
	GameState.succ = null;
	for (SceneObject scene_object : scene_objects.values()) 
	    scene_object.update();
    }
}
