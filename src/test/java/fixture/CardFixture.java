package fixture;

import domain.deck.Card;
import domain.deck.Deck;
import domain.deck.DeckGenerator;
import domain.deck.Rank;
import domain.deck.Suit;
import java.util.ArrayList;
import java.util.List;

public class CardFixture {
    private static final Deck deck = DeckGenerator.generateRandomDeck();
    private static final List<Card> deckFixture = new ArrayList<>();

    static {
        for (int i = 0; i < 52; i++) {
            deckFixture.add(deck.drawNewCard());
        }
    }

    public static Card of(Rank rank, Suit suit) {
        return deckFixture.stream()
                .filter(card -> card.getRank().equals(rank))
                .filter(card -> card.getSuit().equals(suit))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 카드를 생성할 수 없습니다."));
    }
}
