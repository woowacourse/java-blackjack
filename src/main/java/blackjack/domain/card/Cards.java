package blackjack.domain.card;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> cards;

    public Cards(Card... cards) {
        this.cards = new ArrayList<>();
        Collections.addAll(this.cards, cards);
    }

    public BlackjackScore calculateScore() {
        BlackjackScore totalBlackjackScore = new BlackjackScore(cards.stream()
                .mapToInt(Card::getScore)
                .sum(), cards.size());

        if (hasAce()) {
            return totalBlackjackScore.withAce();
        }
        return totalBlackjackScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return calculateScore().isBlackjack();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
