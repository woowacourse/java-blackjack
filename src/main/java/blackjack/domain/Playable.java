package blackjack.domain;

import java.util.List;

public abstract class Playable {
    private final String name;
    private final Cards cards;

    public Playable(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    public abstract boolean isAvailableToTake();

    public String getName(){
        return this.name;
    }


    public int sumCards() {
        return cards.sumCards();
    }

    public Cards getCards(){
        return cards;
    }

    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public int sumCardsForResult() {
        return cards.sumCardsForResult();
    }

}
