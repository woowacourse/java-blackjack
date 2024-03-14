package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomCardGenerator;

class BlackjackGameTest {

    @Test
    @DisplayName("성공: 모든 참가자에게 시작 카드를 분배")
    void distributeStartingCards() {
        Players players = Players.withNames(List.of("name1", "name2"));
        BlackjackGame game = BlackjackGame.of(players, new RandomCardGenerator());
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);
        Dealer dealer = game.getDealer();

        game.distributeStartingCards();

        assertThat(player1.getCards()).hasSize(2);
        assertThat(player2.getCards()).hasSize(2);
        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("성공: 플레이어에게 카드 한장 주기")
    void giveOneCardTo_Player() {
        BlackjackGame game = BlackjackGame.of(Players.withNames(List.of("name")), new RandomCardGenerator());
        Player player = game.getPlayers().get(0);
        game.giveOneCardTo(player);

        assertThat(player.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("성공: 딜러에게 카드 한장 주기")
    void giveOneCardTo_Dealer() {
        BlackjackGame game = BlackjackGame.of(Players.withNames(List.of("name")), new RandomCardGenerator());
        Dealer dealer = game.getDealer();
        game.giveOneCardTo(dealer);

        assertThat(dealer.getCards()).hasSize(1);
    }
}
