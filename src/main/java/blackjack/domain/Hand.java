package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void addCard(final Card card) {
		cards.add(card);
	}
	// if(calculateOptimalScore < 17){
	// 	draw
	// }
	// if(hasAce()){
	// 	random
	// }

	// if(calculateOptimalScore <= 21){
	// 	draw ->? 요청
	// }

	public int calculateOptimalScore() {
		int totalScore = cards.stream()
			.mapToInt(Card::getScore)
			.sum();
		if (isBust(totalScore)) {
			return 0;
		}
		if (hasAce()) {
			return getOptimizedScore(totalScore, totalScore + 10);
		}
		return totalScore;
	}

	private boolean isBust(int totalScore) {
		return totalScore > 21;
	}

	private boolean hasAce() {
		return cards.stream()
			.mapToInt(Card::getScore)
			.anyMatch(score -> score == Denomination.ACE.getScore());
	}

	private int getOptimizedScore(final int aceAsOneScore, final int aceAsElevenScore) {
		if (isBust(aceAsElevenScore)) {
			return aceAsOneScore;
		}
		return aceAsElevenScore;
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}
}
