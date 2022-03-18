package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class Participant {

	protected final Name name;
	protected final Hand hand;
	protected final Betting betting;

	public Participant(Name name, Hand hand, Betting betting) {
		this.name = name;
		this.hand = hand;
		this.betting = betting;
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

	public int getScore() {
		return hand.getScore();
	}

	public boolean isBlackJack() {
		return hand.isBlackJack();
	}

	public String showName() {
		return name.getName();
	}

	public List<String> showHand() {
		return hand.show();
	}

	public int showBetting() {
		return betting.getBettingMoney();
	}

	public Name getName() {
		return name;
	}
}
