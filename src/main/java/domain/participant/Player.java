package domain.participant;

public class Player extends BlackjackParticipant {

    public Player(String name) {
        super(name);
        validatePlayerName();
    }

    @Override
    public boolean isDrawable() {
        int sum = calculateCardSum();
        return !isBUST(sum);
    }
}
