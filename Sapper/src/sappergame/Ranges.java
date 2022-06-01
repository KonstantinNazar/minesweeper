package sappergame;

import java.util.ArrayList;
import java.util.Random;

public class Ranges
{
    private static Cells fieldSize;
    private static int totalNumberOfMinesOnField;
    private static int сellSizeInPixels;
    private static ArrayList<Cells> allCellsOnTheField;
    private static final double maximumPercentageOfMapMining = 0.25;

    public static Cells getFieldSize()
    {
        return fieldSize;
    }

    public static void setFieldSize(Cells fieldSizeValue)
    {
        fieldSize = fieldSizeValue;
        allCellsOnTheField = new ArrayList<Cells>();
        for (int y = 0; y < fieldSize.y; y++)
            for (int x = 0; x < fieldSize.x; x++)
                allCellsOnTheField.add(new Cells(x, y));
    }

    public static ArrayList<Cells> getAllCellsOnTheField()
    {
        return allCellsOnTheField;
    }

    public static int getTotalNumberOfMinesOnField() {
        return totalNumberOfMinesOnField;
    }

    public static void setTotalNumberOfMinesOnField(int totalNumberOfMinesOnField)
    {
        Ranges.totalNumberOfMinesOnField = totalNumberOfMinesOnField;
        if (bombsIsMoreThenMaximumPercentageOfMapMining())
            Ranges.totalNumberOfMinesOnField = (int) (allCellsOnTheField.size() * maximumPercentageOfMapMining);
    }

    public static int getСellSizeInPixels()
    {
        return сellSizeInPixels;
    }

    public static void setСellSizeInPixels(int сellSizeInPixels)
    {
        Ranges.сellSizeInPixels = сellSizeInPixels;
    }

    private static boolean bombsIsMoreThenMaximumPercentageOfMapMining()
    {
        return allCellsOnTheField.size() * maximumPercentageOfMapMining < totalNumberOfMinesOnField;
    }

    public static boolean onField(Cells cell)
    {
        return  cell.x >= 0 && cell.x < fieldSize.x &&
                cell.y >= 0 && cell.y < fieldSize.y;
    }

    public static Cells chooseRandomCellToPlantMine()
    {
        Random random = new Random();
        return new Cells(random.nextInt(fieldSize.x), random.nextInt(fieldSize.y));
    }

    public static ArrayList<Cells> getCellsAroundСurrentСell(Cells currentCell)
    {
        Cells adjacentСell = null;
        ArrayList<Cells> CellsAroundCurrenCell = new ArrayList<Cells>();
        for (int x = currentCell.x - 1; x <= currentCell.x + 1; x++)
            for (int y = currentCell.y - 1; y <= currentCell.y + 1; y++)
            {
                if (Ranges.onField(adjacentСell = new Cells(x,y)))
                    if (isNotCurrentCell(adjacentСell, currentCell))
                        CellsAroundCurrenCell.add(adjacentСell);
            }
        return CellsAroundCurrenCell;
    }

    private static boolean isNotCurrentCell(Cells adjacentСell, Cells currentCell)
    {
        return !(adjacentСell.equals(currentCell));
    }

}
