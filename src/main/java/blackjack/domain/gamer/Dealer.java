package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    private static final String NAME = "딜러";
    private static final int DEALER_STAND_CONDITION = 16;
    private static final int INITIAL_OPEN_CARD_INDEX = 1;

    private final Gamer gamer;

    public Dealer() {
        this.gamer = new Gamer(NAME);
    }

    public Card getInitialOpenedCard() {
        return gamer.getCards().get(INITIAL_OPEN_CARD_INDEX);
    }

    public void addCard(Card card) {
        if (gamer.getScore() > DEALER_STAND_CONDITION) {
            return;
        }

        gamer.addCard(card);
    }

    public boolean isNotBust() {
        return gamer.getScore() <= DEALER_STAND_CONDITION;
    }

    public boolean isInstantBlackJack() {
        return gamer.isInstantBlackJack();
    }

    public String getName() {
        return gamer.getName();
    }

    public int getScore() {
        return gamer.getScore();
    }

    public List<Card> getCards() {
        return gamer.getCards();
    }
}
