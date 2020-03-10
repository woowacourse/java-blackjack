package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TypeTest {
	@Test
	void of() {
		assertThat(Type.of("스페이드") == Type.SPADE).isTrue();
		assertThat(Type.of("하트") == Type.HEART).isTrue();
		assertThat(Type.of("클로버") == Type.CLOVER).isTrue();
		assertThat(Type.of("다이아몬드") == Type.DIAMOND).isTrue();
	}

	@Test
	void of_존재하지_않는_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Type.of("세모"));
	}
}
