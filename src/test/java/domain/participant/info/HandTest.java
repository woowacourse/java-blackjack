package domain.participant.info;

import static domain.card.Cards.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

	@Test
	@DisplayName("카드 추가 테스트")
	void add() {
		Hand hand = new Hand(new ArrayList<>(List.of(ACE_CLOVER, QUEEN_CLOVER)));
		hand.add(TWO_CLOVER);
		assertThat(hand.show()).isEqualTo(List.of("A클로버", "Q클로버", "2클로버"));
	}
}
