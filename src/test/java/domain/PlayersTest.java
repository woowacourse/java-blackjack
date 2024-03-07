package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @Test
    @DisplayName("참여자 이름 중복시 예외가 발생한다.")
    void duplicatePlayerName() {
        //given
        final List<String> names = List.of("redy", "redy");

        //when & then
        Assertions.assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참여자 이름은 중복될 수 없습니다.");
    }

    @DisplayName("총 참여자 수가 2이상 8이하이면 참여자를 생성한다.")
    @ParameterizedTest
    @MethodSource("validPlayersSizeParameterProvider")
    void validPlayersSize(final List<String> names) {
        Assertions.assertThatCode(() -> Players.from(names))
                .doesNotThrowAnyException();
    }

    @DisplayName("총 참여자 수는 2이상 8이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidPlayersSizeParameterProvider")
    void invalidPlayersSize(final List<String> names) {
        Assertions.assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 참여자 수입니다.");
    }

    @Test
    @DisplayName("참가자 중 버스트 되지 않은 참가자가 있다면 False를 반환한다.")
    void isAllBustFalse() {
        //given
        Hands bustHands = new Hands(
                List.of(new Card(CardNumber.JACK, CardShape.HEART), new Card(CardNumber.TEN, CardShape.SPADE),
                        new Card(CardNumber.TEN, CardShape.HEART)));

        Hands noBustHands = new Hands(
                List.of(new Card(CardNumber.JACK, CardShape.HEART), new Card(CardNumber.TEN, CardShape.SPADE)));

        Player player1 = new Player("레디", bustHands);
        Player player2 = new Player("제제", noBustHands);
        Players players = new Players(List.of(player1, player2));

        //when && then
        Assertions.assertThat(players.isAllBust()).isFalse();
    }

    @Test
    @DisplayName("모든 참가자가 버스트되면 True를 반환한다.")
    void isAllBustTrue() {
        //given
        Hands bustHands = new Hands(
                List.of(new Card(CardNumber.JACK, CardShape.HEART), new Card(CardNumber.TEN, CardShape.SPADE),
                        new Card(CardNumber.TEN, CardShape.HEART)));

        Player player1 = new Player("레디", bustHands);
        Player player2 = new Player("제제", bustHands);
        Player player3 = new Player("수달", bustHands);
        Player player4 = new Player("피케이", bustHands);

        Players players = new Players(List.of(player1, player2, player3, player4));

        //when && then
        Assertions.assertThat(players.isAllBust()).isTrue();
    }


    static Stream<Arguments> validPlayersSizeParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of("pobi", "jason")
                ),
                Arguments.of(
                        List.of("1", "2", "3", "4", "5", "6", "7", "8")
                )
        );
    }

    static Stream<Arguments> invalidPlayersSizeParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of("pobi")
                ),
                Arguments.of(
                        List.of("1", "2", "3", "4", "5", "6", "7", "8", "9")
                )
        );
    }
}
