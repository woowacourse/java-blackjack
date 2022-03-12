package blackjack.model.player;

import static blackjack.model.player.Name.dealerName;

import blackjack.model.blackjack.Result;
import blackjack.model.card.Card;
import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;
import blackjack.model.cards.ScoreCards;

public final class Dealer extends Player {

    private static final Score HIT_BOUNDARY = new Score(17);
    private static final int OPEN_CARD_COUNT = 1;

    private final ScoreCards cards;

    public Dealer(Card card1, Card card2, Card... cards) {
        this(Cards.of(card1, card2, cards));
    }

    private Dealer(Cards ownCards) {
        super(dealerName(), ownCards);
        this.cards = Cards.maxScoreCards(ownCards);
    }

    public Result match(Player gamer) {
        if (gamer.isBust()) {
            return Result.WIN;
        } else if (isBust()) {
            return Result.LOSS;
        }
        return compare(score(), gamer.score());
    }

    private Result compare(Score dealerScore, Score playerScore) {
        if (dealerScore.lessThan(playerScore)) {
            return Result.LOSS;
        } else if (dealerScore.moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    @Override
    public Cards openCards() {
        return cards().openCard(OPEN_CARD_COUNT);
    }

    @Override
    public boolean isHittable() {
        return cards.lessThan(HIT_BOUNDARY);
    }
}
