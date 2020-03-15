package com.blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
	@DisplayName("Symbol과 Type을 인자로 넣었을때 인스턴스 생성")
	@Test
	void constructor_SymbolAndType_CreateInsatnce() {
		assertThat(Card.valueOf(Symbol.ACE, Type.CLUB)).isInstanceOf(Card.class);
	}

	@DisplayName("Symbol과 Type에 null을 인자로 넣었을때 예외 발생")
	@Test
	void constructor_SymbolAndTypeNull_ThrownException() {
		assertThatThrownBy(() -> Card.valueOf(null, null)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Symbol에 null을 인자로 넣었을때 예외 발생")
	@Test
	void constructor_SymbolNull_ThrownException() {
		assertThatThrownBy(() -> Card.valueOf(null, Type.CLUB)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("Type에 null을 인자로 넣었을때 예외 발생")
	@Test
	void constructor_TypeNull_ThrownException() {
		assertThatThrownBy(() -> Card.valueOf(Symbol.ACE, null)).isInstanceOf(IllegalArgumentException.class);
	}
}
