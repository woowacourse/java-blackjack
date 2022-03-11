package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Gamer {
	private final List<Card> cards = new ArrayList<>();
	protected Score score = Score.from(0);

	public void addCard(Card card) {
		cards.add(card);
		this.score = this.score.addBy(card.getScore());

		if (hasAce()) {
			this.score = Score.from(calculateOptimalScoreWithAce());
		}

		if (this.score.isBiggerThan(21)) {
			this.score = this.score.setToMinusOne();
		}
	}

	public void addTwoCards(Deck deck) {
		addCard(deck.distributeCard());
		addCard(deck.distributeCard());
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

	public int calculateOptimalScoreWithAce() {
		final int theNumberOfAce = countAceCard();
		final int scoreWithoutAce = calculateNotAceCardScore();
		List<Integer> possible = getPossibleAceScores(theNumberOfAce);
		final int optimalScore = generateOptimalScore(scoreWithoutAce, possible);
		return optimalScore;
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
		List<Integer> possibleScores = new ArrayList<>();
		for (int i = 0; i <= aceCnt; i++) {
			possibleScores.add(i + 11 * (aceCnt - i));
		}
		return possibleScores;
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
