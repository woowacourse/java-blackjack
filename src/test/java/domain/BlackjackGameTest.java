package domain;

import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackjackGameTest {
    @DisplayName("유저는 최소 1명 이상 7명 이하여야 한다.")
    @ParameterizedTest
    @MethodSource("userTestCase")
    void test(List<String> names) {
        // given
        List<Player> players = names.stream().map(Player::new).toList();

        // when & then
        Assertions.assertThatCode(() -> BlackjackGame.of(
                players,
                new Dealer(),
                new CardDeck())).doesNotThrowAnyException();
    }

    private static Stream<Arguments> userTestCase() {
        return Stream.of(
                Arguments.arguments(List.of("수양", "레몬", "키키", "나나", "모모", "부부", "롸롸")),
                Arguments.arguments(List.of("수양"))
        );
    }

    @DisplayName("등록한 유저가 기준보다 적거나 많으면 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("userExceptionTestCase")
    void test2(List<String> names) {
        // given
        List<Player> users = names.stream().map(Player::new).toList();

        // when & then
        Assertions.assertThatThrownBy(() -> BlackjackGame.of(users, new Dealer(), new CardDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 1명 이상 7명 이하로 등록해야 합니다.");
    }

    private static Stream<Arguments> userExceptionTestCase() {
        return Stream.of(
                Arguments.arguments(List.of("수양", "레몬", "키키", "나나", "모모", "부부", "롸롸", "뫄뫄")),
                Arguments.arguments(List.of())
        );
    }

    @Test
    @DisplayName("유저는 중복될 수 없다.")
    void test3() {
        // given
        List<String> names = List.of("수양", "레몬", "수양", "레몬", "부부", "롸롸", "뫄뫄");
        List<Player> users = names.stream().map(Player::new).toList();

        // when & then
        Assertions.assertThatThrownBy(() -> BlackjackGame.of(users, new Dealer(), new CardDeck()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저는 중복될 수 없습니다.");
    }

    @DisplayName("게임 시작 시 모든 유저와 딜러는 카드를 두 장씩 배부받는다.")
    @Test
    void test4() {
        // given
        List<String> names = List.of("수양", "레몬");
        List<Player> users = names.stream().map(Player::new).toList();
        BlackjackGame blackjackGame = BlackjackGame.of(users, new Dealer(), new CardDeck());

        // when
        blackjackGame.firstHandOutCard();

        Assertions.assertThat(blackjackGame.getDealer().getSize()).isEqualTo(2);
        blackjackGame.getParticipants()
                .getPlayers()
                .forEach(player -> Assertions.assertThat(player.getSize()).isEqualTo(2));
    }
}
