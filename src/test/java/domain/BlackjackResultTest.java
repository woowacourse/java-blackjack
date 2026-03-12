package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    private final List<Card> winScore = createCards(
            Card.of(Rank.ACE, Suit.DIAMOND), Card.of(Rank.FOUR, Suit.CLOVER));
    private final List<Card> middleScore = createCards(
            Card.of(Rank.FIVE, Suit.DIAMOND), Card.of(Rank.FOUR, Suit.CLOVER));
    private final List<Card> loseScore = createCards(
            Card.of(Rank.FOUR, Suit.SPADE), Card.of(Rank.THREE, Suit.CLOVER));
    private final List<Card> bustScore = createCards(
            Card.of(Rank.J, Suit.SPADE), Card.of(Rank.Q, Suit.CLOVER), Card.of(Rank.K, Suit.HEART));

    private final List<String> userNames = List.of("pobi");

    @Test
    @DisplayName("BlackjackResult 객체 생성 시 플레이어들의 승패 저장 확인")
    void player_result_test() {
        BlackjackResult blackjackResult = blackjackResultWith(winScore);

        assertThat(blackjackResult.getPlayersResult()).hasSize(userNames.size());
    }

    @Test
    @DisplayName("플레이어 승리 결과")
    void player_result_win() {
        BlackjackResult blackjackResult = blackjackResultWith(winScore);

        assertThat(blackjackResult.getPlayersResult()).isEqualTo(List.of(GameResult.WIN));
    }

    @Test
    @DisplayName("플레이어 패배 결과")
    void player_result_lose() {
        BlackjackResult blackjackResult = blackjackResultWith(loseScore);

        assertThat(blackjackResult.getPlayersResult()).isEqualTo(List.of(GameResult.LOSE));
    }

    @Test
    @DisplayName("플레이어 버스트 시 패배 결과")
    void player_isBust_result_lose() {
        BlackjackResult blackjackResult = blackjackResultWith(bustScore);

        assertThat(blackjackResult.getPlayersResult()).isEqualTo(List.of(GameResult.LOSE));
    }

    @Test
    @DisplayName("플레이어 버스트 아니며 딜러 버스트 시 플레이어 승리 결과")
    void player_isNotBust_dealer_isBust_result_win() {
        Dealer dealer = Dealer.of(bustScore);
        Players players = Players.of(userNames);

        players.getPlayers().getFirst().addInitialCards(winScore);
        BlackjackResult blackjackResult = BlackjackResult.of(dealer, players);

        assertThat(blackjackResult.getPlayersResult()).isEqualTo(List.of(GameResult.WIN));
    }

    @Test
    @DisplayName("플레이어 무승부 결과")
    void player_isBust_result_draw() {
        BlackjackResult blackjackResult = blackjackResultWith(middleScore);

        assertThat(blackjackResult.getPlayersResult()).isEqualTo(List.of(GameResult.DRAW));
    }

    @Test
    @DisplayName("딜러의 결과는 플레이어들 승/패 여부 횟수의 반대이다. -> 플레이어 승리 시 딜러는 패배")
    void dealer_result() {
        BlackjackResult blackjackResult = blackjackResultWith(winScore);

        assertThat(blackjackResult.countDealerWinOrLoseReversePlayerResult(GameResult.WIN)).isEqualTo(1);
    }

    private List<Card> createCards(Card... cards) {
        return List.of(cards);
    }

    private BlackjackResult blackjackResultWith(List<Card> cards) {
        Dealer dealer = Dealer.of(middleScore);
        Players players = Players.of(userNames);

        players.getPlayers().getFirst().addInitialCards(cards);

        return BlackjackResult.of(dealer, players);
    }

}