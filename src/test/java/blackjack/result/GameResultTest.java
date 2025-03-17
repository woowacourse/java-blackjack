package blackjack.result;

import blackjack.card.Card;
import blackjack.card.CardFixture;
import blackjack.participant.Dealer;
import blackjack.participant.GameParticipantFixture;
import blackjack.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @ParameterizedTest
    @CsvSource({
            "17, 16, WIN",  // 플레이어가 이기는 경우
            "17, 22, WIN",  // 플레이어가 이기는 경우
            "19, 20, LOSE", // 딜러가 이기는 경우
            "22, 20, LOSE", // 딜러가 이기는 경우
            "17, 17, DRAW",  // 무승부
            "21, 21, DRAW",  // 무승부
            "22, 22, LOSE" // 둘 다 버스트라면 딜러가 이긴다
    })
    @DisplayName("플레이어의 승패를 판단할 수 있다")
    void canDecideResult(int heroSum, int villainSum, GameResult expectedResult) {
        // given
        Player player = GameParticipantFixture.createPlayer("강산");
        Dealer dealer = GameParticipantFixture.createDealer();

        List<Card> playerCards = CardFixture.createCardsForSum(heroSum).getCards();
        List<Card> dealerCards = CardFixture.createCardsForSum(villainSum).getCards();

        playerCards.forEach(player::drawCard);
        dealerCards.forEach(dealer::drawCard);

        // when
        GameResult result = GameResult.of(player.calculateSumOfCards(), dealer.calculateSumOfCards());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("게임 결과의 반대 결과를 반환할 수 있다")
    void canReturnOppositeGameResult() {
        // given
        GameResult win = GameResult.WIN;
        GameResult lose = GameResult.LOSE;
        GameResult draw = GameResult.DRAW;

        // when
        GameResult opposedWin = win.oppose();
        GameResult opposedLose = lose.oppose();
        GameResult opposedDraw = draw.oppose();

        // then
        assertThat(opposedWin).isEqualTo(GameResult.LOSE);
        assertThat(opposedLose).isEqualTo(GameResult.WIN);
        assertThat(opposedDraw).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("나열된 게임 결과들을 카운트할 수 있다")
    void canCountResults() {
        // given
        List<GameResult> results = List.of(
                GameResult.WIN,
                GameResult.WIN,
                GameResult.LOSE);

        // when
        Map<GameResult, Integer> resultToCounts = GameResult.count(results);

        // then
        assertThat(resultToCounts.get(GameResult.WIN)).isEqualTo(2);
        assertThat(resultToCounts.get(GameResult.LOSE)).isEqualTo(1);
        assertThat(resultToCounts.get(GameResult.DRAW)).isEqualTo(0);
    }
}
