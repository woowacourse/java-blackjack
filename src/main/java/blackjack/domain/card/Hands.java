package blackjack.domain.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {

    private static final int ACE_SCORE_GAP = CardNumber.ACE.getNumber() - CardNumber.ACE_BONUS_NUMBER;

    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public Hands(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
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

    public boolean isBlackjackAndSizeIs(final int size) {
        return isBlackjack() && this.size() == size;
    }

    public boolean isBlackjackAndSizeIsNot(final int size) {
        return isBlackjack() && this.size() != size;
    }

    public boolean isScoreBiggerThan(final Hands other) {
        return this.calculateScore().isBiggerThan(other.calculateScore());
    }

    public boolean isScoreSame(final Hands other) {
        return this.calculateScore().equals(other.calculateScore());
    }

    public boolean isNotSize(final int size) {
        return this.size() != size;
    }

    public int size() {
        return cards.size();
    }
    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public boolean isBlackjack() {
        return getStatus().isBlackjack();
    }

    public boolean isNotDead() {
        return !isDead();
    }

    public boolean isDead() {
        return getStatus().isDead();
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

    @Override
    public String toString() {
        return cards.toString();
    }
}
