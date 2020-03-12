package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NameTest {
	@Test
	void create() {
		assertThat(new Name("name")).isInstanceOf(Name.class);
	}

	@Test
	void create_이름이_비어있는_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(null));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(""));
	}

	@Test
	void create_이름에_공백이_포함된_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(" "));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(" a"));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name("a "));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(" a "));
	}
}


