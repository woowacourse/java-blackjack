package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayersTest {

	@DisplayName("플레이어의 수가 1 이상 또는 8 이하이면 정상적으로 생성된다.")
	@ParameterizedTest
	@MethodSource("playersSizeSuccessTestArguments")
	void playersSizeSuccessTest(List<String> players) {
		assertThatCode(() -> new Players(players))
			.doesNotThrowAnyException();
	}

	private static Stream<Arguments> playersSizeSuccessTestArguments() {
		return Stream.of(
			Arguments.arguments(List.of("p1")),
			Arguments.arguments(List.of("p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"))
		);
	}

	@DisplayName("플레이어의 수가 1 미만 또는 8 초과이면 예외를 발생시킨다.")
	@ParameterizedTest
	@MethodSource("playersSizeErrorTestArguments")
	void playersSizeErrorTest(List<String> players) {
		assertThatThrownBy(() -> new Players(players))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Players.SIZE_ERROR_MESSAGE);
	}

	private static Stream<Arguments> playersSizeErrorTestArguments() {
		return Stream.of(
			Arguments.arguments(List.of()),
			Arguments.arguments(List.of("p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9"))
		);
	}

	@DisplayName("플레이어의 이름이 중복되면 예외가 발생한다.")
	@ParameterizedTest
	@MethodSource("playersNameDuplicateErrorTestArguments")
	void playersNameDuplicateErrorTest(List<String> players) {
		assertThatThrownBy(() -> new Players(players))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(Players.DUPLICATION_ERROR_MESSAGE);
	}

	private static Stream<Arguments> playersNameDuplicateErrorTestArguments() {
		return Stream.of(
			Arguments.arguments(List.of("p1", "p1", "p3"))
		);
	}
}
