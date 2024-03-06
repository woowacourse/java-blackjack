package blackjack;


public class GamePlayer extends Player implements CardReceivable {
    private static final Integer RECEIVE_SIZE = 21;

    public GamePlayer(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() < RECEIVE_SIZE;
    }
}
