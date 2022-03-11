package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackControllerTest extends BlackJackController {

    @Test
    @DisplayName("딜러의 점수가 플레이어보다 높을 때 딜러의 승이다")
    void dealer_win_when_dealer_score_high() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Denomination.QUEEN, Suit.HEART));
        dealer.receiveCard(new Card(Denomination.TEN, Suit.SPADE));

        Player player = new Player("User A");
        player.receiveCard(new Card(Denomination.JACK, Suit.CLOVER));
        player.receiveCard(new Card(Denomination.NINE, Suit.DIAMOND));

        // when
        decideGameScore(dealer, List.of(player));
        EnumMap<MatchResult, Integer> dealerResultScores = dealer.getResultScores();
        MatchResult playerMatchResult = player.getResult();

        // then
        assertThat(dealerResultScores.get(MatchResult.WIN)).isEqualTo(1);
        assertThat(playerMatchResult).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 높을 때 플레이어의 승이다")
    void player_win_when_player_score_high() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Denomination.JACK, Suit.HEART));
        dealer.receiveCard(new Card(Denomination.NINE, Suit.SPADE));

        Player player = new Player("User A");
        player.receiveCard(new Card(Denomination.QUEEN, Suit.CLOVER));
        player.receiveCard(new Card(Denomination.TEN, Suit.DIAMOND));

        // when
        decideGameScore(dealer, List.of(player));
        EnumMap<MatchResult, Integer> dealerResultScores = dealer.getResultScores();
        MatchResult playerMatchResult = player.getResult();

        // then
        assertThat(dealerResultScores.get(MatchResult.LOSE)).isEqualTo(1);
        assertThat(playerMatchResult).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트이고 플레이어는 아닐 때 플레이어의 승이다")
    void player_win_when_dealer_bust_player_not_bust() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Denomination.QUEEN, Suit.HEART));
        dealer.receiveCard(new Card(Denomination.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Denomination.TWO, Suit.CLOVER));

        Player player = new Player("User A");
        player.receiveCard(new Card(Denomination.JACK, Suit.DIAMOND));
        player.receiveCard(new Card(Denomination.NINE, Suit.HEART));

        // when
        decideGameScore(dealer, List.of(player));
        EnumMap<MatchResult, Integer> dealerResultScores = dealer.getResultScores();
        MatchResult playerMatchResult = player.getResult();

        // then
        assertAll(
                () -> assertThat(dealerResultScores.get(MatchResult.LOSE)).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(MatchResult.WIN)
        );
    }

    @Test
    @DisplayName("플레이어가 버스트이고 딜러는 아닐 때 딜러의 승이다")
    void dealer_win_when_player_bust_dealer_not_bust() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Denomination.JACK, Suit.HEART));
        dealer.receiveCard(new Card(Denomination.NINE, Suit.SPADE));

        Player player = new Player("User A");
        player.receiveCard(new Card(Denomination.QUEEN, Suit.DIAMOND));
        player.receiveCard(new Card(Denomination.TEN, Suit.HEART));
        player.receiveCard(new Card(Denomination.TWO, Suit.HEART));

        // when
        decideGameScore(dealer, List.of(player));
        EnumMap<MatchResult, Integer> dealerResultScores = dealer.getResultScores();
        MatchResult playerMatchResult = player.getResult();

        // then
        assertAll(
                () -> assertThat(dealerResultScores.get(MatchResult.WIN)).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(MatchResult.LOSE)
        );
    }
}
