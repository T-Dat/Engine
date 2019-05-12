public class Renderer {
    private Window window;
    private World world;
    private int pW, pH;
    private Font font = Font.STANDARD;

    public Renderer(Window window, World world) {
        pW = window.getPW();
        pH = window.getPH();
        this.window = window;
        this.world = world;
    }

    public void render() {
        renderTiles();
        renderPlayer(world.getFather());
        renderPlayer(world.getKidnapper());
        if (world.getPlayerCollision()) {
            drawText("Finished!!!", pW / 2, pH / 2, 0xff0000ff);
        }
    }

    public void renderPlayer(Player player) {
        int color = player.isKidnapper() ? 0xffff0000 : 0xff00ff00;
        drawRect((int) player.getPosX(), (int) player.getPosY(), player.getWidth() - 1, player.getHeight() - 1, color);
    }

    public void renderTiles() {
        for (int y = 0; y < world.getTileH(); y++) {
            for (int x = 0; x < world.getTileW(); x++) {
                if (world.getCollision(x,y)) {
                    drawRect(x * World.TS, y * World.TS, World.TS, World.TS, 0xff0f0f0f);
                } else {
                    drawRect(x * World.TS, y * World.TS, World.TS, World.TS, 0xfff9f9f9);
                }
            }

        }
    }

    public void clear() {
        for (int x = 0; x < pW; x++) {
            for (int y = 0; y < pH; y++) {
                window.setPixel(x, y, 0xffffffff);
            }
        }
    }

    public void drawText(String text, int posX, int posY, int color) {
        text = text.toUpperCase();
        int offset = 0;
        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for (int y = 0; y < font.getFontImage().getH(); y++) {
                for (int x = 0; x < font.getWidths()[unicode]; x++) {
                    if (font.getFontImage().getP()[x + font.getOffsets()[unicode] + y * font.getFontImage().getW()] == 0xffffffff) {
                        window.setPixel(x + posX + offset, y + posY, color);
                    }
                }
            }
            offset += font.getWidths()[unicode];
        }
    }

    public void drawRect(int posX, int posY, int width, int height, int color) {
        if (posX < -width) return;
        if (posY < -height) return;
        if (posX >= pW) return;
        if (posY >= pH) return;

        int newX = 0;
        int newY = 0;
        int newWidth = width;
        int newHeight = height;

        if (posX < 0) newX -= posX;
        if (posY < 0) newY -= posY;
        if (newWidth + posX >= pW) newWidth = pW - posX;
        if (newHeight + posY >= pH) newHeight = pH - posY;

        for (int y = newY; y <= newHeight; y++) {
            for (int x = newX; x <= newWidth; x++) {
                window.setPixel(x + posX, y + posY, color);
            }
        }
    }
}
