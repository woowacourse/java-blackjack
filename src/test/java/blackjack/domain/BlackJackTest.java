package blackjack.domain;

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
            List<String> playerNames = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
            BlackJack.createFrom(playerNames);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.");
    }

	@Test
	@DisplayName("스타팅 카드를 두 장씩 배분하는지")
	void Hand_Out_Two_Starting_Cards(){
		List<String> playerNames = Arrays.asList("a", "b");
		BlackJack blackJack = BlackJack.createFrom(playerNames);
	}
}
