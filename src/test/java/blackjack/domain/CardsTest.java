package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@Test
	@DisplayName("카드 한 장을 저장한다.")
	void saveCard() {
		Cards cards = new Cards();
		cards.save(new Card(Suit.CLOVER, Denomination.FIVE));
		assertThat(cards.getCards().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("Ace가 있는 상태로 total point를 계산한다.")
	void sumTotalPointWithAce() {
		Cards cards = new Cards();

		cards.save(new Card(Suit.CLOVER, Denomination.ACE));
		cards.save(new Card(Suit.DIAMOND, Denomination.JACK));

		assertThat(cards.calculateTotalPoint()).isEqualTo(21);
	}

	@Test
	@DisplayName("Ace가 있는 상태로 total point가 21을 초과하는 경우 total point를 계산한다.")
	void sumTotalPointWithAceOverMax() {
		Cards cards = new Cards();

		cards.save(new Card(Suit.CLOVER, Denomination.ACE));
		cards.save(new Card(Suit.DIAMOND, Denomination.JACK));
		cards.save(new Card(Suit.DIAMOND, Denomination.ACE));

		assertThat(cards.calculateTotalPoint()).isEqualTo(12);
	}
}
