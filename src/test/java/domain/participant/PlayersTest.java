package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayersTest {

    @Test
    @DisplayName("입력 받은 플레이어 이름만큼 Player 객체가 잘 만들어졌는지 확인한다.")
    void playersCreateTest() {
        String names = "pobi,jason";
        Players players = Players.of(names);

        assertThat(players.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("입력 받은 플레이어 이름이 쉼표만 있을 경우 에러를 발생시킨다.")
    void playersCommaErrorTest() {
        String names = "pobi,,,jason";

        assertThatThrownBy(()-> Players.of(names))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("첫 턴에서 모든 참가자가 카드를 두개씩 뽑는지 확인")
    void initialTurnTest(){
        Players players = Players.of("runa, kun");

        players.runInitialTurn();
        int actual = (int)players.getPlayers().stream()
            .filter(participant -> participant.getCards().size() == 2)
            .count();

        assertThat(actual).isEqualTo(2);
    }
}