import domain.card.Card;
import domain.card.DeckMaker;
import domain.card.Hand;
import domain.card.OneDeckMaker;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.List;

public class TestDefaults {
    private static final DeckMaker ONE_DECK_MAKER = new OneDeckMaker();
    private static final List<Card> DEFAULT_CARDS = createCards();
    private static final Hand DEFAULT_BLACKJACK_HAND = createBlackjackHand();

    public static Hand createBlackjackHand() {
        return new Hand(List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.KING, Suit.SPADE)));
    }

    public static List<Card> createCards() {
        return ONE_DECK_MAKER.make();
    }

    public static Hand getBlackjackHand() {
        return DEFAULT_BLACKJACK_HAND;
    }

    public static List<Card> getCardsByRanks(List<Rank> ranks) {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : ranks) {
            addCardByRank(rank, cards);
        }
        return cards;
    }

    private static void addCardByRank(Rank rank, List<Card> cards) {
        for (Card card : DEFAULT_CARDS) {
            if (card.rank().equals(rank)) {
                cards.add(card);
                return;
            }
        }
    }

    public static Card getCard(int idx) {
        return DEFAULT_CARDS.get(idx);
    }
}
