package domain.participant.info;

import static domain.card.Cards.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
	@Test
	@DisplayName("에이스가 있는지 테스트_있는 경우")
	void hasAce() {
		Hand hand = new Hand(List.of(ACE_CLOVER, QUEEN_CLOVER));
		assertThat(hand.hasAce()).isTrue();
	}

	@Test
	@DisplayName("에이스가 있는지 테스트_없는 경우")
	void hasNotAce() {
		Hand hand = new Hand(List.of(TWO_CLOVER, QUEEN_CLOVER));
		assertThat(hand.hasAce()).isFalse();
	}
}
