package domain.result;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.result.score.DealerFinalScore;
import domain.result.score.PlayerFinalScore;
import domain.user.Dealer;
import domain.user.Player;

public class RefereeTest {
	Player player;
	Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player("둔덩");
		dealer = new Dealer();
	}

	@DisplayName("플레이어가 이기는 경우")
	@Test
	void isPlayerWinTest() {
		player.addCards(Arrays.asList(new Card("하트", "3")));
		dealer.addCards(Arrays.asList(new Card("하트", "2")));

		assertThat(Referee.isPlayerWin(
			new PlayerFinalScore(player), new DealerFinalScore(dealer))).isTrue();
	}

	@DisplayName("플레이어가 지는 경우")
	@Test
	void isPlayerLoseTest() {
		player.addCards(Arrays.asList(new Card("하트", "2")));
		dealer.addCards(Arrays.asList(new Card("하트", "2")));

		assertThat(Referee.isPlayerLose(
			new PlayerFinalScore(player), new DealerFinalScore(dealer))).isTrue();
	}

	@DisplayName("비기는 경우")
	@Test
	void isPlayerDrawTest() {
		player.addCards(Arrays.asList(new Card("하트", "1"),
			new Card("하트", "K")));
		dealer.addCards(Arrays.asList(new Card("하트", "1"),
			new Card("하트", "K")));

		assertThat(Referee.isPlayerDraw(
			new PlayerFinalScore(player), new DealerFinalScore(dealer))).isTrue();
	}
}
