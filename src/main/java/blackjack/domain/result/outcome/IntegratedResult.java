package blackjack.domain.result.outcome;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.ResultRule;
import blackjack.domain.result.ResultType;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

public class IntegratedResult<T extends Player, E, U> {
    private final T player;
    private final ResultType resultType;
    private final ResultResolver<T, E, U> resultResolver;

    public IntegratedResult(T player, Dealer dealer, ResultResolver<T, E, U> resultResolver) {
        Objects.requireNonNull(player, NULL_ERR_MSG);
        Objects.requireNonNull(dealer, NULL_ERR_MSG);
        Objects.requireNonNull(resultResolver, NULL_ERR_MSG);
        this.player = player;
        this.resultType = ResultRule.findResult(player.getCards(), dealer.getCards());
        this.resultResolver = resultResolver;
    }

    public E showPlayerResult() {
        return resultResolver.showPlayerResult(player, resultType);
    }

    public boolean hasSameResult(ResultType type) {
        return this.resultType == type;
    }

    public Name getName() {
        return player.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegratedResult<?, ?, ?> that = (IntegratedResult<?, ?, ?>) o;
        return Objects.equals(player, that.player) &&
                resultType == that.resultType &&
                Objects.equals(resultResolver, that.resultResolver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, resultType, resultResolver);
    }
}
