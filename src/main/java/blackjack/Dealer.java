package blackjack;

import java.util.List;

public class Dealer {
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
        return cardHolder.getAllCards().getFirst();
    }
}
