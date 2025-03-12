package blackjack.domain.card;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_CARD_NUMBER = 2;
    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public Score calculateScore() {
        Score totalScore = new Score(cards.stream()
                .mapToInt(Card::getScore)
                .sum());
        if (hasAce()) {
            return totalScore.withAce();
        }
        return totalScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return calculateScore().isBlackjackScore() && cards.size() == BLACKJACK_CARD_NUMBER;
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void take(Card card1, Card card2) {
        this.cards.addAll(Arrays.asList(card1, card2));
    }

    public void additionalTake(Card card) {

        if (calculateScore().isBust()) {
            throw new IllegalArgumentException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.add(card);
    }

    public boolean doesNeedDealerPickAdditionalCard() {
        return calculateScore().doesNeedDealerPickAdditionalCard();
    }

    public boolean canTake() {
        return calculateScore().canTake();
    }

    public int getSize() {
        return cards.size();
    }

    public void add(Card card) {
        cards.add(card);
    }
}
