package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int INIT_DISTRIBUTE_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateInitCards(cards);
        this.cards = new ArrayList<>();
        addCards(cards);
    }

    public static List<Card> createInitCards(Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i< INIT_DISTRIBUTE_SIZE; i++){
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

    public int calculateScoreByAceOne() {
        return cards.stream().mapToInt(card -> card.getScore().getAmount()).sum();
    }

    public int calculateMaxScore() {
        if (containsAce()) {
            return calculateScoreByAceOne() + Score.getDifferenceAcesScore();
        }
        return calculateScoreByAceOne();
    }

    private boolean containsAce() {
        return cards.stream().anyMatch(card -> card.isScoreAce());
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
