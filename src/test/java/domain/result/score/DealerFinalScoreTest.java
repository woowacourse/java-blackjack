package domain.result.score;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;

public class DealerFinalScoreTest {
	Player player;
	Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player("둔덩");
		dealer = new Dealer();
	}

	@DisplayName("true인경우")
	@Test
	void isBiggerTest() {
		dealer.addCards(Arrays.asList(new Card("하트", "1")));
		player.addCards(Arrays.asList(new Card("하트", "1")));

		DealerFinalScore dealerFinalScore = new DealerFinalScore(dealer);
		PlayerFinalScore playerFinalScore = new PlayerFinalScore(player);

		assertThat(dealerFinalScore.isBigger(playerFinalScore)).isTrue();
	}

	@DisplayName("false인경우")
	@Test
	void isBiggerTest2() {
		dealer.addCards(Arrays.asList(new Card("하트", "2")));
		player.addCards(Arrays.asList(new Card("하트", "3")));

		DealerFinalScore dealerFinalScore = new DealerFinalScore(dealer);
		PlayerFinalScore playerFinalScore = new PlayerFinalScore(player);

		assertThat(dealerFinalScore.isBigger(playerFinalScore)).isFalse();
	}
}
