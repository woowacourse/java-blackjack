package domain.gameplaying;

import static org.junit.jupiter.api.Assertions.*;

import domain.gameplaying.strategy.RandomStrategy;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantsTest {

    private Participants participants;

    @BeforeEach
    void setUp() {
        this.participants = Participants.onlyDealer(new RandomStrategy());
    }

    @ParameterizedTest
    @MethodSource("playerNames")
    @DisplayName("참여자들은 모든 플레이어의 이름을 반환할 수 있어야한다.")
    void 참여자들_생성_확인(List<String> names) {
        Participants participants =  this.participants.join(names);

        List<String> actual = participants.allPlayerNames();

        assertEquals(names, actual);
    }

    static Stream<List<String>> playerNames() {
        return Stream.of(
                List.of("pobi"),
                List.of("pobi", "jason"),
                List.of("pobi", "jason", "tars")
        );
    }
}
