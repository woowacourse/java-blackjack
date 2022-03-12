package blackjack.model.player;

import static java.util.stream.Collectors.toMap;

import blackjack.model.blackjack.Records;
import blackjack.model.blackjack.Result;
import blackjack.model.card.Card;
import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;
import blackjack.model.cards.ScoreCards;
import java.util.List;
import java.util.Map;

public final class Dealer extends Player {

    private static final Name NAME = new Name("딜러");
    private static final Score HIT_BOUNDARY = new Score(17);
    private static final int OPEN_CARD_COUNT = 1;

    private final ScoreCards cards;

    public Dealer(Card card1, Card card2, Card... cards) {
        this(Cards.of(card1, card2, cards));
    }

    private Dealer(Cards ownCards) {
        super(NAME, ownCards);
        this.cards = Cards.maxScoreCards(ownCards);
    }

    public Records matchAll(Players players) {
        return new Records(collectResults(players));
    }

    private Map<Name, Result> collectResults(Players players) {
        return players.stream()
            .collect(toMap(Player::name, this::eachPlayerResult));
    }

    private Result eachPlayerResult(Player player) {
        if (player.isBust()) {
            return Result.LOSS;
        } else if (isBust()) {
            return Result.WIN;
        }
        return compare(player.score(), score());
    }

    private Result compare(Score playerScore, Score dealerScore) {
        if (playerScore.lessThan(dealerScore)) {
            return Result.LOSS;
        } else if (playerScore.moreThan(dealerScore)) {
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
