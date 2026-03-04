import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDefaults {
    private static final List<Card> DEFAULT_CARDS = createCards();

    public static Deck createDeck() {
        return new Deck(createCards());
    }

    public static List<Card> createCards() {
        List<Card> deck = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }

        return deck;
    }

    public static List<Card> getCardsByRanks(List<Rank> ranks) {
        List<Card> cards = new ArrayList<>();

        int rankIdx = 0;

        for (Card card : DEFAULT_CARDS) {
            if (rankIdx == ranks.size()) {
                break;
            }
            if (card.rank().equals(ranks.get(rankIdx))) {
                cards.add(card);
                rankIdx++;
            }
        }
        return cards;
    }

    public static Card getCardByRank(Rank rank) {
        for (Card card : DEFAULT_CARDS) {
            if (card.rank().equals(rank)) {
                return card;
            }
        }
        throw new IllegalArgumentException("기본 카드에 rank:%s 카드가 없습니다.".formatted(rank));
    }

    public static Card getCard(int idx) {
        return DEFAULT_CARDS.get(idx);
    }

    public static List<Card> getCards(int cardCount) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < cardCount; i++) {
            cards.add(getCard(i));
        }
        return Collections.unmodifiableList(cards);
    }
}
