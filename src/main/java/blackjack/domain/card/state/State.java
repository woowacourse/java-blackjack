package blackjack.domain.card.state;

import blackjack.domain.card.Card;

import java.util.List;

public interface State {
    State draw(Card card);

    State stand();

    int calculate();

    List<Card> getCards();

    BlackjackStatus getStatus();
}
