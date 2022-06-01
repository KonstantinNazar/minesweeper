package sappergame;

public class GameLogic
{
    private Mines minefield;
    private PlayingField playingfield;
    private GameStatus statusOfGame;

    public GameLogic()
    {
        minefield = new Mines();
        playingfield = new PlayingField();
    }

    public void startNewGame()
    {
        minefield.createNewFieldWithMines();
        playingfield.createNewPlayingField();
        statusOfGame = GameStatus.PLAYING;
    }

    public CellValue getCellValue(Cells cell)
    {
        if (playingfield.getCellValue(cell) == CellValue.OPENED)
            return minefield.getCellValue(cell);
        else
            return playingfield.getCellValue(cell);
    }

    public GameStatus getStatusOfGame()
    {
        return statusOfGame;
    }

    public void pressLeftButton(Cells cell)
    {
        if (gameOver())
            return;
        openCell(cell);
        victoryCheck();
    }

    private void openCell(Cells cell)
    {
        switch (playingfield.getCellValue(cell))
        {
            case OPENED:
            {
                openClosedCellsAroundCellWithNumber(cell);
                return;
            }
            case FLAGED:
                return;
            case CLOSED:
            {
                switch (minefield.getCellValue(cell))
                {
                    case ZERO:
                    {
                        openAllCellsAround(cell);
                        return;
                    }
                    case BOMB:
                    {
                        openMinesOnMinefield(cell);
                        return;
                    }
                    default:
                    {
                        playingfield.setOpenedValueToCell(cell);
                        return;
                    }
                }
            }

        }
    }

    private void openClosedCellsAroundCellWithNumber(Cells cell)
    {
        if (playingfield.getCellValue(cell) != CellValue.BOMB)
            if (playingfield.getCountOfFlagedCellsAround(cell) == minefield.getCellValue(cell).getNumberOfCellVelue())
                for (Cells adjacentСell : Ranges.getCellsAroundСurrentСell(cell))
                    if (playingfield.getCellValue(adjacentСell) == CellValue.CLOSED)
                        openCell(adjacentСell);
    }

    private void openAllCellsAround(Cells cell)
    {
        playingfield.setOpenedValueToCell(cell);
        for (Cells adjacentСell : Ranges.getCellsAroundСurrentСell(cell))
            openCell(cell);
    }

    private void openMinesOnMinefield(Cells currentCell)
    {
        statusOfGame = GameStatus.LOSING;
        playingfield.setBombedValueToCell(currentCell);
        for (Cells cell : Ranges.getAllCellsOnTheField())
        {
            if (minefield.getCellValue(cell) == CellValue.BOMB)
                playingfield.openClosedCellWithBomb(cell);
            else
                playingfield.setNobombToFlagedCell(cell);
        }
    }

    private void victoryCheck()
    {
        if (statusOfGame == GameStatus.PLAYING)
            if (playingfield.getNumberOfClosedCells() == minefield.getTotalNumberOfMinesOnField())
                statusOfGame = GameStatus.VICTORY;
    }

    private boolean gameOver()
    {
        if (statusOfGame == GameStatus.PLAYING)
            return false;
        startNewGame();
        return true;
    }

    public void pressRightButton(Cells cell)
    {
        if (gameOver())
            return;
        playingfield.setFlagedValueOrClosedValue(cell);
    }
}
