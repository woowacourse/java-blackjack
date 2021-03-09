package blackjack.domain.dto;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.CardDistributor;
import blackjack.domain.CardDistributorForTest;
import blackjack.domain.ResultType;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Shape;
import blackjack.domain.names.Name;
import blackjack.domain.participants.Betting;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
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
                new HashMap<String, Integer>() {
                    {
                        put("pobi", 1000);
                        put("jason", 0);
                        put("root", -1000);
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
                new HashMap<String, Integer>() {
                    {
                        put("pobi", -1000);
                        put("jason", 1000);
                        put("root", 1000);
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
                new HashMap<String, Integer>() {
                    {
                        put("pobi", -1000);
                        put("jason", -1000);
                        put("root", -1000);
                    }
                }
            )
        );
    }

    @ParameterizedTest
    @DisplayName("게임 승패 테스트")
    @MethodSource("provideDeck")
    void gameResultTest(Deck deck, Map<ResultType, Integer> expected) {
        CardDistributor cardDistributor = new CardDistributor(deck);
        CardDistributorForTest cardDistributorForTest = CardDistributorForTest
            .valueOf(cardDistributor);
        Dealer dealer = new Dealer();
        cardDistributorForTest.distributeCardsTo(dealer, 3);
        Player pobi = new Player(new Name("pobi"), Betting.valueOf("1000"));
        cardDistributorForTest.distributeCardsTo(pobi, 3);
        Player jason = new Player(new Name("jason"), Betting.valueOf("1000"));
        cardDistributorForTest.distributeCardsTo(jason, 3);
        Player root = new Player(new Name("root"), Betting.valueOf("1000"));
        cardDistributorForTest.distributeCardsTo(root, 3);

        Players players = new Players(Arrays.asList(pobi, jason, root));
        GameResult gameResult = players.match(dealer);

        assertThat(gameResult.unwrap()).isEqualTo(expected);
    }
}
