package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public interface State {
    State takeCard(Card card);

    boolean isBlackjack();

    boolean isBurst();

    Score calculateScore();

    Cards getCards();

    int size();

}
