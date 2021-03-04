package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.game.WinOrLose;

public class Dealer implements Player {

    private static final int NUMBER_OF_INITIAL_CARDS = 1;
    private static final int LIMIT_SCORE_TO_HIT = 16;

    private final Name name;
    private final Cards cards;

    public Dealer() {
        name = new Name("딜러");
        cards = new Cards();
    }

    public boolean ableToDraw() {
        return cards.getScore().isBelow(LIMIT_SCORE_TO_HIT);
    }

    public WinOrLose calculateGamblerWinOrNot(final Player player) {
        if (player.getScore().isBust()) {
            return WinOrLose.LOSE;
        }
        if (cards.getScore().isBust()) {
            return WinOrLose.WIN;
        }
        if (cards.getScore().isBiggerThan(player.getScore())) {
            return WinOrLose.LOSE;
        }
        if (cards.getScore().isLessThan(player.getScore())) {
            return WinOrLose.WIN;
        }
        return WinOrLose.DRAW;
    }

    @Override
    public void initializeCards(final Deck deck) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            cards.add(deck.draw());
        }
    }

    @Override
    public void drawCard(final Deck deck) {
        cards.add(deck.draw());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Score getScore() {
        return cards.getScore();
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
