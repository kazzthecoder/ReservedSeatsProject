import java.util.Scanner;

public class Theater {

    final int rows;
    final int columns;
    Seat[][] reservedSeats;

    Scanner in = new Scanner(System.in);

    public Theater(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        reservedSeats = new Seat[rows][columns];
    }
    void reserveSeat(Seat seat) throws BookedException {
        if (null == reservedSeats[seat.row][seat.column]) {
            reservedSeats[seat.row][seat.column] = seat;
        } else {
            throw new BookedException();
        }
    }

    void displaySeats() {
        StringBuilder rowsHeader = new StringBuilder();
        rowsHeader.append("====SEATS====");
        for (int i = 1; i <= rows; i++) {
            rowsHeader.append(" ").append(i);
        }
        rowsHeader.append("\n   ").append("--".repeat(rows)).append("\n");
        for (int i = 0; i < rows; i++) {
            rowsHeader.append(i + 1).append(" | ");
            for (int j = 0; j < columns; j++) {
                if (null == reservedSeats[i][j]) {
                    rowsHeader.append("o ");
                } else {
                    rowsHeader.append("x ");
                }
            }
            rowsHeader.append("\n");
        }
        rowsHeader.append("\n");
        // less flush, better performance
        System.out.println(rowsHeader);
    }

    void reservationQuestions() {
        System.out.println("Which seat do you want to reserve?\nRow:");
        try {
            int row = in.nextInt();
            System.out.println("Column:");
            int column = in.nextInt();
            System.out.println("Under what name is the reservation?");
            String name = in.next();
            Seat newSeat = new Seat(row, column, name);
            reserveSeat(newSeat);
            System.out.println("seat [" + row + "," + column + "] for: " + name + " reserved");
        }	catch (BookedException e) {
            System.out.println("Sorry, Seat Already Booked");
        } catch (Exception e) {
            System.out.println("\n[ERROR] not a valid number");
        }
    }

    void reservationDialog() {
        System.out.println("Do you wish to confirm and exit? (y/n)");
        String answer = in.next();
        if (answer.equalsIgnoreCase("y") || answer.equals("")) {
            System.out.println("Display of current reservations");
            displaySeats();
            System.out.println("\nEnjoy The Show!!!");
        } else if (answer.equalsIgnoreCase("n")) {
            reservationQuestions();
            reservationDialog();
        } else {
            System.out.println("Please Select Valid Option");
            reservationDialog();
        }
    }
}
