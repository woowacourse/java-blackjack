package blackjack.domain.calculator;

import blackjack.domain.card.Card;

import java.util.List;

interface ScoreStrategy {

    boolean support(List<Card> cards);

    int calculate(List<Card> cards);
}
