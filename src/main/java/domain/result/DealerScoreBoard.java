package domain.result;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.User;

public class DealerScoreBoard implements ScoreBoard {
	private final Dealer dealer;
	private final int score;

	public DealerScoreBoard(Dealer dealer, int score) {
		this.dealer = Objects.requireNonNull(dealer);
		this.score = score;
	}

	public static DealerScoreBoard of(Dealer dealer) {
		return new DealerScoreBoard(dealer, dealer.calculateScore());
	}

	public String getName() {
		return dealer.getName();
	}

	public List<Card> getCards() {
		return dealer.getCards();
	}

	public int getScore() {
		return score;
	}

	public User getUser() {
		return dealer;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		DealerScoreBoard that = (DealerScoreBoard)object;
		return this.score == that.score &&
			Objects.equals(this.dealer, that.dealer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dealer, score);
	}
}
