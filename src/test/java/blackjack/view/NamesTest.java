package blackjack.view;

import blackjack.player.Gambler;
import blackjack.player.Player;
import blackjack.player.card.CardBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NamesTest {

    @DisplayName("Names 에서 Player 리스트 만들기")
    @Test
    void toPlayers() {
        //given
        String inputNames = "allen,bebop";
        List<Player> result = Arrays.asList(
                new Gambler(new CardBundle(), "allen"),
                new Gambler(new CardBundle(), "bebop")
        );

        //when
        Names names = new Names(inputNames);
        List<Player> players = names.toPlayers();

        //then
        assertThat(players).isEqualTo(result);
    }

    @DisplayName("Names 에 Null 혹은 비어있는 값 입력시 exception 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void toPlayersException(String inputNames) {
        assertThatThrownBy(() -> new Names(inputNames))
                .isInstanceOf(IllegalArgumentException.class);
    }
}