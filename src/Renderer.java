import gfx.Image;
import gfx.ImageTile;

import java.awt.image.DataBufferInt;

public class Renderer {
    private int pW, pH;
    private Font font = Font.STANDARD;
    Window window;

    public Renderer(Window window) {
        pW = window.getpW();
        pH = window.getpH();
        this.window = window;
    }

    public void  clear() {
        for (int x = 0; x < pW; x++) {
            for (int y = 0; y < pH; y++) {
                window.setPixel(x, y, 0xffffffff);
            }
        }
    }

    public void drawText(String text, int offX, int offY, int color) {
        text = text.toUpperCase();
        int offset = 0;
        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for (int y = 0; y < font.getFontImage().getH(); y++) {
                for (int x = 0; x < font.getWidths()[unicode]; x++) {
                    if (font.getFontImage().getP()[x + font.getOffsets()[unicode] + y * font.getFontImage().getW()] == 0xffffffff) {
                        window.setPixel(x + offX + offset, y+ offY, color);
                    }
                }
            }
            offset += font.getWidths()[unicode];
        }
    }

    public void drawImage(Image image, int offX, int offY) {

        if (offX < -image.getW()) return;
        if (offY < -image.getH()) return;
        if (offX >= pW) return;
        if (offY >= pH) return;

        int newX = 0;
        int newY = 0;
        int newWidth = image.getW();
        int newHeight = image.getH();

        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pW) newWidth = pW -offX;
        if (newHeight + offY >= pH) newHeight = pH -offY;


        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                window.setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
            }
        }
    }

    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
        if (offX < -image.getTileW()) return;
        if (offY < -image.getTileH()) return;
        if (offX >= pW) return;
        if (offY >= pH) return;

        int newX = 0;
        int newY = 0;
        int newWidth = image.getTileW();
        int newHeight = image.getTileH();

        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pW) newWidth = pW -offX;
        if (newHeight + offY >= pH) newHeight = pH -offY;


        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                window.setPixel(x + offX, y + offY, image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);
            }
        }
    }

    public void drawRect(int offX, int offY, int width, int height, int color) {
        for (int y = 0; y <= width; y++) {
            window.setPixel(offX, y + offY, color);
            window.setPixel(offX + width, y + offY, color);
        }

        for (int x = 0; x <= width; x++) {
            window.setPixel(x + offX, offY, color);
            window.setPixel(x + offX, offY + height, color);
        }
    }

    public void fillRect(int offX, int offY, int width, int height, int color) {
        if (offX < -width) return;
        if (offY < -height) return;
        if (offX >= pW) return;
        if (offY >= pH) return;

        int newX = 0;
        int newY = 0;
        int newWidth = width;
        int newHeight = height;

        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pW) newWidth = pW -offX;
        if (newHeight + offY >= pH) newHeight = pH -offY;

        for (int y = newY; y <= newHeight; y++) {
            for (int x = newX; x <= newWidth; x++) {
                window.setPixel(x + offX, y + offY, color);
            }
        }
    }
}
