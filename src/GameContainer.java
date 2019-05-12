public class GameContainer implements Runnable {

    private final double UPDATE_CAP = 1.0 / 60.0;
    private boolean running = false;
    private Thread thread = new Thread(this);

    private int width, height;
    private float scale = 3;

    private Window window;
    private Renderer renderer;
    private Input input;
    private World world;
    private KeyController c1, c2;

    public GameContainer(World world) {
        this.world = world;
        width = World.TS * (world.getTileW());
        height = World.TS * (world.getTileH());
    }

    public void start() {
        window = new Window(width, height, scale);
        renderer = new Renderer(window, world);
        input = new Input(this);
        c1 = new KeyController(window, world.getKidnapper());
        c2 = new KeyController(window, world.getFather());
        thread.run();
    }

    public void run() {
        running = true;

        boolean render;

        double firstTime;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running) {
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP) {

                unprocessedTime -= UPDATE_CAP;
                render = true;

                c1.update((float) UPDATE_CAP);
                c2.update((float) UPDATE_CAP);

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if (render) {
                renderer.clear();
                //world.render(this, renderer);
                renderer.render();
                renderer.drawText("FPS: " + fps, 0, 0, 0xff00ffff);
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public Window getWindow() {
        return window;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public static void main(String[] args) {
        World world = new World(20, 15);
        world.createLevel();
        GameContainer gc = new GameContainer(world);
        gc.start();
    }
}