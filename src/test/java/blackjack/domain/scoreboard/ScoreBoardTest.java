package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
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
        User first = new User(Name.from("욘"));
        User second = new User(Name.from("웨지"));
        User third = new User(Name.from("포비"));
        User fourth = new User(Name.from("제이슨"));

        UserGameResult firstUserGameResult = new UserGameResult(
                Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.HEART, Value.ACE))
                ,first.getName(), WinOrLose.DRAW); //15

        UserGameResult secondUserGameResult = new UserGameResult(
                Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.TEN),
                        new Card(Suit.HEART, Value.ACE))
                , second.getName(), WinOrLose.WIN); //21

        UserGameResult thirdUserGameResult = new UserGameResult(
                Arrays.asList(new Card(Suit.CLOVER, Value.TWO), new Card(Suit.HEART, Value.TWO)),
                third.getName(),WinOrLose.LOSE);

        UserGameResult fourthUserGameResult = new UserGameResult(
                Arrays.asList(new Card(Suit.DIAMOND, Value.TWO), new Card(Suit.SPADE, Value.TWO)),
                fourth.getName(), WinOrLose.LOSE);

        Map<User, UserGameResult> temp = new HashMap<>();
        temp.put(first, firstUserGameResult);
        temp.put(second, secondUserGameResult);
        temp.put(third, thirdUserGameResult);
        temp.put(fourth, fourthUserGameResult);

        GameResult gameResult = new GameResult(
                Arrays.asList(
                        new Card(Suit.CLOVER, Value.JACK), new Card(Suit.DIAMOND, Value.FOUR),
                        new Card(Suit.HEART, Value.ACE)), Dealer.DEALER_NAME.getName()
        );

        ScoreBoard scoreBoard = new ScoreBoard(temp, gameResult);
        //when
        Map<WinOrLose, Long> dealersWinOrLoses = scoreBoard.dealerWinOrLoseCounts();
        //then
        assertThat(dealersWinOrLoses.get(WinOrLose.WIN)).isEqualTo(2L);
        assertThat(dealersWinOrLoses.get(WinOrLose.DRAW)).isEqualTo(1L);
        assertThat(dealersWinOrLoses.get(WinOrLose.LOSE)).isEqualTo(1L);
    }
}
