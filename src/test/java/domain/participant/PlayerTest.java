package domain.participant;

import static domain.card.Cards.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class PlayerTest {

	@Test
	@DisplayName("드로우할 수 있는 경우 할 수 있는지 테스트")
	void canDraw() {
		Hand hand = new Hand(List.of(ACE_CLOVER, TWO_CLOVER));
		Player player = new Player(new Name("pobi"), hand, new Betting(0));
		assertThat(player.canDraw()).isTrue();
	}

	@Test
	@DisplayName("버스트 일 경우 드로우할 수 있는지 테스트")
	void cantDrawBust() {
		Hand hand = new Hand(List.of(QUEEN_CLOVER, QUEEN_CLOVER, TWO_CLOVER));
		Player player = new Player(new Name("pobi"), hand, new Betting(0));
		assertThat(player.canDraw()).isFalse();
	}

	@Test
	@DisplayName("블랙잭일 경우 드로우할 수 있는지 테스트")
	void cantDrawBlackJack() {
		Hand hand = new Hand(List.of(QUEEN_CLOVER, ACE_CLOVER));
		Player player = new Player(new Name("pobi"), hand, new Betting(0));
		assertThat(player.canDraw()).isFalse();
	}
}
