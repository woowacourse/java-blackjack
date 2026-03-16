package domain.card;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BLACK_JACK_SCORE_CONDITION = 21;
    private static final int BLACK_JACK_CARD_COUNT_CONDITION = 2;
    private static final int BUSTED_CONDITION = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand from(List<Card> cards) {
        return new Hand(cards);
    }

    public static Hand empty() {
        return new Hand(new ArrayList<>());
    }

    public Hand addUp(Hand additionalHand) {
        cards.addAll(additionalHand.cards);
        return this;
    }

    public int getResultScore() {
        int basicScore = getBasicScore();
        boolean hasAce = hasAce();

        if (hasAce && (basicScore + ACE_BONUS_SCORE <= BUSTED_CONDITION)) {
            return basicScore + ACE_BONUS_SCORE;
        }

        return basicScore;
    }

    public int getBasicScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBusted() {
        return getBasicScore() > BUSTED_CONDITION;
    }

    public boolean isBlackjack() {
        return satisfyBlackjackCardCount() && satisfyBlackjackScore();
    }

    private boolean satisfyBlackjackCardCount() {
        return cards.size() == BLACK_JACK_CARD_COUNT_CONDITION;
    }

    private boolean satisfyBlackjackScore() {
        return getResultScore() == BLACK_JACK_SCORE_CONDITION;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<String> toDisplay() {
        return cards.stream()
                .map(Card::toDisplay)
                .toList();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Hand that = (Hand) object;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

}
