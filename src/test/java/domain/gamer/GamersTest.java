package domain.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    @DisplayName("플레이어들 간 중복된 이름을 가질 경우 예외를 발생시킨다.")
    @Test
    void createDomainByDuplicatedNames() {
        assertThatThrownBy(() -> new Gamers(List.of("player1", "player1", "player2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 간 중복된 이름을 가질 수 없습니다.");
    }

    @DisplayName("플레이어가 1명보다 적을 경우 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidMinSize() {
        assertThatThrownBy(() -> new Gamers(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어는 최소 1명에서 최대 8명까지 참여할 수 있습니다.");
    }

    @DisplayName("플레이어가 8명이 넘어갈 경우 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidMaxSize() {
        assertThatThrownBy(() -> new Gamers(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어는 최소 1명에서 최대 8명까지 참여할 수 있습니다.");
    }

    @DisplayName("올바른 조건의 플레이어가 전달된 경우 성공적으로 도메인을 생성한다.")
    @Test
    void createDomainSuccessfully() {
        assertThatCode(() -> new Gamers(List.of("1", "2")))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 이름이 \"딜러\"면 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidName() {
        assertThatThrownBy(() -> new Gamers(List.of("딜러")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 \"딜러\"가 될 수 없습니다.");
    }
}
