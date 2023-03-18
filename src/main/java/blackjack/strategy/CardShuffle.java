package blackjack.strategy;

import blackjack.domain.card.Card;

import java.util.List;

public interface CardShuffle {

    List<Card> shuffle(List<Card> cards);
}
