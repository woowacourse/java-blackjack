package domain;

import domain.participants.Player;
import domain.participants.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("플레이어들 객체를 생성한다.")
    void createPlayersTest() {
        Player player1 = new Player("pobi",new BettingMoney(0));
        Player player2 = new Player("jason",new BettingMoney(0));

        Assertions.assertDoesNotThrow(() -> new Players(List.of(player1, player2)));
    }

    @Test
    @DisplayName("중복된 이름인경우 예외가 발생한다.")
    void validateDuplicatedNameTest() {
        Player player1 = new Player("pobi",new BettingMoney(0));
        Player player2 = new Player("pobi",new BettingMoney(0));

        assertThatThrownBy(() ->new Players(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름입니다.");
    }

}
