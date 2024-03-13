package domain.gamer;

import domain.card.Card;
import domain.card.CardDeck;

public class Dealer extends Gamer {

    private static final int MIN_DEALER_SCORE = 16;
    private final CardDeck cardDeck;

    public Dealer() {
        this.cardDeck = new CardDeck();
        initDealerCard();
    }

    private void initDealerCard() {
        cards.addCard(dealCard());
    }

    public Card dealCard() {
        return cardDeck.pickCard();
    }

    public void receiveCard() {
        cards.addCard(dealCard());
    }

    public boolean shouldDraw() {
        return cards.getScoreByAceToOne() <= MIN_DEALER_SCORE;
    }
}
