package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.game.WinOrLose;

public class Dealer implements Player {

    private static final int MIN_SCORE_TO_STAND = 17;

    private final Name name;
    private final Cards cards;

    public Dealer() {
        name = new Name("딜러");
        cards = new Cards();
    }

    @Override
    public void initializeCards(final Deck deck) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            cards.add(deck.draw());
        }
    }

    public WinOrLose calculateGamblerWinOrNot(final Player player) {
        if (player.isBust()) {
            return WinOrLose.LOSE;
        }
        if (isBust()) {
            return WinOrLose.WIN;
        }
        if (isBiggerThan(player)) {
            return WinOrLose.LOSE;
        }
        if (isLessThan(player)) {
            return WinOrLose.WIN;
        }
        return WinOrLose.DRAW;
    }

    @Override
    public void drawCard(final Deck deck) {
        cards.add(deck.draw());
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }

    @Override
    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    @Override
    public boolean isTwentyOne() {
        return cards.isTwentyOne();
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public String name() {
        return name.getName();
    }

    @Override
    public Score score() {
        return cards.totalScore();
    }

    public boolean ableToDraw() {
        return cards.totalScore().isLessThan(Score.of(MIN_SCORE_TO_STAND));
    }

    public boolean isBiggerThan(final Player player) {
        return cards.isBiggerThan(player.cards());
    }

    public boolean isLessThan(final Player player) {
        return cards.isLessThan(player.cards());
    }
}
