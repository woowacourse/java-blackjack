package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gamer {
	private final List<Card> cards = new ArrayList<>();
	protected Score score = Score.from(0);

	public void addTwoCards(Deck deck) {
		addCard(deck.distributeCard());
		addCard(deck.distributeCard());
	}

	public void addCard(Card card) {
		cards.add(card);
		score = this.score.addBy(card.getScore());

		if (hasAce()) {
			calculateAceSum();
		}

		if (this.score.isBiggerThan(21)) {
			score = this.score.setToMinusOne();
		}
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public int getScore() {
		return this.score.getScore();
	}

	public boolean isBurst() {
		return this.score.isSmallerThan(0);
	}

	public void calculateAceSum() {
		final int theNumberOfAce = countAceCard();
		final int scoreWithoutAce = calculateNotAceCardScore();
		List<Integer> possible = getPossibleAceScores(theNumberOfAce);
		final int optimalScore = generateOptimalScore(scoreWithoutAce, possible);
		this.score = Score.from(optimalScore);
	}

	private int generateOptimalScore(final int scoreWithoutAce, final List<Integer> possible) {
		int optimalScore = scoreWithoutAce;
		List<Integer> collect = possible.stream()
			.filter(each -> each + scoreWithoutAce <= 21)
			.collect(Collectors.toList());
		for (int possibleScore : collect) {
			optimalScore = Math.max(optimalScore, possibleScore + scoreWithoutAce);
		}
		return optimalScore;
	}

	private List<Integer> getPossibleAceScores(int aceCnt) {
		List<Integer> possible = new ArrayList<>();
		for (int i = 0; i <= aceCnt; i++) {
			possible.add(i + 11 * (aceCnt - i));
		}
		return possible;
	}

	private int countAceCard() {
		return (int)cards.stream()
			.filter(Card::isAce)
			.count();
	}

	private int calculateNotAceCardScore() {
		return cards.stream()
			.filter(card -> !card.isAce())
			.mapToInt(Card::getScore)
			.sum();
	}

	public boolean hasAce() {
		return this.cards.stream().anyMatch(Card::isAce);
	}
}
