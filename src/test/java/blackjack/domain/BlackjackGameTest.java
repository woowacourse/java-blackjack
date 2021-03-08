package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackGameTest {
    @Test
    @DisplayName("블랙잭 게임을 관리하는 객체 생성 - 플레이어 이름을 받아 세팅")
    void createBlackjackGame() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");

        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        assertThat(blackjackGame).isInstanceOf(BlackjackGame.class);
    }

    @Test
    @DisplayName("초기 카드를 나눠준다. - 2장인지 확인한다.")
    void handOutInitialCards() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        BlackjackGame blackjackGame = BlackjackGame.generateByUser(names);
        blackjackGame.handOutInitialCards();

        assertThat(blackjackGame.getDealer().getCards().cards().size()).isEqualTo(2);
        blackjackGame.getPlayers()
                .players()
                .forEach(player -> assertThat(player.getCards().cards().size())
                        .isEqualTo(2));
    }
}
