package blackjack.domain.calculator;

import blackjack.domain.card.CardBundle;

interface ScoreStrategy {

    boolean support(CardBundle cards);

    int calculate(CardBundle cards);
}
