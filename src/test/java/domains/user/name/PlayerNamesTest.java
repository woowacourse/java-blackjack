package domains.user.name;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PlayerNamesTest {
	@DisplayName("이름들이 null 혹은 빈문자열 일 때 예외 처리")
	@ParameterizedTest
	@NullAndEmptySource
	void constructor_NulLAndEmpty_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new PlayerNames(input))
			.isInstanceOf(InvalidPlayerNamesException.class)
			.hasMessage(InvalidPlayerNamesException.NULL_OR_EMPTY);
	}

	@DisplayName("참가자가 5명 초과 할 경우 예외처리")
	@Test
	void checkNumberOfPlayers_OverFivePlayers_ExceptionThrown() {
		assertThatThrownBy(() -> new PlayerNames("a,b,c,d,e,f"))
			.isInstanceOf(InvalidPlayerNamesException.class)
			.hasMessage(InvalidPlayerNamesException.OVER_NUMBER_OF_PLAYERS);
	}

	@DisplayName("참가자들간 중복된 이름이 있다면 예외처리")
	@Test
	void checkDuplicationName_HasDuplication_ExceptionThrown() {
		assertThatThrownBy(() -> new PlayerNames("a,b,c, c"))
			.isInstanceOf(InvalidPlayerNamesException.class)
			.hasMessage(InvalidPlayerNamesException.DUPLICATION);
	}
}
