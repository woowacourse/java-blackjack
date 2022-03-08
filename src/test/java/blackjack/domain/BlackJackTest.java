package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    @Test
    @DisplayName("카드를 2장씩 나눈다.")
    void splitCard() {
        Player player1 = new Player("페퍼");
        Player player2 = new Player("애쉬");
        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(player1, player2));
        BlackJack blackJack = new BlackJack(playerGroup);
        blackJack.divideCards();

        assertThat(player1.getCardsSize()).isEqualTo(2);
        assertThat(player2.getCardsSize()).isEqualTo(2);
        assertThat(blackJack.getDealer().getCardsSize()).isEqualTo(2);
    }
}
