package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import blackjack.domain.user.exceptions.AbstractPlayerException;

import java.util.List;
import java.util.Objects;

public abstract class AbstractPlayer implements Playable {
	protected static final int MAX_SCORE = 21;
	private static final int MAX_SCORE_TO_MAXIMIZE = 12;
	private static final int ADDING_SCORE_TO_MAXIMIZE = 10;

	private final String name;
	private final Hand hand;

	protected AbstractPlayer(String name) {
		validateNameIsNotNull(name);
		validateNameIsNotBlank(name);

		this.name = name;
		this.hand = new Hand();
	}

	private void validateNameIsNotNull(String name) {
		if (name == null) {
			throw new AbstractPlayerException("이름에 Null 값이 들어올 수 없습니다.");
		}
	}

	private void validateNameIsNotBlank(String blank) {
		if (blank.trim().isEmpty()) {
			throw new AbstractPlayerException("이름은 공백으로만 이루어질 수 없습니다.");
		}
	}

	@Override
	public void giveCard(Card card) {
		hand.add(card);
	}

	@Override
	public void giveCards(List<Card> cards) {
		hand.addAll(cards);
	}

	@Override
	public boolean isBust() {
		return getScore().isOver(MAX_SCORE);
	}

	@Override
	public int countCards() {
		return hand.size();
	}

	@Override
	public Score getScore() {
		Score score = sumScore();
		return maximize(score);
	}

	private Score sumScore() {
		return hand.sumScore();
	}

	private Score maximize(Score score) {
		if (score.isUnder(MAX_SCORE_TO_MAXIMIZE) && hand.hasAce()) {
			return score.add(Score.of(ADDING_SCORE_TO_MAXIMIZE));
		}
		return score;
	}

	@Override
	public List<Card> getHand() {
		return hand.getHand();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractPlayer that = (AbstractPlayer) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(hand, that.hand);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hand);
	}

	@Override
	public String toString() {
		return "AbstractPlayer{" +
				"name='" + name + '\'' +
				", hand=" + hand +
				'}';
	}
}
