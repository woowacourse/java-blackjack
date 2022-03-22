package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersValidatorTest {
    @Test
    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다.")
    void validateDuplicationNames() {
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);
        Player player1 = new Player("범고래", List.of(card), 1000);
        Player player2 = new Player("범고래", List.of(card), 2000);
        Assertions.assertThatThrownBy(() -> {
                    PlayersValidator.validate(List.of(player1, player2));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }

    @Test
    @DisplayName("플레이어의 인원이 2명 미만일 경우, 예외가 발생한다.")
    void validatePlayerMinSize() {
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);
        Player player = new Player("범고래", List.of(card), 2000);
        Assertions.assertThatThrownBy(() -> {
                    PlayersValidator.validate(List.of(player));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어의 인원은 최소 2인 ~ 최대 8인 입니다.");
    }

    @Test
    @DisplayName("플레이어의 인원이 최소 2명 이상으로 생성한다.")
    void validatePlayerMinSizeSuccess() {
        // given
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);
        Player player1 = new Player("범고래1", List.of(card), 2000);
        Player player2 = new Player("범고래2", List.of(card), 2000);
        List<Player> players = List.of(player1, player2);
        PlayersValidator.validate(players);

        // when
        int value = players.size();

        // then
        assertThat(value).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어의 인원이 8명 초과일 경우, 예외가 발생한다.")
    void validatePlayerMaxSize() {
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);
        Player player1 = new Player("범고래1", List.of(card), 2000);
        Player player2 = new Player("범고래2", List.of(card), 2000);
        Player player3 = new Player("범고래3", List.of(card), 2000);
        Player player4 = new Player("범고래4", List.of(card), 2000);
        Player player5 = new Player("범고래5", List.of(card), 2000);
        Player player6 = new Player("범고래6", List.of(card), 2000);
        Player player7 = new Player("범고래7", List.of(card), 2000);
        Player player8 = new Player("범고래8", List.of(card), 2000);
        Player player9 = new Player("범고래9", List.of(card), 2000);
        Assertions.assertThatThrownBy(() -> {
                    PlayersValidator.validate(List.of(player1, player2, player3, player4, player5, player6, player7, player8, player9));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어의 인원은 최소 2인 ~ 최대 8인 입니다.");
    }

    @Test
    @DisplayName("플레이어의 인원이 최대 8명까지 생성할 수 있다.")
    void validatePlayerMaxSizeSuccess() {
        // given
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);
        Player player1 = new Player("범고래1", List.of(card), 2000);
        Player player2 = new Player("범고래2", List.of(card), 2000);
        Player player3 = new Player("범고래3", List.of(card), 2000);
        Player player4 = new Player("범고래4", List.of(card), 2000);
        Player player5 = new Player("범고래5", List.of(card), 2000);
        Player player6 = new Player("범고래6", List.of(card), 2000);
        Player player7 = new Player("범고래7", List.of(card), 2000);
        Player player8 = new Player("범고래8", List.of(card), 2000);
        List<Player> players = List.of(player1, player2, player3, player4, player5, player6, player7, player8);
        PlayersValidator.validate(players);

        // when
        int value = players.size();

        // then
        assertThat(value).isEqualTo(8);
    }
}