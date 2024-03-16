package domain.card.state;

import domain.card.Card;

public interface CardState {
    CardState receive(Card card);

    CardState finish();
}
