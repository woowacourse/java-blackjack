package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.blackjack.Result;
import blackjack.model.blackjack.Score;
import blackjack.model.cards.Cards;
import blackjack.model.cards.ScoreCards;

public class Dealer extends Player {

    public static final Score HIT_BOUNDARY = new Score(17);

    private final ScoreCards maxScoreCards;

    public Dealer(Card card1, Card card2, Card... cards) {
        this(card1, Cards.of(card1, card2, cards));
    }

    private Dealer(Card card1, Cards ownCards) {
        super("딜러", Cards.of(card1), ownCards);
        this.maxScoreCards = Cards.maxScoreCards(ownCards);
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
    public boolean isHittable() {
        return maxScoreCards.lessThan(HIT_BOUNDARY);
    }
}
