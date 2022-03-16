package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public class Participant {

	protected final Name name;
	protected final boolean blackJack;
	protected final Hand hand;

	public Participant(Name name, Hand hand) {
		this.name = name;
		this.hand = hand;
		this.blackJack = isMaxScore();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public boolean isBust() {
		return hand.isBust();
	}

	public boolean isMaxScore() {
		return hand.isMaxScore();
	}

	public int getBestScore() {
		return hand.getBestScore();
	}

	public boolean isBlackJack() {
		return blackJack;
	}

	public ParticipantDTO getInfo() {
		return new ParticipantDTO(
			name.getInfo(),
			hand.getInfo()
		);
	}

	public Name getName() {
		return Name.copyOf(name);
	}
}
