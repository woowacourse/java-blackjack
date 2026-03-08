import domain.RandomValueGenerator;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;

import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDefaults {
    private static final Suit FIRST_SUIT = Suit.SPADE;
    private static final Suit SECOND_SUIT = Suit.HEART;
    private static final Rank DEFAULT_RANK = Rank.ACE;

    private static final List<Card> DEFAULT_CARDS = createCards();
    private static final Hand DEFAULT_BLACKJACK_HAND = createBlackjackHand();
    private static final RandomValueGenerator constantGenerator = (n) -> 1;

    public static Deck createDeck() {
        return new Deck(createCards(), constantGenerator);
    }

    public static Hand createBlackjackHand() {
        return new Hand(List.of(new Card(DEFAULT_RANK, FIRST_SUIT), new Card(DEFAULT_RANK, FIRST_SUIT)));
    }

    public static RandomValueGenerator getConstantGenerator() {
        return constantGenerator;
    }

    public static Hand getBlackjackHand() {
        return DEFAULT_BLACKJACK_HAND;
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

    public static List<Card> getDefaultCards() {
        return DEFAULT_CARDS;
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
