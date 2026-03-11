package domain.card;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    public static final int BUSTED_CONDITION = 21;
    public static final int ACE_BONUS_SCORE = 10;

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

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean checkExist(Card targetCard) {
        return cards.stream().
                anyMatch(c -> c.equals(targetCard));
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
