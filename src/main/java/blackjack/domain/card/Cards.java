package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Cards {

    private static final int INIT_DISTRIBUTE_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateInitCards(cards);
        this.cards = new ArrayList<>();
        addCards(cards);
    }

    public static List<Card> createInitCards(final Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_DISTRIBUTE_SIZE; i++) {
            cards.add(deck.draw());
        }
        return cards;
    }

    private void validateInitCards(final List<Card> cards) {
        if (cards == null || cards.size() != INIT_DISTRIBUTE_SIZE) {
            throw new IllegalArgumentException("[ERROR] 잘못 배분된 카드입니다.");
        }
    }

    private void addCards(final List<Card> cards) {
        for (Card card : cards) {
            addCard(card);
        }
    }

    public void addCard(final Card card) {
        checkCardNull(card);
        cards.add(card);
    }

    private void checkCardNull(final Card card) {
        if (card == null) {
            throw new IllegalArgumentException("[ERROR] 올바른 카드를 입력해주세요.");
        }
    }

    public int calculateMaxScore() {
        if (containsAce()) {
            return calculateScore() + Score.getDifferenceAcesScore();
        }
        return calculateScore();
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(Card::isScoreAce);
    }

    public int calculateScore() {
        return cards.stream().mapToInt(card -> card.getScoreAmount()).sum();
    }

    public int calculateInitCardScore() {
        if (containsInitAce()) {
            return calculateInitMaxScore();
        }
        return calculateInitScore();
    }

    private boolean containsInitAce() {
        return getInitCards().stream().anyMatch(Card::isScoreAce);
    }

    private int calculateInitMaxScore() {
        return calculateInitScore() + Score.getDifferenceAcesScore();
    }

    private int calculateInitScore() {
        return getInitCards().stream().mapToInt(card -> card.getScoreAmount()).sum();
    }

    private List<Card> getInitCards() {
        return cards.subList(0, INIT_DISTRIBUTE_SIZE);
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
