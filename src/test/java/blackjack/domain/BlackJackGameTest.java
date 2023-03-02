package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackJackGameTest {

    @DisplayName("딜러와 플레이어들에게 초기 카드를 2장씩 배분한다.")
    @Test
    void handOutInitCardsTo1() {
        // given
        BlackJackGame blackJackGame = new BlackJackGame("pobi,crong");

        // when
        blackJackGame.handOutInitCards();

        Players players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        int playerCardSize = players.getPlayers()
                .stream()
                .mapToInt(player -> player.getCards().size())
                .sum();
        int totalCardSize = dealer.getCards().size() + playerCardSize;

        // then
        assertThat(totalCardSize).isEqualTo(6);
    }
}
