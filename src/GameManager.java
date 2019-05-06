import java.util.ArrayList;

public class GameManager extends AbstractGame {
    private ArrayList<GameObject> objects = new ArrayList<>(2);
    private boolean[] collisions;


    public GameManager(int tileW, int tileH) {
        this.tileW = tileW;
        this.tileH = tileH;
        collisions = new boolean[tileW *tileH];
        objects.add(new Player(3,2, true, true));
        objects.add(new Player(8,6, false, false));
    }

    @Override
    public void update(GameContainer gc, float dt) {
        for (GameObject object : objects) {
            object.update(gc, this, dt);
        }
    }


    @Override
    public void render(GameContainer gc, Renderer r) {
        for (int y = 0; y < tileH; y++) {
            for (int x = 0; x < tileW; x++) {
                if (collisions[x + y * tileW]) {
                    r.fillRect(x * TS, y * TS, TS, TS, 0xff0f0f0f);
                } else {
                    r.fillRect(x * TS, y * TS, TS, TS, 0xfff9f9f9);
                }
            }

        }
        for (GameObject obj: objects) {
            obj.render(gc, r);
        }
        if (getPlayerCollision()) {
            gc.getRenderer().drawText("Finished!!!", gc.getWidth()/2, gc.getHeight()/2, 0xff0000ff);
        }
    }

    public boolean getCollision(int x, int y) {
        if (x < 0 || x >= tileW || y < 0 || y >= tileH) {
            return true;
        }
        return collisions[x + y * tileW];
    }

    public boolean getPlayerCollision() {
        return Math.abs(objects.get(0).posX - objects.get(1).posX) < 10 && Math.abs(objects.get(0).posY - objects.get(1).posY) < 10;
    }

    private void createLevel() {
        for (int i = 0; i < collisions.length; i++) {
            collisions[i] = false;
        }
        collisions[5] = true;
        collisions[8] = true;
        collisions[20] = true;
        collisions[100] = true;
        collisions[160] = true;
        collisions[250] = true;
        for (int i = 0; i < tileW; i++) {
            collisions[i] = true;
            collisions[280 + i] = true;
        }
        for (int i = 0; i < tileH; i++) {
            collisions[i* tileW + 19] = true;
            collisions[i* tileW] = true;
        }

    }

    public static void main(String[] args) {
        GameManager gm = new GameManager(20, 15);
        gm.createLevel();
        GameContainer gc = new GameContainer(gm);
        gc.start();
    }
}
