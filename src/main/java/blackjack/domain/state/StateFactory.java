package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public class StateFactory {
	public static PlayerState drawTwoCards(Card firstCard, Card secondCard) {
		ParticipantCards participantCards = new ParticipantCards();
		participantCards.addCard(firstCard);
		participantCards.addCard(secondCard);
		if (participantCards.calculateIncludeAce() == 21) {
			return new BlackJack(participantCards);
		}
		return new Hit(participantCards);
	}
}
