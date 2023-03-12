package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayersTest {

    @Test
    @DisplayName("Players 객체의 필드 크기는 Player 수와 동일하다")
    void sizeOfPlayersIsSameWithNumberOfPlayer() {
        List<String> names = List.of("roy", "hoy", "poy");
        CardDeck cardDeck = CardDeck.generateCardDeck();
        List<Integer> moneys = List.of(1000, 100, 50);
        int expectedNumberOfPlayer = 3;

        Players players = Players.of(names, cardDeck, moneys);

        assertThat(players.getPlayers().size()).isEqualTo(expectedNumberOfPlayer);
    }

    @Test
    @DisplayName("플레이어 이름 배열이 입력될 경우 Players 객체가 정상적으로 생성된다.")
    void generatePlayers() {
        List<String> names = List.of("roy", "hoy", "poy");
        CardDeck cardDeck = CardDeck.generateCardDeck();
        List<Integer> moneys = List.of(1000, 100, 50);

        assertDoesNotThrow(() -> Players.of(names, cardDeck, moneys));
    }

    @Test
    @DisplayName("최대 플레이어 수가 4명을 초과할 경우 예외가 발생한다.")
    void exceptionMoreThanMaximumPlayers() {
        List<String> names = List.of("roy", "hoy", "poy", "joy", "koy");
        CardDeck cardDeck = CardDeck.generateCardDeck();
        List<Integer> moneys = List.of(1000, 100, 50, 10, 5);

        assertThatThrownBy(() -> Players.of(names, cardDeck, moneys))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 수는 4명을 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어 간 이름이 동일할 경우 예외가 발생한다.")
    void exceptionWhenPlayerNamesAreDuplicated() {

        List<String> names = List.of("roy", "roy", "poy");
        CardDeck cardDeck = CardDeck.generateCardDeck();
        List<Integer> moneys = List.of(1000, 100, 50);

        assertThatThrownBy(() -> Players.of(names, cardDeck, moneys))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 간 이름은 중복될 수 없습니다.");
    }
}
