package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlayerTest {

    private static Stream<Arguments> provideCard() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Pattern.SPADE, CardNumber.TEN),
                                new Card(Pattern.SPADE, CardNumber.TEN),
                                new Card(Pattern.SPADE, CardNumber.TWO)
                        ),
                        false
                ),
                Arguments.of(
                        List.of(
                                new Card(Pattern.SPADE, CardNumber.TEN),
                                new Card(Pattern.SPADE, CardNumber.ACE)
                        ),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideCard")
    void 플레이어의_카드_합계가_21_이하인지_판정한다(List<Card> cards, boolean expected) {
        //given
        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        playerCards.addAll(cards);

        //when
        boolean actual = player.isUnderBurstBound();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 플레이어의_이름과_배팅_금액으로_플레이어를_생성한다() {
        //given
        String name = "pobi";
        int bettingAmount = 1000;

        //when
        Player player = new Player(name, bettingAmount);

        //then
        assertAll(
                () -> assertThat(player.getName()).isEqualTo(name),
                () -> assertThat(player.getBettingAmount()).isEqualTo(bettingAmount)
        );
    }

    private static Stream<Arguments> provideGameResultAndBettingAmount() {
        return Stream.of(
                Arguments.of(GameResult.BLACKJACK_WIN, 1000, 1500),
                Arguments.of(GameResult.WIN, 1000, 1000),
                Arguments.of(GameResult.LOSE, 1000, -1000),
                Arguments.of(GameResult.DRAW, 1000, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideGameResultAndBettingAmount")
    void 플레이어의_게임_결과에_따라_배팅_금액을_계산해야한다(GameResult gameResult, int bettingAmount, int expected) {
        //given
        String name = "pobi";
        Player player = new Player(name, bettingAmount);

        //when
        int actual = player.calculateBettingAmount(gameResult);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
