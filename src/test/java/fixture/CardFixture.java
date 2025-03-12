package fixture;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import domain.shuffler.RandomShuffler;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CardFixture {
    public static List<Card> deckFixture = new ArrayList<>();
    private static final Deck deck = new Deck(new RandomShuffler());

    static {
        for (int i = 0; i < 52; i++) {
            deckFixture.add(deck.drawCard());
        }
    }

    public static Card of(Rank rank, Suit suit) {
        return deckFixture.stream()
                .filter(card -> card.getRank() == rank && card.getSuit() == suit)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("[ERROR] 해당하는 카드를 찾을 수 없습니다."));
    }
}
