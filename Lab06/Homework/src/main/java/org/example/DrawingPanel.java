package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.awt.image.BufferedImage;


/**
 * The DrawingPanel class extends JPanel and is used to draw the game board and the game pieces.
 * It contains the game logic for placing the game pieces and checking for a win condition.
 * Implements Serializable to allow the object to be saved to a file.
 */
public class DrawingPanel extends JPanel implements Serializable {
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    boolean[][] hSticks;
    boolean[][] vSticks;
    boolean playerTurn = true;
    int lastRedRow = -1;
    int lastRedCol = -1;
    int lastBlueRow = -1;
    int lastBlueCol = -1;
    int[][] stonesPlaced;
    Random rand = new Random();

    /**
     * Constructor for the DrawingPanel class.
     * It initializes the DrawingPanel with the number of rows and columns.
     * It also adds a MouseListener to the panel to place the game pieces.
     * @param frame The MainFrame object that contains the DrawingPanel.
     */
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init(frame.configPanel.getRows(), frame.configPanel.getCols());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                placeStone(e.getX(), e.getY());
            }
        });
    }

    /**
     * The init() method initializes the game board with the given number of rows and columns.
     * It also initializes the game pieces and the sticks between the cells.
     * It sets the preferred size of the panel and calls repaint().
     * @param rows The number of rows of the game board.
     * @param cols The number of columns of the game board.
     */
    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        this.hSticks = new boolean[rows][cols - 1];
        this.vSticks = new boolean[rows - 1][cols];
        this.stonesPlaced = new int[rows][cols];
        this.playerTurn=true;


        for (int i = 0; i < hSticks.length; i++) {
            for (int j = 0; j < hSticks[i].length; j++) {
                hSticks[i][j] = rand.nextBoolean();
            }
        }

        for (int i = 0; i < vSticks.length; i++) {
            for (int j = 0; j < vSticks[i].length; j++) {
                vSticks[i][j] = rand.nextBoolean();
            }
        }

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        repaint();
    }

    /**
     * The reattachListeners() method adds a new MouseListener to the panel.
     */
    public void reattachListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                placeStone(e.getX(), e.getY());
            }
        });
    }

    /**
     * The paintComponent() method is used to draw the game board, the game pieces and the sticks between the cells.
     * It uses the Graphics object to draw the components.
     * @param graphics the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        paintSticks(g);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (stonesPlaced[row][col] > 0) {
                    int x = padX + col * cellWidth - stoneSize / 2;
                    int y = padY + row * cellHeight - stoneSize / 2;
                    g.setColor(stonesPlaced[row][col] == 1 ? Color.RED : Color.BLUE);
                    g.fillOval(x, y, stoneSize, stoneSize);
                }
            }
        }
    }

    /**
     * The checkForWin() method checks if a player has won the game.
     * It checks if the other player has any valid moves left.
     */
    private void checkForWin() {
        playerTurn = !playerTurn;
        boolean hasMoves = false;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isValidMove(r, c)) {
                    hasMoves = true;
                    break;
                }
            }
            if (hasMoves) break;
        }
        playerTurn = !playerTurn;

        if (!hasMoves) {
            repaint();
            JOptionPane.showMessageDialog(this, "Player " + (playerTurn ? "Red" : "Blue") + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * The placeStone() method places a game piece on the game board at the given coordinates.
     * @param mouseX The x-coordinate of the mouse click.
     * @param mouseY The y-coordinate of the mouse click.
     */
    private void placeStone(int mouseX, int mouseY) {
        int col = (mouseX - padX + cellWidth / 2) / cellWidth;
        int row = (mouseY - padY + cellHeight / 2) / cellHeight;

        if (isValidMove(row, col)) {
            int currentPlayer = playerTurn ? 1 : 2;
            stonesPlaced[row][col] = currentPlayer;

            if (currentPlayer == 1) {
                lastRedRow = row;
                lastRedCol = col;
            } else {
                lastBlueRow = row;
                lastBlueCol = col;
            }


            checkForWin();

            playerTurn = !playerTurn;
            repaint();
        }
    }

    /**
     * The isValidMove() method checks if a move is valid.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return True if the move is valid, false otherwise.
     */
    private boolean isValidMove(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || stonesPlaced[row][col] != 0) {
            return false;
        }

        int currentPlayer = playerTurn ? 1 : 2;

        if (!hasStickAtPosition(row, col)) {
            return false;
        }

        if (isFirstMoveForColor(currentPlayer)) {
            return true;
        }

        int lastRow = currentPlayer == 1 ? lastRedRow : lastBlueRow;
        int lastCol = currentPlayer == 1 ? lastRedCol : lastBlueCol;

        boolean isAdjacent = Math.abs(lastRow - row) + Math.abs(lastCol - col) == 1;

        boolean stickBetween = isStickBetween(row, col, lastRow, lastCol);

        return isAdjacent && stickBetween;
    }


    /**
     * The isFirstMoveForColor() method checks if the current player has made a move yet.
     * @param color The color of the current player.
     * @return True if the player has not made a move yet, false otherwise.
     */
    private boolean isFirstMoveForColor(int color) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (stonesPlaced[r][c] == color) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The hasStickAtPosition() method checks if there is a stick at the given position.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return True if there is a stick at the given position, false otherwise.
     */
    private boolean hasStickAtPosition(int row, int col) {
        if (col > 0 && hSticks[row][col - 1]) {
            return true;
        }
        if (col < cols - 1 && hSticks[row][col]) {
            return true;
        }
        if (row > 0 && vSticks[row - 1][col]) {
            return true;
        }
        if (row < rows - 1 && vSticks[row][col]) {
            return true;
        }

        return false;
    }

    /**
     * The isStickBetween() method checks if there is a stick between two cells.
     * @param currentRow The row of the current cell.
     * @param currentCol The column of the current cell.
     * @param lastRow The row of the last cell.
     * @param lastCol The column of the last cell.
     * @return True if there is a stick between the two cells, false otherwise.
     */
    private boolean isStickBetween(int currentRow, int currentCol, int lastRow, int lastCol) {
        if (currentRow == lastRow) {
            if (currentCol == lastCol + 1) {
                return hSticks[currentRow][lastCol];
            } else if (currentCol == lastCol - 1) {
                return hSticks[currentRow][currentCol];
            }
        }
        if (currentCol == lastCol) {
            if (currentRow == lastRow + 1) {
                return vSticks[lastRow][currentCol];
            } else if (currentRow == lastRow - 1) {
                return vSticks[currentRow][currentCol];
            }
        }
        return false;
    }

    /**
     * The paintSticks() method is used to draw the sticks between the cells.
     * It uses the Graphics2D object to draw the sticks.
     * @param g The Graphics2D object
     */
    private void paintSticks(Graphics2D g)
    {
        g.setStroke(new BasicStroke(4));

        for (int i = 0; i < hSticks.length; i++) {
            for (int j = 0; j < hSticks[i].length; j++) {
                if (hSticks[i][j]) {
                    int x1 = padX + j * cellWidth;
                    int y = padY + i * cellHeight;
                    int x2 = x1 + cellWidth;
                    g.setColor(Color.BLACK);
                    g.drawLine(x1, y, x2, y);
                }
            }
        }
        for (int i = 0; i < vSticks.length; i++) {
            for (int j = 0; j < vSticks[i].length; j++) {
                if (vSticks[i][j]) {
                    int x = padX + j * cellWidth;
                    int y1 = padY + i * cellHeight;
                    int y2 = y1 + cellHeight;
                    g.setColor(Color.BLACK);
                    g.drawLine(x, y1, x, y2);
                }
            }
        }
        g.setStroke(new BasicStroke(1));

    }

    /**
     * The paintGrid() method is used to draw the game board grid.
     * It uses the Graphics2D object to draw the grid.
     * @param g The Graphics2D object
     */
    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);

        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }

        for(int col = 0;col<cols;col++)
        {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int x2 = x1;
            int y2 = padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    /**
     * The exportBoardToPNG() method exports the game board to a PNG image.
     * It uses the ImageIO class to write the image to a file.
     * It displays a message dialog if the export was successful or not.
     */
    public void exportBoardToPNG() {
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        this.paint(graphics2D);
        graphics2D.dispose();

        try {
            ImageIO.write(image, "PNG", new File("game_board.png"));
            JOptionPane.showMessageDialog(this, "Exported board image to game_board.png successfully.", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to export board image.", "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}