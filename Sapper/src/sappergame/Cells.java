package sappergame;

public class Cells
{
    public int x;
    public int y;

    public Cells(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals (Object cell)
    {
        if (cell instanceof Cells)
        {
            return (this.x == ((Cells)cell).x) && (this.y == ((Cells)cell).y);
        }
        return false;
    }

}
