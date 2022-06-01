package sappergame;

public class PlayingField
{
    private Field playingField;
    private int numberOfClosedCells;

    void createNewPlayingField()
    {
        playingField = new Field(CellValue.CLOSED);
        numberOfClosedCells = Ranges.getFieldSize().x * Ranges.getFieldSize().y;
    }

    CellValue getCellValue(Cells cell)
    {
        return playingField.getCellValue(cell);
    }

    public int getNumberOfClosedCells()
    {
        return numberOfClosedCells;
    }

    public int getCountOfFlagedCellsAround(Cells cell)
    {
        int flags = 0;
        for (Cells currentCell : Ranges.getCellsAroundСurrentСell(cell))
        {
            if (playingField.getCellValue(currentCell) == CellValue.FLAGED)
                flags++;
        }
        return flags;
    }

    public void openClosedCellWithBomb(Cells cell)
    {
        if (playingField.getCellValue(cell) == CellValue.CLOSED)
            playingField.setValueForCell(cell, CellValue.OPENED);
    }

    public void setNobombToFlagedCell(Cells cell)
    {
        if (playingField.getCellValue(cell) == CellValue.FLAGED)
            playingField.setValueForCell(cell, CellValue.NOBOMB);
    }

    public void setBombedValueToCell(Cells cell)
    {
        playingField.setValueForCell(cell, CellValue.BOMBED);
    }

    public void setOpenedValueToCell(Cells cell)
    {
        playingField.setValueForCell(cell, CellValue.OPENED);
        numberOfClosedCells--;
    }

    public void setFlagedValueOrClosedValue(Cells cell)
    {
        if (playingField.getCellValue(cell) == CellValue.FLAGED)
            setClosedValueToCell(cell);
        else if (playingField.getCellValue(cell) == CellValue.CLOSED)
            setFlagedValueToCell(cell);
    }

    public void setClosedValueToCell(Cells cell)
    {
        playingField.setValueForCell(cell, CellValue.CLOSED);
    }

    public void setFlagedValueToCell(Cells cell)
    {
        playingField.setValueForCell(cell, CellValue.FLAGED);
    }
}
