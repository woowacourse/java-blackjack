package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {
	private final List<Card> cards = new ArrayList<>();

	public int addCard(Card card) {
		cards.add(card);
		return calculateScore();
	}

	private int calculateScore() {
		int aceCnt = countAceCard();
		int notAceScore = calculateNotAceCardScore();
		List<Integer> possible = getPossibleAceScores(aceCnt);
		return possible.stream()
			.filter(each -> each + notAceScore <= 21)
			.max(Integer::compare).orElse(-1);
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

	public Card getRandomCard() {
		int lastIndex = cards.size();
		return cards.get(new Random().nextInt(lastIndex));
	}

	public List<Card> getCards() {
		return this.cards;
	}
}
