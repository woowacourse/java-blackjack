package blackjack.model.player;

import static blackjack.model.blackjack.Result.DRAW;
import static blackjack.model.blackjack.Result.LOSS;
import static blackjack.model.blackjack.Result.WIN;
import static java.util.stream.Collectors.toUnmodifiableMap;

import blackjack.model.blackjack.Records;
import blackjack.model.blackjack.Result;
import blackjack.model.card.Card;
import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;
import blackjack.model.cards.ScoreCards;
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
            .collect(toUnmodifiableMap(Player::name, this::eachPlayerResult));
    }

    private Result eachPlayerResult(Player player) {
        if (player.isBust()) {
            return LOSS;
        } else if (isBust()) {
            return WIN;
        }
        return compareWith(player);
    }

    private Result compareWith(Player player) {
        if(player.lessScoreThan(this)) {
            return LOSS;
        } else if(player.moreScoreThan(this)) {
            return WIN;
        }
        return DRAW;
    }

    @Override
    public Cards openCards() {
        return cards().openedCards(OPEN_CARD_COUNT);
    }

    @Override
    public boolean isHittable() {
        return cards.lessThan(HIT_BOUNDARY);
    }
}
