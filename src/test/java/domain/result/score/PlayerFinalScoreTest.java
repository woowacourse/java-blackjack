package domain.result.score;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

public class PlayerFinalScoreTest {
	Player player;
	Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player("둔덩");
		dealer = new Dealer();
	}

	@DisplayName("true인 경우")
	@Test
	void isBiggerTest() {
		dealer.addCards(Arrays.asList(new Card("하트", "2")));
		player.addCards(Arrays.asList(new Card("하트", "1")));

		DealerFinalScore dealerFinalScore = new DealerFinalScore(dealer);
		PlayerFinalScore playerFinalScore = new PlayerFinalScore(player);

		assertThat(playerFinalScore.isBigger(dealerFinalScore)).isTrue();
	}

	@DisplayName("false인 경우")
	@Test
	void isBiggerTest2() {
		dealer.addCards(Arrays.asList(new Card("하트", "1")));
		player.addCards(Arrays.asList(new Card("하트", "1")));

		DealerFinalScore dealerFinalScore = new DealerFinalScore(dealer);
		PlayerFinalScore playerFinalScore = new PlayerFinalScore(player);

		assertThat(playerFinalScore.isBigger(dealerFinalScore)).isFalse();
	}
}
