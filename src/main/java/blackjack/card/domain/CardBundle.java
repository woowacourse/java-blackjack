package blackjack.card.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import blackjack.BlackjackRule;

public class CardBundle {
	private static final int ACE_WEIGHT = 10;
	private static final int BLACKJACK_MAXIMUM_VALUE = 21;
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	// todo : 블랙잭인 경우 승패여부가 로직에 안들어감 ex) 딜러 블랙잭, 플레이어 3장 블랙잭인경우 무승부임!
	public GameResult calculateWinOrLose(CardBundle gamblerCardBundle) {
		return GameResult.createGameResult(this, gamblerCardBundle);
	}

	public int calculateScore() {
		int resultScore = cards.stream()
			.mapToInt(Card::getScore)
			.sum();

		if (hasAce() && isNotBurst(addAceWeight(resultScore))) {
			return addAceWeight(resultScore);
		}
		return resultScore;
	}

	private int addAceWeight(int resultScore) {
		return resultScore + ACE_WEIGHT;
	}

	public Card getFirstCard() {
		return cards.get(0);
	}

	public boolean isBlackjack() {
		return cards.size() == BlackjackRule.STARTING_CARD_COUNT
			&& calculateScore() == BLACKJACK_MAXIMUM_VALUE;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	public boolean isBurst() {
		return calculateScore() > BLACKJACK_MAXIMUM_VALUE;
	}

	private boolean isNotBurst(int score) {
		return score <= BLACKJACK_MAXIMUM_VALUE;
	}

	public boolean isNotBurst() {
		return !isBurst();
	}

	public boolean isSameScore(CardBundle other) {
		return this.calculateScore() == other.calculateScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CardBundle that = (CardBundle)o;
		return Objects.equals(cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
}
