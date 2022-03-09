package blackJack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

class BlackJackGameTest {

    @Test
    @DisplayName("Participants 생성 테스트")
    void createValidDealer() {
        assertThat(new BlackJackGame(new Dealer(), List.of(new Player("rookie")))).isNotNull();
    }

    @Test
    @DisplayName("Player들의 이름이 중복된 경우 테스트")
    void checkDuplicatePlayerName() {
        List<Player> players = List.of(new Player("rookie"), new Player("rookie"));

        assertThatThrownBy(() -> new BlackJackGame(new Dealer(), players))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("Player의 수가 1-7명이 아닌 경우 테스트")
    void checkPlayerCount() {
        List<Player> players = List.of(
            new Player("k1"), new Player("k2"), new Player("k3"), new Player("k4"),
            new Player("k5"), new Player("k6"), new Player("k7"), new Player("k8"));

        assertThatThrownBy(() -> new BlackJackGame(new Dealer(), players))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어의 인원수는 1명 이상 7명 이하여야 합니다.");
    }

    @Test
    @DisplayName("게임의 결과를 계산해주는 기능 테스트")
    void calculateGameResult() {
        Player player1 = new Player("kei");
        Player player2 = new Player("rookie");
        Dealer dealer = new Dealer();

        player1.receiveCard(new Card(Symbol.SPADE, Denomination.EIGHT));
        player2.receiveCard(new Card(Symbol.SPADE, Denomination.J));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.NINE));

        BlackJackGame participants = new BlackJackGame(dealer, List.of(player1, player2));

        assertThat(participants.calculateGameResult().values()).containsExactly(
            WinOrLose.LOSE, WinOrLose.WIN
        );
    }
}
