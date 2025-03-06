package domain;

import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(CardsGenerator cardsGenerator) {
        cards = cardsGenerator.generate();
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 부족합니다.");
        }
        Card card = cards.getLast();
        cards.removeLast();
        return card;
    }

    public void giveCardTo(Participant participant) {
        participant.addCard(pick());
    }

    public int countDealerDraw(Dealer dealer) {
        int count = 0;
        while (dealer.hasToDraw()) {
            giveCardTo(dealer);
            count++;
        }
        return count;
    }
}
