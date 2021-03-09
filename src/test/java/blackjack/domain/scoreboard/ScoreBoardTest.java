package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import blackjack.domain.scoreboard.result.GameResult;
import blackjack.domain.scoreboard.result.UserGameResult;
import blackjack.domain.user.Dealer;
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
        User first = new User("욘", 1000);
        User second = new User("웨지", 1000);
        User third = new User("포비", 1000);
        User fourth = new User("제이슨", 1000);

        UserGameResult firstUserGameResult = new UserGameResult(
                new Cards(
                        Arrays.asList(
                                new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.FOUR),
                                new Card(Suit.HEART, Symbol.ACE))
                )
                , first.getName().toString(), WinOrLose.DRAW, 1000); //15

        UserGameResult secondUserGameResult = new UserGameResult(
                new Cards(
                        Arrays.asList(
                                new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.TEN),
                                new Card(Suit.HEART, Symbol.ACE))
                )
                , second.getName().toString(), WinOrLose.WIN, 2500); //21

        UserGameResult thirdUserGameResult = new UserGameResult(
                new Cards(
                        Arrays.asList(new Card(Suit.CLOVER, Symbol.TWO), new Card(Suit.HEART, Symbol.TWO))
                ),
                third.getName().toString(), WinOrLose.LOSE, 0);

        UserGameResult fourthUserGameResult = new UserGameResult(
                new Cards(
                        Arrays.asList(new Card(Suit.DIAMOND, Symbol.TWO), new Card(Suit.SPADE, Symbol.TWO))
                ),
                fourth.getName().toString(), WinOrLose.LOSE, 0);

        Map<User, UserGameResult> temp = new HashMap<>();
        temp.put(first, firstUserGameResult);
        temp.put(second, secondUserGameResult);
        temp.put(third, thirdUserGameResult);
        temp.put(fourth, fourthUserGameResult);

        GameResult gameResult = new GameResult(
                new Cards(
                        Arrays.asList(
                                new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.FOUR),
                                new Card(Suit.HEART, Symbol.ACE))
                ), Dealer.DEALER_NAME
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
