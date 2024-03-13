package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackjackGameTest {

    @DisplayName("딜러와 플레이어들에게 초기 카드 2장을 분배한다.")
    @Test
    void dealInitCardsTest() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.create();
        Dealer dealer = Dealer.newInstance();
        Players players = new Players(List.of("a", "b"));
        Player player1 = players.getPlayers().get(0);
        Player player2 = players.getPlayers().get(1);

        // when
        blackjackGame.dealInitCards(dealer, players);

        // then
        assertAll(
                () -> assertThat(dealer.getCardHand())
                        .hasSize(2),
                () -> assertThat(player1.getCardHand())
                        .hasSize(2),
                () -> assertThat(player2.getCardHand())
                        .hasSize(2)
        );
    }

    @DisplayName("플레이어에게 카드 1장을 분배한다.")
    @Test
    void dealCardToPlayerTest() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.create();
        Player player = Player.newInstance("a");

        // when
        blackjackGame.hit(player);

        // then
        assertThat(player.getCardHand())
                .hasSize(1);
    }

    @DisplayName("딜러에게 카드 1장을 분배한다.")
    @Test
    void dealCardToDealerTest() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.create();
        Dealer dealer = Dealer.newInstance();

        // when
        blackjackGame.hit(dealer);

        // then
        assertThat(dealer.getCardHand())
                .hasSize(1);
    }
}
