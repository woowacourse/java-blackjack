package blackjack.strategy;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardHandState;
import java.util.function.Function;

public interface CardHandStateStrategy extends Function<CardBundle, CardHandState> {
}
