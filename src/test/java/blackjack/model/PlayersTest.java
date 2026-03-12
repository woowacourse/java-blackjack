package blackjack.model;

import blackjack.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("플레이어는 2명 이상 5명이어야 한다")
    void createPlayersTest() {
        // given
        Player playerOne = new Player("luke", 1000);
        Player playerTwo = new Player("usher", 1000);

        // when
        Players players = new Players(List.of(playerOne, playerTwo));
        List<Player> players1 = players.getPlayers();

        // then
        assertThat(players1.get(0).getName()).isEqualTo("luke");
        assertThat(players1.get(1).getName()).isEqualTo("usher");
    }

    @Test
    @DisplayName("플레이어는 2명 이상 5명 이하가 아닌 경우 예외를 발생한다")
    void createPlayersErrorTest() {
        // given
        Player playerOne = new Player("luke", 1000);

        // when & then
        assertThatThrownBy(() -> new Players(List.of(playerOne)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_PLAYERS.getMessage());
    }

    @Test
    @DisplayName("카드를 2장을 나눠준다.")
    void giveTwoCardsTest() {
        // given
        Deck deck = new Deck();
        Player luke = new Player("luke", 1000);
        Player sm = new Player("sm", 1000);
        Players players = new Players(List.of(luke, sm));
        // when
        players.giveTwoCards(deck);
        // then
        assertThat(luke.getHandCards().size()).isEqualTo(2);
        assertThat(sm.getHandCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("모든 플레이어의 게임 결과를 심판이 판정하여 Map으로 반환한다.")
    void judgeAllTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Figure.SPADE, Number.TEN));
        dealer.receiveCard(new Card(Figure.HEART, Number.TEN));

        Player luke = new Player("luke", 1000);
        luke.receiveCard(new Card(Figure.DIAMOND, Number.ACE));
        luke.receiveCard(new Card(Figure.CLOVER, Number.TEN));

        Player sm = new Player("sm", 1000);
        sm.receiveCard(new Card(Figure.HEART, Number.TWO));
        sm.receiveCard(new Card(Figure.SPADE, Number.EIGHT));

        Players players = new Players(List.of(luke, sm));
        Referee referee = new Referee();
        // when
        Map<Player, GameResult> results = players.judgeAll(dealer, referee);
        // then
        assertThat(results).hasSize(2);
        assertThat(results.get(luke)).isEqualTo(GameResult.BLACKJACK);
        assertThat(results.get(sm)).isEqualTo(GameResult.LOSE);
    }
}
