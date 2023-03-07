package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDistributor {

    private final Cards cards;

    public CardDistributor(Cards cards) {
        this.cards = cards;
        cards.shuffle();
    }

    public Card distribute() {
        Card card = cards.getCard();
        cards.removeCard();
        return card;
    }

    public List<Card> distributeInitialCard() {
        List<Card> initialCard = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            initialCard.add(distribute());
        }

        return initialCard;
    }

    public int getCardsSize() {
        return cards.getSize();
    }

}
