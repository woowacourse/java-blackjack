package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    private final List<Card> winCards = createCards(
            Card.of(Rank.ACE, Suit.DIAMOND), Card.of(Rank.FOUR, Suit.CLOVER));
    private final List<Card> dealerCards = createCards(
            Card.of(Rank.FIVE, Suit.DIAMOND), Card.of(Rank.FOUR, Suit.CLOVER));
    private final List<Card> loseCards = createCards(
            Card.of(Rank.FOUR, Suit.SPADE), Card.of(Rank.THREE, Suit.CLOVER));
    private final List<Card> bustCards = createCards(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.CLOVER), Card.of(Rank.K, Suit.HEART));

    private final List<String> userNames = List.of("승리 플레이어", "무승부 플레이어", "패배 플레이어", "버스트 플레이어");

    @Test
    @DisplayName("BlackjackResult 객체 생성 시 플레이어들의 승패 저장 확인")
    void player_result_test() {
        BlackjackResult blackjackResult = blackjackResultWith();

        // 딜러 카드 합: 14
        // 플레이어들의 카드 힙: 15, 14, 7, 30
        // 결과: 승리, 무승부, 패배, 패배(버스트 -> 딜러의 값과 상관없음)
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
        BlackjackResult blackjackResult = blackjackResultWith();
        int dealerWinCount = blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.LOSE);
        int dealerDrawCount = blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.DRAW);
        int dealerLoseCount = blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.WIN);

        // 2승 1무 1패
        assertThat(dealerWinCount).isEqualTo(2);
        assertThat(dealerDrawCount).isEqualTo(1);
        assertThat(dealerLoseCount).isEqualTo(1);
    }

    private List<Card> createCards(Card... cards) {
        return List.of(cards);
    }

    private List<List<Card>> getPlayerCards() {
        return new ArrayList<>(List.of(
                winCards, // 카드 합: 15
                dealerCards, // 카드 합: 14
                loseCards, // 카드 합: 7
                bustCards// 카드 합: 30
        ));
    }

    private BlackjackResult blackjackResultWith() {
        Dealer dealer = Dealer.of(dealerCards);
        Players players = Players.of(userNames);
        List<List<Card>> playersCards = getPlayerCards();

        for (int i = 0; i < playersCards.size(); i++) {
            players.getPlayers().get(i).addInitialCards(playersCards.get(i));
        }

        return BlackjackResult.of(dealer, players);
    }

}