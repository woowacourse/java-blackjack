package domains.user;

import domains.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @DisplayName("참가자들간 중복된 이름이 있다면 예외처리")
    @Test
    void checkDuplicationName_HasDuplication_ExceptionThrown() {
        assertThatThrownBy(() -> new Players("a,b,c, c", new Deck()))
                .isInstanceOf(InvalidPlayersException.class)
                .hasMessage(InvalidPlayersException.DUPLICATION);
    }

    @DisplayName("참가자가 5명 초과 할 경우 예외처리")
    @Test
    void checkNumberOfPlayers_OverFivePlayers_ExceptionThrown() {
        assertThatThrownBy(() -> new Players("a,b,c,d,e,f", new Deck()))
                .isInstanceOf(InvalidPlayersException.class)
                .hasMessage(InvalidPlayersException.OVER_NUMBER_OF_PLAYERS);
    }
}
