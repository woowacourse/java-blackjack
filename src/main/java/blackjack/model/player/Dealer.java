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

public final class Dealer {

    private static final Name NAME = new Name("딜러");
    private static final Score HIT_BOUNDARY = new Score(17);
    private static final int OPEN_CARD_COUNT = 1;

    private final ScoreCards cards;
    private final Player player;


    public Dealer(Card card1, Card card2, Card... cards) {
        this(Cards.of(card1, card2, cards));
    }

    private Dealer(Cards handCards) {
        this.cards = Cards.maxScoreCards(handCards);
        this.player = new Player(NAME, cards, OPEN_CARD_COUNT);
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
        if(lessScoreThan(player)) {
            return WIN;
        } else if(moreScoreThan(player)) {
            return LOSS;
        }
        return DRAW;
    }

    private boolean lessScoreThan(Player other) {
        return score().lessThan(other.score());
    }

    private boolean moreScoreThan(Player other) {
        return score().moreThan(other.score());
    }

    public final Score score() {
        return player.score();
    }

    public final Cards cards() {
        return player.cards();
    }

    public final void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }
        player.take(card);
    }

    public final boolean isBust() {
        return player.isBust();
    }

    public final Name name() {
        return player.name();
    }

    public Cards openCards() {
        return player.openCards();
    }

    public boolean isHittable() {
        return cards.lessThan(HIT_BOUNDARY);
    }
}
