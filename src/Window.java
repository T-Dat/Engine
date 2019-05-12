import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Window {

    private JFrame frame;
    private BufferedImage image;
    private int[] p;
    private int pW, pH;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;

    public Window(GameContainer gc) {
        pW = gc.getWidth();
        pH = gc.getHeight();
        image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
        p = ((DataBufferInt) getImage().getRaster().getDataBuffer()).getData();
        canvas = new Canvas();
        Dimension s = new Dimension((int)(gc.getWidth() * gc.getScale()), (int)(gc.getHeight() * gc.getScale()));
        canvas.setPreferredSize(s);
        canvas.setMaximumSize(s);
        canvas.setMinimumSize(s);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    public void update() {
        g.drawImage(image,0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bs.show();
    }

    public void setPixel(int x, int y, int value) {
        if (x < 0 || y < 0 || x >= pW || y >= pH || ((value >> 24) & 0xff) == 0) {
            return;
        }
        p[x + y * pW] = value;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getpW() {
        return pW;
    }

    public int getpH() {
        return pH;
    }
}
