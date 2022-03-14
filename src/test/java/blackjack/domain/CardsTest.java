package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.machine.Cards;
import blackjack.domain.strategy.NumberGenerator;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
	@DisplayName("카드 뽑기 테스트")
	@Test
	void pickCard() {
		NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(2));
		Cards cards = new Cards();

		assertThat(cards.pickCard(numberGenerator).getName()).isEqualTo("3다이아몬드");
	}

	@DisplayName("이미 뽑힌 카드의 인덱스가 들어가면 ERROR")
	@Test
	void duplicate() {
		NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(2));
		Cards cards = new Cards();
		cards.pickCard(numberGenerator);

		assertThatThrownBy(() -> cards.pickCard(numberGenerator))
			.isInstanceOf(IndexOutOfBoundsException.class);
	}
}


