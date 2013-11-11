package justaconcept.games.burningtouch;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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

    private Stage stage;
    private Skin skin;
    private Table table;
    private BitmapFont buttonFont;

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
	skin.add("white", new Texture(pixmap));

	TextButtonStyle textButtonStyle = new TextButtonStyle();
	textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
	textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
	textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
	textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
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

    @Override
    public void create() {
	setUpStage();

	try {
	    LayoutManager.testResolution();
	} catch (Exception error) {
	    showError("An exception has occured: \n" + error.getMessage() + "\nThe application will terminate.");
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
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
	if (GameState.current_paper == GameState.latest_paper)
	    ((DynamicPaper) scene_objects.get(Constants.PPR_OBJ_STR)).resume();
    }

    @Override
    public void dispose() {
	stage.dispose();
	buttonFont.dispose();
	skin.dispose();
    }
}
