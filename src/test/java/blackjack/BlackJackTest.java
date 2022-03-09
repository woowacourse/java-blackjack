package blackjack;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import blackjack.domain.BlackJack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Participant;

public class BlackJackTest {

    @Test
    @DisplayName("참가자의 수가 8명을 초과하면 예외처리")
    void Player_Number_Exceed_Exception() {
        assertThatThrownBy(() -> {
            List<String> players = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
            BlackJack.createFrom(players);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("y 또는 n이 아니면 예외처리")
    void Illegal_Choice_Format_Exception() {
        List<Participant> players = new ArrayList<>();
        players.add(Participant.createPlayer("panda"));
        BlackJack blackJack = BlackJack.createFrom(Arrays.asList("yaho", "panda"));
        assertThatThrownBy(() ->
            blackJack.receiveAdditionalCard(players.get(0), "123!")
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] y 또는 n 으로 입력해야 합니다.");
    }
}
