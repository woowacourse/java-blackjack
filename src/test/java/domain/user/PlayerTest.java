package domain.user;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;

public class PlayerTest {
	@DisplayName("플레이어의 카드가 2장이면서, 총 점수가 21점이면, 블랙잭이다.")
	@Test
	void isBlackjackTrueTest() {
		Player player = Player.fromNameAndCards("동글", new Card(CLOVER, ACE), new Card(CLOVER, JACK));

		assertThat(player.isBlackjack()).isTrue();
	}

	@DisplayName("플레이어의 카드가 2장이 아니고, 총 점수가 21점이면, 블랙잭이 아니다.")
	@Test
	void isBlackjackFalseTest() {
		Player player = Player.fromNameAndCards("동글", new Card(CLOVER, ACE), new Card(CLOVER, FIVE),
			new Card(HEART, FIVE));

		assertThat(player.isBlackjack()).isFalse();
	}

	@DisplayName("플레이어의 카드가 2장이고, 총 점수가 21점이 아니면, 블랙잭이 아니다.")
	@Test
	void isBlackjackFalseTest2() {
		Player player = Player.fromNameAndCards("동글", new Card(CLOVER, ACE), new Card(CLOVER, FIVE));

		assertThat(player.isBlackjack()).isFalse();
	}

	@DisplayName("플레이어의 카드가 2장이 아니고, 총 점수가 21점이 아니면, 블랙잭이 아니다.")
	@Test
	void isBlackjackFalseTest3() {
		Player player = Player.fromNameAndCards("동글", new Card(CLOVER, ACE), new Card(CLOVER, FIVE),
			new Card(CLOVER, THREE));

		assertThat(player.isBlackjack()).isFalse();
	}

	@DisplayName("첫번째 드로우 카드 리스트를 가져올때, 플레이어의 카드 중 첫번째 두장을 가져온다.")
	@Test
	void getInitialCardTest() {
		Player player = Player.fromNameAndCards("name", new Card(HEART, EIGHT), new Card(CLOVER, NINE),
			new Card(DIAMOND, TWO));
		assertThat(player.getInitialCard()).containsExactly(new Card(HEART, EIGHT), new Card(CLOVER, NINE));
	}

	@DisplayName("플레이어의 카드 점수 합계가 21점이 넘어가는 경우 버스트다.")
	@Test
	void isBustTrueTest() {
		Player player = Player.fromNameAndCards("test", new Card(HEART, EIGHT), new Card(CLOVER, NINE),
			new Card(SPADE, JACK));
		assertThat(player.isBust()).isTrue();
	}

	@DisplayName("플레이어의 카드 점수 합계가 21점이 넘지 않는 경우 버스트가 아니다.")
	@Test
	void isBustFalseTest() {
		Player player = Player.fromNameAndCards("test", new Card(HEART, EIGHT), new Card(SPADE, JACK));
		assertThat(player.isBust()).isFalse();
	}

	@DisplayName("블랙잭이 아니고, 버스트가 아닌 경우 추가 드로우 할 수 있다.")
	@Test
	void isDrawableTest() {
		Player player = Player.fromNameAndCards("test", new Card(HEART, EIGHT), new Card(SPADE, JACK));
		assertThat(player.isDrawable()).isTrue();
	}
}
