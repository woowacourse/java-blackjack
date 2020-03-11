package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */

@SuppressWarnings("NonAsciiCharacters")
public class SymbolTest {
	@ParameterizedTest
	@CsvSource(value = {"4, false", "21, false", "22, true"})
	void 버스트(int score, boolean expected) {
		assertThat(Symbol.isBust(score)).isEqualTo(expected);
	}
}
