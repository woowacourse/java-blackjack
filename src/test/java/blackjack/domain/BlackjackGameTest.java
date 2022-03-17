package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

class BlackjackGameTest {

    private final Deck deck = Deck.create();
    private final BlackjackGame blackjackGame = new BlackjackGame(deck);

    @Test
    @DisplayName("블랙잭 게임 시작할 때 딜러와 참가자가 각각 두 장의 카드를 받는지 확인")
    void initTest() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("짱구", "짱아"));
        blackjackGame.initStartingCards(dealer, players);

        assertThat(dealer.getCards()).hasSize(2);
        for (Player player : players.getPlayers()) {
            assertThat(player.getCards()).hasSize(2);
        }
    }
}