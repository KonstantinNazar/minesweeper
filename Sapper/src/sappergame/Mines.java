package sappergame;

public class Mines
{
    private Field minefield;
    private int totalNumberOfMinesOnField;

    Mines()
    {
        totalNumberOfMinesOnField = Ranges.getTotalNumberOfMinesOnField();
    }
    
    void createNewFieldWithMines()
    {
        createClearField();
        for (int mine = 0; mine < totalNumberOfMinesOnField; mine++)
            placeMineInCell();
    }

    void createClearField()
    {
        minefield = new Field(CellValue.ZERO);
    }

    private void placeMineInCell()
    {
        while (true)
        {
            Cells cellForMine = Ranges.chooseRandomCellToPlantMine();
            if (cellValueIsBomb(cellForMine))
                continue ;
            minefield.setValueForCell(cellForMine, CellValue.BOMB);
            updateNumbersAroundThisCell(cellForMine);
            break ;
        }
    }

    private void updateNumbersAroundThisCell(Cells centralCell)
    {
        for (Cells adjacentСell : Ranges.getCellsAroundСurrentСell(centralCell))
            if (minefield.getCellValue(adjacentСell) != CellValue.BOMB)
                minefield.setValueForCell(adjacentСell, minefield.getCellValue(adjacentСell).getNextNumberCellValue());
    }

    CellValue getCellValue (Cells cell)
    {
        return minefield.getCellValue(cell);
    }

    private boolean cellValueIsBomb(Cells cell)
    {
        return minefield.getCellValue(cell) == CellValue.BOMB;
    }

    public int getTotalNumberOfMinesOnField()
    {
        return totalNumberOfMinesOnField;
    }

}
