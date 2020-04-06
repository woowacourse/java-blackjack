package domain.result;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.gamer.Hand;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.result.score.Score;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackRuleTest {
    static Stream<Arguments> scoreTestParams() {
        return Stream.of(
                Arguments.of("ACE 점수 11점일 때 확인",
                        Arrays.asList(Card.of(Rank.ACE, Suit.CLOVER)),
                        Score.of(11)),
                Arguments.of("블랙잭 점수 확인",
                        Arrays.asList(Card.of(Rank.ACE, Suit.CLOVER), Card.of(Rank.QUEEN, Suit.CLOVER)),
                        Score.of(21)),
                Arguments.of("ACE 점수 1점일 때 확인",
                        Arrays.asList(Card.of(Rank.ACE, Suit.CLOVER), Card.of(Rank.QUEEN, Suit.CLOVER), Card.of(Rank.THREE, Suit.CLOVER)),
                        Score.of(14))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("scoreTestParams")
    void 점수_계산_테스트(String message, List<Card> cards, Score expectedScore) {
        BlackJackRule blackJackRule = new BlackJackRule();

        Name name = new Name("phobi");
        Hand hand = new Hand();

        for (Card card : cards) {
            hand.drawCard(() -> card);
        }
        assertThat(blackJackRule.calculateScore(new Player(name, hand))).isEqualTo(expectedScore);
    }
}
