package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.game.GameRule;
import blackjack.domain.value.Nickname;
import blackjack.exception.ExceptionMessage;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameUserStorageTest {

    static final List<Nickname> NICKNAMES = List.of(new Nickname("쿠키"), new Nickname("빙봉"));

    @Test
    @DisplayName("입력된 닉네임으로 플레이어를 등록할 수 있다.")
    void canRegisterPlayer() {
        GameUserStorage gameUserStorage = new GameUserStorage();

        gameUserStorage.registerPlayer(NICKNAMES);

        assertThat(gameUserStorage.getPlayers())
                .extracting(Player::getNickname)
                .containsExactlyInAnyOrder("쿠키", "빙봉");
    }

    @Test
    @DisplayName("등록할 플레이어의 수가 0명인 경우 예외를 발생시킨다.")
    void canEmptyPlayer() {
        List<Nickname> emptyNickname = List.of();
        assertThatThrownBy(() -> new GameUserStorage(emptyNickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_PLAYER_COUNT.getContent());
    }

    @Test
    @DisplayName("등록할 플레이어의 수가 너무 많은 경우 예외를 발생시킨다.")
    void canTooManyPlayer() {
        List<Nickname> tooManyNickname =
                IntStream.range(0, GameRule.MAX_PLAYER_COUNT.getValue() + 1)
                        .mapToObj(number -> new Nickname("player" + number))
                        .toList();
        assertThatThrownBy(() -> new GameUserStorage(tooManyNickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_PLAYER_COUNT.getContent());
    }

    @Test
    @DisplayName("중복된 플레이어는 허용하지 않는다.")
    void canValidateDuplicatedPlayer() {
        List<Nickname> duplicatedPlayer = List.of(new Nickname("쿠키"), new Nickname("쿠키"));
        assertThatThrownBy(() -> new GameUserStorage(duplicatedPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_ALLOWED_DUPLICATED_PLAYER.getContent());
    }
}