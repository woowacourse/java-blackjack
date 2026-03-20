package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    private final List<Card> winCards = List.of(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.HEART), Card.of(Rank.ACE, Suit.DIAMOND));
    private final List<Card> drawCards = List.of(
            Card.of(Rank.TEN, Suit.DIAMOND), Card.of(Rank.TEN, Suit.CLOVER));
    private final List<Card> loseCards = List.of(
            Card.of(Rank.TEN, Suit.HEART), Card.of(Rank.SEVEN, Suit.CLOVER));
    private final List<Card> bustCards = List.of(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.CLOVER), Card.of(Rank.K, Suit.HEART));

    private final List<String> userNames = List.of("승리 플레이어", "무승부 플레이어", "패배 플레이어", "버스트 플레이어");

    @Test
    @DisplayName("BlackjackResult 객체 생성 시 플레이어들의 승패 저장 확인")
    void player_result_test() {
        Dealer dealer = Dealer.of(drawCards);
        List<List<Card>> playersCards = getPlayerCards();
        BlackjackResult blackjackResult = blackjackResultWith(dealer, playersCards);

        assertThat(blackjackResult.getPlayersResult()).containsExactly(GameResult.WIN, GameResult.DRAW, GameResult.LOSE,
                GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어, 딜러 카드 버스트인 경우 무승부가 아닌 플레이어의 패배.")
    void player_dealer_isBust_player_lose() {
        Dealer dealer = Dealer.of(bustCards);
        Players players = Players.of(List.of("버스트 플레이어"));
        players.getPlayers().getFirst().addInitialCards(bustCards);
        BlackjackResult blackjackResult = BlackjackResult.of(dealer, players);

        assertThat(blackjackResult.getPlayersResult()).containsExactly(GameResult.LOSE);

    }

    @Test
    @DisplayName("플레이어 버스트 아니며 딜러 버스트 시 플레이어 승리 결과")
    void player_isNotBust_dealer_isBust_result_win() {
        Dealer dealer = Dealer.of(bustCards);
        Players players = Players.of(List.of("승리 플레이어"));

        players.getPlayers().getFirst().addInitialCards(winCards);
        BlackjackResult blackjackResult = BlackjackResult.of(dealer, players);

        assertThat(blackjackResult.getPlayersResult()).isEqualTo(List.of(GameResult.WIN));
    }

    @Test
    @DisplayName("딜러의 결과는 플레이어들 승/패 여부 횟수의 반대이다. -> 플레이어 승리 시 딜러는 패배")
    void dealer_result() {
        Dealer dealer = Dealer.of(drawCards);
        List<List<Card>> playersCards = getPlayerCards();
        BlackjackResult blackjackResult = blackjackResultWith(dealer, playersCards);

        int dealerWinCount = blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.LOSE);
        int dealerDrawCount = blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.DRAW);
        int dealerLoseCount = blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.WIN);

        // 2승 1무 1패
        assertThat(dealerWinCount).isEqualTo(2);
        assertThat(dealerDrawCount).isEqualTo(1);
        assertThat(dealerLoseCount).isEqualTo(1);
    }

    private List<List<Card>> getPlayerCards() {
        return new ArrayList<>(List.of(
                winCards, // 카드 합: 15
                drawCards, // 카드 합: 14
                loseCards, // 카드 합: 7
                bustCards// 카드 합: 30
        ));
    }

    private BlackjackResult blackjackResultWith(Dealer dealer, List<List<Card>> playersCards) {
        Players players = Players.of(userNames);

        for (int i = 0; i < playersCards.size(); i++) {
            players.getPlayers().get(i).addInitialCards(playersCards.get(i));
        }

        return BlackjackResult.of(dealer, players);
    }

}