package blackjack.domain.result;

import blackjack.domain.card.CardBundle;

import java.util.function.Function;

interface MoneyRate extends Function<CardBundle, Double> {

}
