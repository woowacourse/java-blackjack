package blackjack.domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    private AcceptStrategy inputStrategy;

    @BeforeEach
    void setup() {
        inputStrategy = new ParticipantAcceptStrategy();
    }

    @ParameterizedTest
    @MethodSource("participantListBySuccess")
    @DisplayName("참가자는 2~8명 사이이다. (성공)")
    void checkParticipantNumberBySuccess(List<Player> participants) {
        Dealer dealer = new Dealer();
        assertDoesNotThrow(() -> new Players(participants, dealer));
    }

    private static Stream<List<Player>> participantListBySuccess() {
        AcceptStrategy inputStrategy = new ParticipantAcceptStrategy();
        return Stream.of(
                List.of(
                        new Participant("pobi", inputStrategy),
                        new Participant("corin", inputStrategy)
                ),
                List.of(
                        new Participant("1", inputStrategy),
                        new Participant("2", inputStrategy),
                        new Participant("3", inputStrategy),
                        new Participant("4", inputStrategy),
                        new Participant("5", inputStrategy),
                        new Participant("6", inputStrategy),
                        new Participant("7", inputStrategy),
                        new Participant("8", inputStrategy)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("participantListByFail")
    @DisplayName("참가자는 2~8명 사이이다. (실패)")
    void checkParticipantNumber(List<Player> participants) {
        Dealer dealer = new Dealer();

        Assertions.assertThatThrownBy(() -> new Players(participants, dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 정보가 잘못 입력되었습니다.");
    }

    private static Stream<List<Player>> participantListByFail() {
        AcceptStrategy inputStrategy = new ParticipantAcceptStrategy();
        return Stream.of(
                null,
                List.of(
                        new Participant("pobi", inputStrategy)
                ),
                List.of(
                        new Participant("1", inputStrategy),
                        new Participant("2", inputStrategy),
                        new Participant("3", inputStrategy),
                        new Participant("4", inputStrategy),
                        new Participant("5", inputStrategy),
                        new Participant("6", inputStrategy),
                        new Participant("7", inputStrategy),
                        new Participant("8", inputStrategy),
                        new Participant("9", inputStrategy)
                )
        );
    }

    @Test
    @DisplayName("참가자 이름은 중복될 수 없다.")
    void thrownExceptionWhenNamesDuplicated() {
        Player dealer = new Dealer();
        Assertions.assertThatThrownBy(() -> new Players(List.of(
                        new Participant("pobi", inputStrategy),
                        new Participant("pobi", inputStrategy)
                ), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름은 중복될 수 없습니다.");
    }
}
