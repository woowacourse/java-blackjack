package blackjack.domain.gamer;

public class Dealer extends Gamer{

    private static final String NAME = "딜러";
    private static final int BLACKJACK_CARD_COUNT = 2;

    public Dealer() {
        super(NAME);
    }

    public int getCardSize() {
        return getCards().size();
    }

    public int findHitCount() {
        return getCards().size() - BLACKJACK_CARD_COUNT;
    }
}
