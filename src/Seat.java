public class Seat {
    public final int row;
    public final int column;
    private final String name;

    public Seat(int row, int column, String name) {
        this.row = row - 1;
        this.column = column - 1;
        this.name = name;
    }
}
