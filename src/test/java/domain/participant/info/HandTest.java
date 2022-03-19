package domain.participant.info;

import static domain.card.Cards.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

	@Test
	@DisplayName("블랙잭 확인 테스트")
	void isBlackJack() {
		Hand hand = new Hand(List.of(ACE_CLOVER, QUEEN_CLOVER));
		assertThat(hand.isBlackJack()).isTrue();
	}

	@Test
	@DisplayName("버스트 확인 테스트")
	void isBust() {
		Hand hand = new Hand(List.of(TWO_CLOVER, QUEEN_CLOVER, QUEEN_CLOVER));
		assertThat(hand.isBust()).isTrue();
	}

	@Test
	@DisplayName("Ace가 있을 경우 계산_ACE가 11로 계산될 경우")
	void getScoreAceEleven() {
		Hand hand = new Hand(List.of(ACE_CLOVER, TWO_CLOVER));
		assertThat(hand.getScore()).isEqualTo(13);
	}

	@Test
	@DisplayName("Ace가 있을 경우 계산_ACE가 1로 계산될 경우")
	void getScoreAceOne() {
		Hand hand = new Hand(List.of(ACE_CLOVER, TWO_CLOVER, QUEEN_CLOVER));
		assertThat(hand.getScore()).isEqualTo(13);
	}

	@Test
	@DisplayName("카드 추가 테스트")
	void add() {
		Hand hand = new Hand(new ArrayList<>(List.of(ACE_CLOVER, QUEEN_CLOVER)));
		hand.add(TWO_CLOVER);
		assertThat(hand.show()).isEqualTo(List.of("A클로버", "Q클로버", "2클로버"));
	}
}
