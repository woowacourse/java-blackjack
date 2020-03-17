package blackjack.card.domain.gameresult.rate;

import blackjack.card.domain.CardBundle;

import java.util.function.Function;

public interface MoneyRate extends Function<CardBundle, Double> {

}
