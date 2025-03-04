package domain;

import java.util.List;

public class Dealer {

    private final CardDeck cardDeck;
    private final Cards ownedCards; // 변수명 추천

    private Dealer(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.ownedCards = Cards.of();
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    public Card passCard() {
        return cardDeck.popCard();
    }

    public void receive(Card card) {
        ownedCards.add(card);
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }
}
