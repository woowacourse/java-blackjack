package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gamer {
	private final List<Card> cards = new ArrayList<>();
	protected int score = 0;

	public void addCards(Deck deck, int times) {
		for (int i = 0; i < times; i++) {
			addCard(deck.distributeCard());
		}
	}

	public void addCard(Card card) {
		cards.add(card);
		this.score += card.getNumber();
		if (hasAce()) {
			calculateAceSum();
		}
		if (this.score > 21) {
			this.score = -1;
		}
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public int getScore() {
		return score;
	}

	public boolean isBurst() {
		return this.score < 0;
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
		this.score = res;
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
			.mapToInt(Card::getNumber)
			.sum();
	}

	public boolean hasAce() {
		return this.cards.stream().anyMatch(Card::isAce);
	}
}
