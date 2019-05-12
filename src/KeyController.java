import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {

    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];
    private Player player;

    public KeyController(Window window, Player player) {
        window.getCanvas().addKeyListener(this);
        this.player = player;
    }

    public void update(float dt) {
        for (int i = 0; i < NUM_KEYS; i++) {
            keysLast[i] = keys[i];
        }
        movePlayer(dt);
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void movePlayer(float dt) {
        if (isKey(KeyEvent.VK_D)) {
            player.moveRight(dt);
        }

        if (isKey(KeyEvent.VK_A)) {
            player.moveLeft(dt);
        }

        if (isKey(KeyEvent.VK_W)) {
            player.moveUp(dt);
        }

        if (isKey(KeyEvent.VK_S)) {
            player.moveDown(dt);
        }
    }

}

