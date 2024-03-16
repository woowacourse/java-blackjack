package blackjack.model.cards;

import blackjack.model.blackjackgame.GameOutcomeStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cards {
    private static final int SIZE_WHEN_BLACKJACK = 2;

    private final List<Card> cards;

    public Cards() {
        this(List.of());
    }

    private Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public void add(final List<Card> cardsToAdd) {
        cardsToAdd.forEach(this::add);
    }

    public boolean canAddMore() {
        return !isBusted() && !isBlackJack();
    }

    public Score getCardsScore() {
        Score score = calculateScore();
        if (hasAce()) {
            return score.getScoreWhenHasAce();
        }
        return score;
    }

    public GameOutcomeStatus compareCardsScore(final Cards other) {
        if (isBlackJack() && other.isBlackJack()) {
            return GameOutcomeStatus.PUSH;
        }
        if (isBlackJack()) {
            return GameOutcomeStatus.BLACKJACK;
        }
        return getCardsScore().getPlayerStatus(other.getCardsScore());
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isBusted() {
        return getCardsScore().isBusted();
    }

    private boolean isBlackJack() {
        return cards.size() == SIZE_WHEN_BLACKJACK && getCardsScore().isBlackJack();
    }

    private Score calculateScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.getDefaults(), Score::sum);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
