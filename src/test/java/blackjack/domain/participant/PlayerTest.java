package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class PlayerTest {

	@Test
	@DisplayName("플레이어는 카드의 수가 2장이 아니면 게임을 아직 시작할 수 없다.")
	void is_not_ready() {
		Player player = new Player(new Name("pobi"));

		assertThat(player.isReady()).isFalse();
	}

	@Test
	@DisplayName("플레이어는 카드의 수가 2장 이상이면 게임을 시작할 수 있다.")
	void is_ready() {
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));
		player.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));

		assertThat(player.isReady()).isTrue();
	}

	@Test
	@DisplayName("플레이어는 현재 상태를 판단해 턴 종료 여부를 반환한다.")
	void is_finished() {
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));
		player.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));

		assertThat(player.isFinished()).isTrue();
	}
	
	@Test
	@DisplayName("플레이어는 현재 턴이 종료된 상태가 아니라면 카드를 뽑을 수 있다.")
	void draw() {
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(Denomination.QUEEN, Suit.CLOVER));

		List<Card> cards = player.getCards()
			.getValue();
		assertThat(cards.size()).isEqualTo(1);
	}

	@Test
	@DisplayName("플레이어가 현재 턴이 종료된 상태에서 카드를 뽑으면 에러가 발생한다.")
	void can_not_draw() {
		//given
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(Denomination.QUEEN, Suit.CLOVER));
		player.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));

		//when, then
		assertThatThrownBy(() -> player.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER)))
			.isInstanceOf(IllegalStateException.class)
			.hasMessageContaining("턴이 종료된 상태입니다.");
	}

	@Test
	@DisplayName("카드의 총점을 계산한다.")
	void score() {
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(Denomination.QUEEN, Suit.CLOVER));
		player.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));

		assertThat(player.score()).isEqualTo(21);
	}

	@Test
	@DisplayName("플레이어가 stay를 진행 하면 턴을 종료한다")
	void stay() {
		Player player = new Player(new Name("pobi"));

		player.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));
		player.draw(Card.valueOf(Denomination.QUEEN, Suit.CLOVER));

		player.stay();

		assertThat(player.isFinished()).isTrue();
	}
}
