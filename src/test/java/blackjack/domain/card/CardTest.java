package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import blackjack.domain.exceptions.InvalidCardException;

class CardTest {
	@Test
	void Card_SymbolAndType_GenerateInstance() {
		assertThat(Card.of(Symbol.ACE, Type.CLUB)).isInstanceOf(Card.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSymbol_InvalidCardExceptionThrown(Symbol nullSymbol) {
		assertThatThrownBy(() -> new Card(nullSymbol, Type.DIAMOND))
			.isInstanceOf(InvalidCardException.class)
			.hasMessage(InvalidCardException.NULL);
		assertThatThrownBy(() -> Card.of(nullSymbol, Type.DIAMOND))
			.isInstanceOf(InvalidCardException.class)
			.hasMessage(InvalidCardException.NULL);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullType_InvalidCardExceptionThrown(Type nullType) {
		assertThatThrownBy(() -> new Card(Symbol.TEN, nullType))
			.isInstanceOf(InvalidCardException.class)
			.hasMessage(InvalidCardException.NULL);
		assertThatThrownBy(() -> Card.of(Symbol.TEN, nullType))
			.isInstanceOf(InvalidCardException.class)
			.hasMessage(InvalidCardException.NULL);
	}

	@Test
	void isAce_CardSymbolIsAce_ReturnTrue() {
		assertThat(Card.of(Symbol.ACE, Type.DIAMOND).isAce()).isTrue();
	}

	@Test
	void name_SymbolAndType_CardName() {
		String expected = "10♣";
		assertThat(Card.name(Symbol.TEN, Type.CLUB)).isEqualTo(expected);
	}
}
