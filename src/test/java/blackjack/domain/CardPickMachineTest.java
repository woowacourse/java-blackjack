package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.CardPickMachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardPickMachineTest {
	@DisplayName("카드 뽑기 테스트")
	@Test
	void pickCard() {
		CardPickMachine cards = new CardPickMachine();

		assertThat(cards.pickCard(1).getName()).isEqualTo("2다이아몬드");
	}

	@DisplayName("이미 뽑힌 카드의 인덱스가 들어가면 ERROR")
	@Test
	void duplicate() {
		CardPickMachine cards = new CardPickMachine();
		cards.pickCard(1);

		assertThatThrownBy(() -> cards.pickCard(1))
				.isInstanceOf(IndexOutOfBoundsException.class);
	}
}
