import java.awt.event.KeyEvent;

public class GameContainer implements Runnable {

    private final double UPDATE_CAP = 1.0 / 60.0;
    private boolean running = false;
    private Thread thread = new Thread(this);

    private int width, height;
    private float scale = 3;

    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    public GameContainer(AbstractGame game) {
        this.game = game;
        width = AbstractGame.TS * (game.getTileW());
        height = AbstractGame.TS * (game.getTileH());
    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(window);
        input = new Input(this);
        thread.run();
    }

    public void stop() {
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

                input.update();

                game.update(this, (float) UPDATE_CAP);

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if (render) {
                renderer.clear();
                game.render(this, renderer);
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


        dispose();
    }

    private void dispose() {}

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

    public Input getInput() {
        return input;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}