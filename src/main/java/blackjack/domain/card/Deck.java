package blackjack.domain.card;

import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck(Shuffler shuffler) {
        this.deck = shuffler.createDeck();
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 남은 카드가 없습니다.");
        }
        return deck.remove(0);
    }
}
