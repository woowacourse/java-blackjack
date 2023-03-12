package domain;

import java.util.List;

public class Dealer {

    public static final int STANDARD_TO_GET_ONE_MORE_CARD = 16;

    private final Name name;
    private final Cards cards;

    public Dealer(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean isWhetherToGetMoreCard() {
        return cards.calculateSum() <= STANDARD_TO_GET_ONE_MORE_CARD;
    }

    public void pickCard(CardDeck cardDeck) {
        Card card = cardDeck.pickCard();
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    //    public static final int STANDARD_FOR_HIT = 16;
//
//    public Dealer(Cards cards) {
//        this("딜러", cards);
//    }
//
//    private Dealer(String name, Cards cards) {
//        super(name, cards);
//    }
//
//    @Override
//    public boolean canAddCard() {
//        return cards.getScore() <= STANDARD_FOR_HIT;
//    }
}
