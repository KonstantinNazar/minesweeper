package sappergame;

public enum CellValue
{
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object imageForValue;

    CellValue getNextNumberCellValue()
    {
        return CellValue.values()[this.ordinal() + 1];
    }

    int getNumberOfCellVelue()
    {
        return this.ordinal();
    }
}
