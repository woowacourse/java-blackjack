package blackjack.card.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;

class CardTest {

	@DisplayName("카드가 ACE 인지 아닌지 확인")
	@ParameterizedTest
	@CsvSource(value = {"ACE,true", "TWO,false"})
	void isAce(CardNumber cardNumber, boolean expect) {
		Card card = Card.of(Symbol.HEART, cardNumber);
		assertThat(card.isAce()).isEqualTo(expect);
	}

	@DisplayName("카드의 점수를 반환하는 기능")
	@ParameterizedTest
	@CsvSource(value = {"ACE,1", "TEN,10", "KING,10"})
	void name(CardNumber cardNumber, int expect) {
		Card card = Card.of(Symbol.CLUB, cardNumber);
		assertThat(card.getScore()).isEqualTo(expect);
	}

	@DisplayName("싱글톤으로 만들어둔 CardCache에서 카드 가져오기")
	@Test
	void of() {
		//given
		Symbol symbol = Symbol.DIAMOND;
		CardNumber cardNumber = CardNumber.FIVE;

		//when
		Card card = Card.of(symbol, cardNumber);

		//then
		assertThat(card.getSymbol()).isEqualTo(Symbol.DIAMOND.getName());
		assertThat(card.getNumber()).isEqualTo(CardNumber.FIVE.getNumber());
	}
}