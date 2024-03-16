package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public interface CardState {
    CardState receive(Card card);

    CardState finish();

    Cards cards();
}
