package blackjack.domain.card;

import blackjack.domain.player.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int ACE_GAP = 10;
    public static final int MAX_SCORE = 21;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int oneAceScore = calculateOneAceScore();
        if (hasAce() && oneAceScore + ACE_GAP <= MAX_SCORE) {
            return oneAceScore + ACE_GAP;
        }
        return oneAceScore;
    }

    private int calculateOneAceScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().
                anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return isMaxScore() && cards.size() == Players.INIT_CARD_SIZE;
    }

    public boolean isMaxScore() {
        return calculateScore() == Cards.MAX_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > Cards.MAX_SCORE;
    }

    public List<Card> getCardValues() {
        Objects.requireNonNull(cards, "Cards의 내부 값이 null을 참조하고 있습니다.");
        return List.copyOf(cards);
    }
}
