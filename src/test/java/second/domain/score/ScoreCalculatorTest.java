package second.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import second.domain.BlackJackGame;
import second.domain.card.Card;
import second.domain.card.HandCards;
import second.domain.card.Rank;
import second.domain.card.Suit;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreCalculatorTest {

    @Test
    @DisplayName("값 계산")
    void calculate() {
        HandCards handCards = new HandCards(parseNumbersToCards(Rank.ACE, Rank.Q));

        HandCards handCards2 = new HandCards(parseNumbersToCards(Rank.ACE, Rank.K, Rank.SIX));

        assertThat(ScoreCalculator.calculate(handCards).getValue()).isEqualTo(ScoreCalculator.BLACK_JACK_MAX_SCORE.getValue());
        assertThat(ScoreCalculator.calculate(handCards2).getValue()).isEqualTo(new Score(17).getValue());
    }

    private static List<Card> parseNumbersToCards(Rank... input) {
        return Arrays.stream(input)
                .map(i -> Card.of(i, Suit.CLOVER))
                .collect(toList());
    }
}
