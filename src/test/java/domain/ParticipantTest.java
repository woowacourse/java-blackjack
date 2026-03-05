package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {

    @ParameterizedTest(name = "[{index}] 참여자: {0}")
    @MethodSource("participantsAndNames")
    @DisplayName("참여자는 이름을 반환할 수 있어야 한다.")
    void 참여자_이름_반환(String expected, Participant participant) {
        String actual = participant.name();

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> participantsAndNames() {
        return Stream.of(
                Arguments.arguments("딜러", new Dealer("딜러")),
                Arguments.arguments("pobi", new Player("pobi")),
                Arguments.arguments("jason", new Player("jason")),
                Arguments.arguments("tars", new Player("tars"))
        );
    }
}