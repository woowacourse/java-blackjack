package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.GameResult;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GameResultTest {

    private static Stream<Arguments> provideDeck() {
        return Stream.of(
            Arguments.of(new Deck(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.TWO),
                Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.EIGHT),
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.HEART, CardValue.EIGHT),
                Card.valueOf(Shape.HEART, CardValue.TWO),
                Card.valueOf(Shape.SPADE, CardValue.TEN),
                Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
                Card.valueOf(Shape.CLOVER, CardValue.TWO),
                Card.valueOf(Shape.CLOVER, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.SEVEN),
                Card.valueOf(Shape.HEART, CardValue.TWO))),
                new HashMap<ResultType, Integer>() {
                    {
                        put(ResultType.WIN, 1);
                        put(ResultType.TIE, 1);
                        put(ResultType.LOSE, 1);
                    }
                }
            ),
            Arguments.of(new Deck(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.FOUR),
                Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.EIGHT),
                Card.valueOf(Shape.DIAMOND, CardValue.EIGHT),
                Card.valueOf(Shape.HEART, CardValue.EIGHT),
                Card.valueOf(Shape.HEART, CardValue.SIX),
                Card.valueOf(Shape.SPADE, CardValue.TEN),
                Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
                Card.valueOf(Shape.CLOVER, CardValue.TWO),
                Card.valueOf(Shape.CLOVER, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.SEVEN),
                Card.valueOf(Shape.HEART, CardValue.TWO))),
                new HashMap<ResultType, Integer>() {
                    {
                        put(ResultType.WIN, 2);
                        put(ResultType.LOSE, 1);
                    }
                }
            ),
            Arguments.of(new Deck(Arrays.asList(
                Card.valueOf(Shape.SPADE, CardValue.TWO),
                Card.valueOf(Shape.DIAMOND, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.EIGHT),
                Card.valueOf(Shape.DIAMOND, CardValue.EIGHT),
                Card.valueOf(Shape.HEART, CardValue.EIGHT),
                Card.valueOf(Shape.HEART, CardValue.NINE),
                Card.valueOf(Shape.SPADE, CardValue.TEN),
                Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
                Card.valueOf(Shape.CLOVER, CardValue.NINE),
                Card.valueOf(Shape.CLOVER, CardValue.TEN),
                Card.valueOf(Shape.SPADE, CardValue.SEVEN),
                Card.valueOf(Shape.DIAMOND, CardValue.NINE))),
                new HashMap<ResultType, Integer>() {
                    {
                        put(ResultType.LOSE, 3);
                    }
                }
            )
        );
    }

    @ParameterizedTest
    @DisplayName("게임 승패 테스트")
    @MethodSource("provideDeck")
    void gameResultTest(Deck deck, Map<ResultType, Integer> expected) {
        Dealer dealer = new Dealer(deck);
        dealer.drawCard(deck);
        Player pobi = new Player("pobi", deck);
        pobi.drawCard(deck);
        Player jason = new Player("jason", deck);
        jason.drawCard(deck);
        Player root = new Player("root", deck);
        root.drawCard(deck);

        Players players = new Players(Arrays.asList(pobi, jason, root));
        GameResult gameResult = players.match(dealer);

        assertThat(gameResult.getStatistics()).isEqualTo(expected);
    }
}
