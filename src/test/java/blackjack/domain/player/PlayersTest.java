package blackjack.domain.player;

import blackjack.domain.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayersTest {

    @ParameterizedTest
    @MethodSource("participantListBySuccess")
    @DisplayName("참가자는 2~8명 사이이다. (성공)")
    void checkParticipantNumberBySuccess(List<Player> participants) {
        Dealer dealer = new Dealer();
        assertDoesNotThrow(() -> new Players(participants, dealer));
    }

    private static Stream<List<Player>> participantListBySuccess() {
        return Stream.of(
                List.of(
                        new Participant("pobi"),
                        new Participant("corinne")
                ),
                List.of(
                        new Participant("1"),
                        new Participant("2"),
                        new Participant("3"),
                        new Participant("4"),
                        new Participant("5"),
                        new Participant("6"),
                        new Participant("7"),
                        new Participant("8")
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
        return Stream.of(
                null,
                List.of(
                        new Participant("pobi")
                ),
                List.of(
                        new Participant("1"),
                        new Participant("2"),
                        new Participant("3"),
                        new Participant("4"),
                        new Participant("5"),
                        new Participant("6"),
                        new Participant("7"),
                        new Participant("8"),
                        new Participant("9")
                )
        );
    }

    @Test
    @DisplayName("참가자 이름은 중복될 수 없다.")
    void thrownExceptionWhenNamesDuplicated() {
        Player dealer = new Dealer();
        Assertions.assertThatThrownBy(() -> new Players(List.of(
                        new Participant("pobi"),
                        new Participant("pobi")
                ), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참가자 이름은 중복될 수 없습니다.");
    }


}
