import java.util.ArrayList;

public class GameManager2 extends AbstractGame {

    private ArrayList<GameObject> objects = new ArrayList<>();
    private boolean[] collisions = new boolean[20*15];
    private int mapW = 20, mapH = 15;
    public static final int TS = 16;

    public GameManager2() {
        objects.add(new Player(3,2));
    }

    @Override
    public void update(GameContainer gc, float dt) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update(gc,this, dt);
            if (objects.get(i).isDead()) {
                objects.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        for (int y = 0; y < mapH; y++) {
            for (int x = 0; x < mapW; x++) {
                if (collisions[x + y*mapW] == true) {
                    r.fillRect(x * TS, y * TS, TS, TS, 0xff0f0f0f);
                } else {
                    r.fillRect(x * TS, y * TS, TS, TS, 0xfff9f9f9);
                }
            }

        }
        for (GameObject obj: objects) {
            obj.render(gc, r);
        }
    }

    public boolean getCollision(int x, int y) {
        if (x < 0 || x >= mapW || y < 0 || y >= mapH) {
            return true;
        }
        return collisions[x + y * mapW];
    }

    public void createLevel() {
        for (int i = 0; i < collisions.length; i++) {
            collisions[i] = false;
        }
        collisions[5] = true;
        collisions[8] = true;
        collisions[20] = true;
        collisions[100] = true;
        collisions[160] = true;
        collisions[250] = true;
        for (int i = 0; i < mapW; i++) {
            collisions[i] = true;
            collisions[280 + i] = true;
        }
        for (int i = 0; i < mapH; i++) {
            collisions[i*mapW + 19] = true;
            collisions[i*mapW] = true;
        }

    }

    public static void main(String[] args) {
        GameManager2 gm = new GameManager2();
        gm.createLevel();
        GameContainer gc = new GameContainer(gm);
        gc.start();
    }
}
