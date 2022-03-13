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
}
