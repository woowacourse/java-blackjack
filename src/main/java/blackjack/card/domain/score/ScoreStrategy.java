package blackjack.card.domain.score;

import blackjack.card.domain.Card;

import java.util.List;

public interface ScoreStrategy {

    boolean support(List<Card> cards);

    int calculate(List<Card> cards);
}
