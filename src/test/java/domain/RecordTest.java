package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecordTest {
	@Test
	void create() {
		assertThat(new Record(0, 0)).isInstanceOf(Record.class);
	}

	@Test
	@SuppressWarnings("NonAsciiCharacters")
	void create_카운트가_0_미만인_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Record(-1, 0));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Record(0, -1));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Record(-1, -1));
	}

	@Test
	void getWinCount() {
		assertThat(new Record(3, 0).getWinCount()).isEqualTo(3);
	}

	@Test
	void getLoseCount() {
		assertThat(new Record(0, 5).getLoseCount()).isEqualTo(5);
	}

	@Test
	void hasMany() {
		assertThat(new Record(1, 2).hasMany()).isTrue();
	}

	@Test
	void hasWin() {
		assertThat(new Record(2, 0).hasWin()).isTrue();
	}
}
