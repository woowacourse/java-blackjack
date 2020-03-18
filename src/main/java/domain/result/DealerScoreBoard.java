package domain.result;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.User;

public class DealerScoreBoard implements ScoreBoard {
	private final Dealer dealer;
	private final int score;

	private DealerScoreBoard(Dealer dealer, int score) {
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
}
