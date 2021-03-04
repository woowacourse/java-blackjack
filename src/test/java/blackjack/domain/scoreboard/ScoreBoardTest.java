package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardTest {
    @DisplayName("딜러의 승무패를 구하는 기능 테스트")
    @Test
    void testDealersWinOrLoses() {
        //given
        User first = new User("욘");
        User second = new User("웨지");
        User third = new User("포비");
        User fourth = new User("제이슨");

        GameResult firstGameResult = new GameResult(
                Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.HEART, Value.ACE))
                , WinOrLose.DRAW); //15

        GameResult secondGameResult = new GameResult(
                Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.TEN),
                        new Card(Suit.HEART, Value.ACE))
                , WinOrLose.WIN); //21

        GameResult thirdGameResult = new GameResult(
                Arrays.asList(new Card(Suit.CLOVER, Value.TWO), new Card(Suit.HEART, Value.TWO)),
                WinOrLose.LOSE);

        GameResult fourthGameResult = new GameResult(
                Arrays.asList(new Card(Suit.DIAMOND, Value.TWO), new Card(Suit.SPADE, Value.TWO)),
                WinOrLose.LOSE);

        Map<User, GameResult> temp = new HashMap<>();
        temp.put(first, firstGameResult);
        temp.put(second, secondGameResult);
        temp.put(third, thirdGameResult);
        temp.put(fourth, fourthGameResult);

        DealerGameResult dealerGameResult = new DealerGameResult(
                Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.HEART, Value.ACE))
        );

        ScoreBoard scoreBoard = new ScoreBoard(temp, dealerGameResult);
        //when
        Map<WinOrLose, Long> dealersWinOrLoses = scoreBoard.dealerWinOrLoseCounts();
        //then
        assertThat(dealersWinOrLoses.get(WinOrLose.WIN)).isEqualTo(2L);
        assertThat(dealersWinOrLoses.get(WinOrLose.DRAW)).isEqualTo(1L);
        assertThat(dealersWinOrLoses.get(WinOrLose.LOSE)).isEqualTo(1L);
    }
}
