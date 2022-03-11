package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.strategy.NumberGenerator;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@DisplayName("카드 한장 뽑기 테스트")
	@Test
	void pickCard() {
		Cards cards = new Cards();
		NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(2));
		assertThat(cards.pickCard(numberGenerator).getName()).isEqualTo("3다이아몬드");
	}
}
