package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.dto.CardDto;
import blackjack.dto.GamerHandDto;

public abstract class BlackjackGamer {

	private final Name name;
	private final Hand hand;

	public BlackjackGamer(Name name) {
		this.name = name;
		this.hand = new Hand(new ArrayList<>());
	}

	public abstract boolean canReceiveCard();

	public void initCard(Deck deck) {
		addCard(deck.draw());
		addCard(deck.draw());
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public Card getFirstCard() {
		return hand.getFirstCard();
	}

	public int getScore() {
		return hand.calculateScore();
	}

	public Name getName() {
		return name;
	}

	public GamerHandDto convertGamerToDto() {
		String playerName = name.value();
		List<CardDto> gamerHand = hand.convertHandToDto();

		return new GamerHandDto(playerName, gamerHand);
	}
}
