package domain.user;

import domain.deck.Deck;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    private static final int PIVOT = 17;

    private Dealer() {
        super(DEALER);
    }

    public static Dealer appoint() {
        return new Dealer();
    }

    @Override
    public String getFirstDrawResult() {
        return DEALER + "카드: " + cards.get(0).getName();
    }

    public void additionalDraw(Deck deck) {
        if (calculatePoint() < PIVOT) {
            optionalDraw(deck);
        }
    }
}
