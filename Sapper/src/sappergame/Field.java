package sappergame;

public class Field
{
    private CellValue [][]field;

    Field (CellValue value)
    {
        field = new CellValue[Ranges.getFieldSize().x][Ranges.getFieldSize().y];
        for (Cells cell : Ranges.getAllCellsOnTheField())
            field[cell.x][cell.y] = value;
    }

    public CellValue getCellValue(Cells cell)
    {
        if (Ranges.onField(cell))
            return field[cell.x][cell.y];
        return null;
    }

    public void setValueForCell(Cells cell, CellValue value)
    {
        if (Ranges.onField(cell))
            field[cell.x][cell.y] = value;
    }
}
