package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {
	@Test
	void create() {
		assertThat(Card.of("스페이드", "A")).isInstanceOf(Card.class);
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void create_null을_입력한_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Card.of(Type.CLOVER, null));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Card.of(null, Symbol.ACE));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Card.of((Type)null, (Symbol)null));
	}

	@Test
	void isAce() {
		assertThat(Card.of("스페이드", "A").isAce()).isTrue();
	}

	@Test
	void getPoint() {
		assertThat(Card.of("스페이드", "A").getPoint()).isEqualTo(1);
		assertThat(Card.of("스페이드", "K").getPoint()).isEqualTo(10);
	}

	@Test
	void getName() {
		assertThat(Card.of("하트", "A").getName()).isEqualTo("A하트");
	}
}
