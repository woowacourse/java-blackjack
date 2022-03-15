package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("BlackjackGame이 생성되는지 확인한다.")
    void construct() {
        List<String> names = List.of("리버", "포키", "크리스");
        BlackjackGame blackjackGame = new BlackjackGame(names);

        assertThat(blackjackGame).isInstanceOf(BlackjackGame.class);
    }

    @DisplayName("참가자들 모두가 카드를 두장 뽑으면 새로운 BlackjackGame을 반환한다.")
    @Test
    void start_new_Blackjackgame() {
        BlackjackGame blackjackGame = new BlackjackGame(List.of("리버", "포키", "크리스"));
        BlackjackGame otherBlackjackGame = blackjackGame.start();

        assertThat(blackjackGame).isNotEqualTo(otherBlackjackGame);
    }
}