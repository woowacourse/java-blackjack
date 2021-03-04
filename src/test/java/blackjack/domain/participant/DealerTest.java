package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardType;
import blackjack.domain.card.CardValue;
import blackjack.dto.DealerResultDto;
import blackjack.dto.ScoreResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DealerTest {

    @Test
    @DisplayName("카드를 받는다.")
    void test_receive_card() {
        Participant dealer = new Dealer(cards -> 0);
        Card card = new Card(CardType.DIAMOND, CardValue.TEN);
        dealer.receiveCard(card);
        assertThat(dealer.showCards().contains(card)).isTrue();
    }

    @Test
    @DisplayName("딜러는 한장의 카드만 보여준다.")
    void test_dealer_show_card() {
        //given
        Participant dealer = new Dealer(cards -> 0);
        dealer.receiveCard(new Card(CardType.DIAMOND, CardValue.TEN));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardValue.ACE));

        //when
        List<Card> cards = dealer.showInitCards();

        //then
        assertThat(cards).hasSize(1);
    }

    @ParameterizedTest
    @DisplayName("딜러가 카드를 한장을 더 뽑을 수 있는지 확인한다")
    @CsvSource(value = {
            "16:true", "17:false"
    }, delimiter = ':')
    void test_dealer_is_receive_card(int totalScore, boolean actual) {
        //given
        Participant dealer = new Dealer(cards -> totalScore);

        //when
        boolean isReceived = dealer.isReceiveCard();

        //then
        assertThat(isReceived).isEqualTo(actual);
    }

    @DisplayName("딜러가 플레이어들과 점수를 비교하여 승패를 가른다.")
    @Test
    void test_decide_win_or_lose() {
        //given
        Dealer dealer = new Dealer(cards -> 16);
        Player firstPlayer = new Player("roki", cards -> 15);
        Player secondPlayer = new Player("suri", cards -> 17);
        Player thirdPlayer = new Player("brown", cards -> 16);
        List<Player> players = Arrays.asList(firstPlayer, secondPlayer, thirdPlayer);

        //when
        List<ScoreResultDto> playerResults = dealer.decideWinOrLoseResults(players);

        //then
        assertAll(
                () -> assertThat(playerResults.get(0).getName()).isEqualTo("roki"),
                () -> assertThat(playerResults.get(0).getGameResult()).isEqualTo(GameResult.LOSE),
                () -> assertThat(playerResults.get(1).getName()).isEqualTo("suri"),
                () -> assertThat(playerResults.get(1).getGameResult()).isEqualTo(GameResult.WIN),
                () -> assertThat(playerResults.get(2).getName()).isEqualTo("brown"),
                () -> assertThat(playerResults.get(2).getGameResult()).isEqualTo(GameResult.DRAW)
        );
    }

    @DisplayName("딜러의 점수가 21점을 초과하 모든 플레이어는 승리한다")
    @Test
    void test_decide_win_or_lose_if_dealer_score_over() {
        //given
        Dealer dealer = new Dealer(cards -> 22);
        Player firstPlayer = new Player("roki", cards -> 0);
        Player secondPlayer = new Player("suri", cards -> 17);
        Player thirdPlayer = new Player("brown", cards -> 16);
        List<Player> players = Arrays.asList(firstPlayer, secondPlayer, thirdPlayer);

        //when
        List<ScoreResultDto> playerResults = dealer.decideWinOrLoseResults(players);

        //then
        assertAll(
                () -> assertThat(playerResults.get(0).getName()).isEqualTo("roki"),
                () -> assertThat(playerResults.get(0).getGameResult()).isEqualTo(GameResult.WIN),
                () -> assertThat(playerResults.get(1).getName()).isEqualTo("suri"),
                () -> assertThat(playerResults.get(1).getGameResult()).isEqualTo(GameResult.WIN),
                () -> assertThat(playerResults.get(2).getName()).isEqualTo("brown"),
                () -> assertThat(playerResults.get(2).getGameResult()).isEqualTo(GameResult.WIN)
        );
    }

    @DisplayName("점수가 21점을 초과한 플레이어는 패배한다")
    @Test
    void test_decide_win_or_lose_if_player_score_over() {
        //given
        Dealer dealer = new Dealer(cards -> 15);
        Player firstPlayer = new Player("roki", cards -> 22);
        Player secondPlayer = new Player("suri", cards -> 24);
        Player thirdPlayer = new Player("brown", cards -> 16);
        List<Player> players = Arrays.asList(firstPlayer, secondPlayer, thirdPlayer);

        //when
        List<ScoreResultDto> playerResults = dealer.decideWinOrLoseResults(players);

        //then
        assertAll(
                () -> assertThat(playerResults.get(0).getName()).isEqualTo("roki"),
                () -> assertThat(playerResults.get(0).getGameResult()).isEqualTo(GameResult.LOSE),
                () -> assertThat(playerResults.get(1).getName()).isEqualTo("suri"),
                () -> assertThat(playerResults.get(1).getGameResult()).isEqualTo(GameResult.LOSE),
                () -> assertThat(playerResults.get(2).getName()).isEqualTo("brown"),
                () -> assertThat(playerResults.get(2).getGameResult()).isEqualTo(GameResult.WIN)
        );
    }

    @DisplayName("Dealer의 승패 결과를 반환한다")
    @Test
    void test_get_dealer_result() {
        //given
        List<Player> players = Arrays.asList(
                new Player("pobi", cards -> 21),
                new Player("woni", cards -> 18),
                new Player("brown", cards -> 20)
        );
        Dealer dealer = new Dealer(cards -> 20);

        //when
        DealerResultDto dealerResult = dealer.getDealerResult(players);

        //then
        assertAll(
                () -> assertThat(dealerResult.getName()).isEqualTo(dealer.getName()),
                () -> assertThat(dealerResult.getResult().get(GameResult.WIN)).isEqualTo(1L),
                () -> assertThat(dealerResult.getResult().get(GameResult.LOSE)).isEqualTo(1L),
                () -> assertThat(dealerResult.getResult().get(GameResult.DRAW)).isEqualTo(1L)
        );
    }
}
