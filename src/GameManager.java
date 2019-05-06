import gfx.Image;
import gfx.ImageTile;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {

    private ImageTile image;
    private SoundClip clip;
    private float tile = 0;
    private int x = 0,y = 0;

    public GameManager() {
        image = new ImageTile("/nazi.png", 16, 16);
        clip = new SoundClip("/glitch.wav");
        clip.setVolume(-20);
    }

    @Override
    public void update(GameContainer gc, float dt) {
        clip.loop();
        tile += dt * 20;
        if (tile > 4) {
            tile = 0;
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImageTile(image, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 16,(int) tile, 0);
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}
