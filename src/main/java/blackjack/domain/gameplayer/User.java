package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;

import java.util.List;

public interface User {

    void addCard(Card card);

    List<Card> showCards();

    boolean canContinue();

    Score calculateScore();
}
