package sappergame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MinesweeperGame extends JFrame
{
    private JPanel playingField;
    private JLabel statusOfGame;
    private GameLogic game;
    private final int IMAGE_SIZE_PIXELS = 25;
    private final int BOMBS = 10;
    private final int SIZE_X = 9;
    private final int SIZE_Y = 9;

    public MinesweeperGame()
    {
        initRanges();
        game = new GameLogic();
        game.startNewGame();
        setImage();
        initLabel();
        initPlayingField();
        initFrame();
    }

    private void initPlayingField()
    {
        playingField = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Cells cell : Ranges.getAllCellsOnTheField())
                {
                    g.drawImage((Image)game.getCellValue(cell).imageForValue,
                            cell.x * Ranges.getСellSizeInPixels(),
                            cell.y * Ranges.getСellSizeInPixels(), this);
                }
            }
        };
        playingField.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX() / Ranges.getСellSizeInPixels();
                int y = e.getY() / Ranges.getСellSizeInPixels();
                Cells cell = new Cells(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(cell);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(cell);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.startNewGame();
                statusOfGame.setText(getStatusOfGame());
                playingField.repaint();
            }
        });
        playingField.setPreferredSize(new Dimension(Ranges.getСellSizeInPixels() * Ranges.getFieldSize().x,
                                                Ranges.getСellSizeInPixels() * Ranges.getFieldSize().y));
        add(playingField);
    }

    private void initFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initRanges()
    {
        Ranges.setFieldSize(new Cells(SIZE_X, SIZE_Y));
        Ranges.setСellSizeInPixels(IMAGE_SIZE_PIXELS);
        Ranges.setTotalNumberOfMinesOnField(BOMBS);
    }

    private void setImage()
    {
        for (CellValue value : CellValue.values())
            value.imageForValue = getImageForCell(value.name().toLowerCase());
    }

    private Image getImageForCell(String imageName)
    {
        String filename = "/minsweeper_img/" + imageName + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private void initLabel()
    {
        statusOfGame = new JLabel("----- Welcome -----");
        statusOfGame.setHorizontalAlignment(SwingConstants.CENTER);
        statusOfGame.setFont(new Font("Lucida Bright", Font.PLAIN, 12));
        add(statusOfGame, BorderLayout.SOUTH);
    }

    private String getStatusOfGame()
    {
        switch (game.getStatusOfGame())
        {
            case PLAYING: return "----- Your turn -----";
            case LOSING: return "----- Game over -----";
            case VICTORY: return "----- You win!!! -----";
            default: return "----- Welcome -----";
        }
    }

}
