package blackjack.domain.participant;

public class Turn {

    private final int turn;
    private int now;

    public Turn(int size) {
        this.turn = size;
    }

    public void next() {
        this.now++;
    }

    public boolean isEndOfTurn() {
        return now < turn;
    }

    public int getNow() {
        return now;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Turn turn1 = (Turn) o;

        if (turn != turn1.turn) {
            return false;
        }
        return now == turn1.now;
    }

    @Override
    public int hashCode() {
        int result = turn;
        result = 31 * result + now;
        return result;
    }
}
