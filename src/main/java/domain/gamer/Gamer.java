package domain.gamer;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;

public abstract class Gamer {
	private static final int BUST_NUMBER = 22;
	private static final int ACE_HIDDEN_SCORE = 10;
	public static final int BLACKJACK_NUMBER = 21;
	public static final int BLACKJACK_CARD_SIZE = 2;

	private String name;
	private final List<Card> cards;
    protected Result result;

    public Gamer(String name) {
        this.name = name;
        this.result = new Result();
      		cards = new ArrayList<>();

    }
  
	public abstract boolean isDrawable();

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
    result.calculateScore(this.cards, isContainAce());
	}

	public boolean isBlackJack() {
		return calculateScore() == BLACKJACK_NUMBER && cards.size() == BLACKJACK_CARD_SIZE;
	}

	public int calculateScore() {
		int score = calculateScoreWithoutAce();

		if (isContainAce() && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
			return score + ACE_HIDDEN_SCORE;
		}

		return score;
	}

	public boolean isContainAce() {
		return cards.stream()
			.anyMatch(x -> x.getCardNumber() == CardNumber.ACE);
	}

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards;
	}
  
    public Result getResult() {
        return result;
    }
}
