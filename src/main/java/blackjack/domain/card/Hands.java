package blackjack.domain.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.result.BlackjackStatus;
import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Hands {

    private static final int ACE_SCORE_GAP = 10;

    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        validateDuplicate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateDuplicate(final List<Card> cards) {
        if (Set.copyOf(cards).size() != cards.size()) {
            throw new IllegalStateException("중복된 카드는 존재할 수 없습니다.");
        }
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int sum = sum();
        int aceCount = countAce();

        while (isDeadScore(sum) && aceCount-- > 0) {
            sum -= ACE_SCORE_GAP;
        }

        return new Score(sum);
    }

    private int sum() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private boolean isDeadScore(final int sum) {
        return BlackjackStatus.from(new Score(sum)).isDead();
    }

    public boolean isNotBlackjack() {
        return !getStatus().isBlackjack();
    }

    public boolean isNotDead() {
        return !getStatus().isDead();
    }

    private BlackjackStatus getStatus() {
        return BlackjackStatus.from(calculateScore());
    }

    public Hands getFirstCard() {
        return cards.stream().limit(1).collect(collectingAndThen(toList(), Hands::new));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
