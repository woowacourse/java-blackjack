package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import blackjack.domain.scoreboard.result.UserGameResult;
import blackjack.domain.user.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserGameResultTest {
    @DisplayName("결과로 갖고 있는 카드목록의 점수를 구하는 테스트")
    @Test
    void testCalculateScore() {
        //given
        List<Card> resultCards = Arrays.asList(
                new Card(Suit.SPADE, Symbol.EIGHT), new Card(Suit.HEART, Symbol.FIVE),
                new Card(Suit.CLOVER, Symbol.SEVEN)
        );

        //when
        UserGameResult userGameResult = new UserGameResult(resultCards, new Name("유저"), WinOrLose.WIN);

        //then
        assertThat(userGameResult.getScore()).isEqualTo(20);
    }
}