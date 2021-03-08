package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.game.WinOrLose;

public class Dealer implements Player {
    private static final int LIMIT_SCORE_TO_HIT = 16;

    private final Name name;
    private final Cards cards;

    public Dealer() {
        name = new Name("딜러");
        cards = new Cards();
    }

    public boolean ableToDraw() {
        return cards.calculateScore().isBelow(LIMIT_SCORE_TO_HIT);
    }

    public WinOrLose calculateGamblerWinOrNot(final Player player) {
        Score gamblerScore = player.getScore();
        Score dealerScore = cards.calculateScore();

        if (dealerScore.equals(gamblerScore) || dealerScore.isBust() && gamblerScore.isBust()) {
            return WinOrLose.DRAW;
        }

        if (dealerScore.isBust() || dealerScore.isLessThan(gamblerScore)) {
            return WinOrLose.WIN;
        }

        return WinOrLose.LOSE;
    }

    @Override
    public void initializeCards(final Deck deck) {
        cards.add(deck.draw());
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Score getScore() {
        return cards.calculateScore();
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
