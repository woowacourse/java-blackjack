package domain.gamer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.CardNumber;

public abstract class Gamer {
	private String name;
	private final List<Card> cards = new ArrayList<>();
	protected Result result = new Result();

	public Gamer(String name) {
		this.name = name;
	}

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public abstract boolean isDrawable();

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards;
	}

	public boolean isContainAce(){
		return cards.stream().anyMatch(x -> x.getCardNumber() == CardNumber.ACE);
	}

	public int getScore(){
		return result.calculateWithAce(this);
	}

	public Result getGameResult() {
		return result;
	}

	@Override
	public String toString() {
		return name + " : " + cards.stream()
			.map(Card::toString)
			.collect(Collectors.joining(", "));
	}
}
