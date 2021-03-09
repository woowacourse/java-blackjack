package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    private static Stream<Arguments> getCardOverMaximum() {
        return Stream.of(Arguments.of(Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER),
            new Card(Symbol.JACK, Shape.DIAMOND),
            new Card(Symbol.FIVE, Shape.HEART)), 16),
            Arguments.of(Arrays.asList(new Card(Symbol.EIGHT, Shape.CLOVER),
                new Card(Symbol.NINE, Shape.CLOVER), new Card(Symbol.ACE, Shape.CLOVER)), 18),
            Arguments.of(Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER),
                new Card(Symbol.NINE, Shape.CLOVER), new Card(Symbol.EIGHT, Shape.CLOVER)), 18));
    }

    @DisplayName("카드의 점수를 확인한다")
    @Test
    void calculateScore() {
        List<Card> cardList = Arrays.asList(new Card(Symbol.EIGHT, Shape.CLOVER),
            new Card(Symbol.QUEEN, Shape.DIAMOND));
        Cards cards = new Cards(cardList);
        int score = cards.calculateScore();
        assertThat(score).isEqualTo(18);
    }

    @DisplayName("ACE를 11로 적용해서 계산했을 때 21을 넘지 않으면 11로 환산한다.")
    @Test
    void calculateScoreWhenAceIsUnder() {
        List<Card> cardList = Arrays.asList(new Card(Symbol.ACE, Shape.CLOVER),
            new Card(Symbol.JACK, Shape.DIAMOND));
        Cards cards = new Cards(cardList);
        int score = cards.calculateScore();
        assertThat(score).isEqualTo(21);
    }

    @DisplayName("ACE를 11로 적용해서 계산했을 때 21을 넘면 1로 환산한다.")
    @ParameterizedTest
    @MethodSource("getCardOverMaximum")
    void calculateScoreWhenAceIsOver(List<Card> cardList, int targetScore) {
        Cards cards = new Cards(cardList);
        int score = cards.calculateScore();
        assertThat(score).isEqualTo(targetScore);
    }

    @DisplayName("Ace가 카드에 여러 개 존재하는 경우")
    @Test
    void calculateScoreWhenMultipleAce() {
        List<Card> cardList = Arrays.asList(
            new Card(Symbol.ACE, Shape.CLOVER),
            new Card(Symbol.ACE, Shape.DIAMOND),
            new Card(Symbol.ACE, Shape.CLOVER));
        Cards cards = new Cards(cardList);
        int score = cards.calculateScore();
        assertThat(score).isEqualTo(13);
    }
}
