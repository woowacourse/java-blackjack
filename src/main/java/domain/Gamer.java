package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gamer {
	private final List<Card> cards = new ArrayList<>();
	protected int score = 0;

	public void addCard(Card card) {
		cards.add(card);
		this.score += card.getScore();
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public boolean isBurst() {
		return this.score > 21;
	}

	public int calculateAceSum() {
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
		return res;
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

}
