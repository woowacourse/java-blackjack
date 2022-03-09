package domain.participant;

public class Player extends Participant {

    public static final int MAX_CARD_SUM = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateSum() < MAX_CARD_SUM;
    }
}
