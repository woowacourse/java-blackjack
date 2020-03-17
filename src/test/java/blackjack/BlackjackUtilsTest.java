package blackjack;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.user.Player;

public class BlackjackUtilsTest {
	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player("둔덩");
		player.addCards(Arrays.asList(new Card("하트", "1"),
			new Card("하트", "K")));
	}

	@DisplayName("블랙잭인 경우")
	@Test
	void isBlackjackTest1() {
		assertThat(player.isBlackjack()).isTrue();
	}

	@DisplayName("블랙잭 아닌 경우")
	@Test
	void isBlackjackTest2() {
		player.addCards(Arrays.asList(new Card("하트", "10")));

		assertThat(player.isBlackjack()).isFalse();
	}

	@DisplayName("버스트인 경우")
	@Test
	void isBustTest() {
		player.addCards(Arrays.asList(new Card("하트", "K"),
			new Card("하트", "1")));

		assertThat(player.isBust()).isTrue();
	}

	@DisplayName("버스트가 아닌 경우")
	@Test
	void isBustTest2() {
		assertThat(player.isBust()).isFalse();
	}
}
