package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NameTest {
	@Test
	void create() {
		assertThat(new Name("name")).isInstanceOf(Name.class);
	}

	@Test
	void create_이름이_공백인_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(""));
	}
}


