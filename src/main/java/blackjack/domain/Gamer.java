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
		int aceCnt = countAceCard();
		int notAceScore = calculateNotAceCardScore();
		List<Integer> possible = getPossibleAceScores(aceCnt);
		int res = notAceScore;
		List<Integer> collect = possible.stream()
			.filter(each -> each + notAceScore <= 21)
			.collect(Collectors.toList());
		for (int possibleScore : collect) {
			res = Math.max(res, possibleScore + notAceScore);
		}
		this.score = Score.from(res);
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
