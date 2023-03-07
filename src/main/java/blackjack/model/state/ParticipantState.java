package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.card.HandCard;

public interface ParticipantState {

    ParticipantState draw(CardDeck cardDeck, HandCard handCard);

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();
}
