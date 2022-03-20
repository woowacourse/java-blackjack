package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPickMachine;

import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardPickMachineTest {
	@DisplayName("카드 뽑기 테스트")
	@Test
	void pickCard() {
		CardPickMachine cards = new CardPickMachine();
		IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(1));
		Card card = cards.pickCard(new Cards().getCardDeck(),intendedNumberGenerator);

		assertThat(card.getName()).isEqualTo("2다이아몬드");
	}

	@DisplayName("이미 뽑힌 카드의 인덱스가 들어가면 ERROR")
	@Test
	void duplicate() {
		CardPickMachine cards = new CardPickMachine();
		IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(1));
		cards.pickCard(new Cards().getCardDeck(), intendedNumberGenerator);

		assertThatThrownBy(() -> cards.pickCard(new Cards().getCardDeck(), intendedNumberGenerator))
				.isInstanceOf(IndexOutOfBoundsException.class);
	}
}
