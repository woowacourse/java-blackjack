package blackjack.domain;

import java.util.List;

public class Dealer {

    private static final int FIRST_CARD_POSITION = 0;

    private final CardHolder cardHolder;

    public Dealer(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    public List<Integer> getPossibleSums() {
        return cardHolder.getPossibleSums();
    }

    public void takeCard(Card newCard) {
        cardHolder.takeCard(newCard);
    }

    public List<Card> getAllCards() {
        return cardHolder.getAllCards();
    }

    public Card revealFirstCard() {
        return cardHolder.getCard(FIRST_CARD_POSITION);
    }

    public CardHolder getCardHolder() {
        return cardHolder;
    }
}
