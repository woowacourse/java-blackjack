package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @ParameterizedTest(name = "[{index}] cards {0}, totalScore {1}")
    @MethodSource("generateCalculateTotalScoreBasicCardArguments")
    @DisplayName("카드에 대한 점수 계산한다.")
    void calculateTotalScoreBasicCard(List<Card> input, int totalScore) {
        Cards cards = new Cards(input);

        assertThat(cards.calculateTotalScore()).isEqualTo(totalScore);
    }

    static Stream<Arguments> generateCalculateTotalScoreBasicCardArguments() {
        return Stream.of(
                Arguments.of(List.of(new Card(Denomination.THREE, Suit.CLOVER),
                        new Card(Denomination.EIGHT, Suit.HEART)), 11),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.EIGHT, Suit.HEART)), 19),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.ACE, Suit.HEART)), 12),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.ACE, Suit.HEART),
                        new Card(Denomination.KING, Suit.HEART)), 12),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.JACK, Suit.HEART)), 21),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.FIVE, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.SPADE)), 16));
    }

    @Test
    @DisplayName("카드를 추가한다.")
    void addCard() {
        Cards cards = new Cards(
                List.of(new Card(Denomination.QUEEN, Suit.CLOVER), new Card(Denomination.FIVE, Suit.SPADE)));

        cards.addCard(new Card(Denomination.SIX, Suit.HEART));

        assertThat(cards.calculateTotalScore()).isEqualTo(21);
    }
}
