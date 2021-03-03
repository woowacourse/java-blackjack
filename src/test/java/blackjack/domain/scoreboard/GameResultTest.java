package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @DisplayName("결과로 갖고 있는 카드목록의 점수를 구하는 테스트")
    @Test
    void testCalculateScore() {
        //given
        List<Card> resultCards = Arrays.asList(
                new Card(Suit.SPADE, Value.EIGHT), new Card(Suit.HEART, Value.FIVE),
                new Card(Suit.CLOVER, Value.SEVEN)
        );

        //when
        GameResult gameResult = new GameResult(resultCards, WinOrLose.WIN);

        //then
        assertThat(gameResult.calculateScore()).isEqualTo(20);
    }
}