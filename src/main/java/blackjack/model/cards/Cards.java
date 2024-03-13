package blackjack.model.cards;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cards {
    private static final int SIZE_WHEN_BLACKJACK = 2;

    private final List<Card> cards;
    private Score score;

    public Cards() {
        this(List.of(), new Score(0));
    }

    private Cards(final List<Card> cards, final Score score) {
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public void add(final Card card) {
        cards.add(card);
        score = score.sum(card.getScore());
    }

    public void add(final List<Card> cardsToAdd) {
        cardsToAdd.forEach(this::add);
    }

    public boolean canAddMore() {
        return !isBusted() && !isBlackJack();
    }

    public Score getCardsScore() {
        if (hasAce()) {
            return score.getScoreWhenHasAce();
        }
        return score;
    }

    public PlayerProfitCalculator getPlayerResultStatus(final Cards other) {
        if (isBlackJack() && other.isBlackJack()) {
            return PlayerProfitCalculator.PUSH;
        }
        if (isBlackJack()) {
            return PlayerProfitCalculator.BLACKJACK;
        }
        return getCardsScore().getPlayerStatus(other.getCardsScore());
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isBusted() {
        return score.isBusted();
    }

    private boolean isBlackJack() {
        return cards.size() == SIZE_WHEN_BLACKJACK && getCardsScore().isBlackJack();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
