package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @Test
    @DisplayName("카드 리스트에 카드를 추가한다.")
    void addCard() {
        Cards cards = createCards(
                new Card(CardPattern.HEART, CardNumber.TWO),
                new Card(CardPattern.HEART, CardNumber.THREE),
                new Card(CardPattern.HEART, CardNumber.FOUR)
        );
        final int expected = 4;

        cards.addCard(new Card(CardPattern.HEART, CardNumber.TWO));
        final int actual = cards.getCards().size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Ace 카드를 포함하지 않는 카드의 총합을 구한다.")
    void calculateScore() {
        final Cards cards = createCards(
                new Card(CardPattern.CLOVER, CardNumber.KING),
                new Card(CardPattern.HEART, CardNumber.JACK)
        );
        final int expected = 20;

        final int actual = cards.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideCardsIncludeAce")
    @DisplayName("Ace 카드를 포함한 카드의 총합을 구한다.")
    void calculateScoreIncludeAce(Cards cards, int expected, String message) {
        final int actual = cards.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideCardsIncludeAce() {
        return Stream.of(
                Arguments.of(createCards(
                        new Card(CardPattern.HEART, CardNumber.TEN),
                        new Card(CardPattern.HEART, CardNumber.ACE)
                ), 21, "ACE 카드를 11로 사용하는 경우"),
                Arguments.of(createCards(
                        new Card(CardPattern.HEART, CardNumber.TEN),
                        new Card(CardPattern.HEART, CardNumber.TEN),
                        new Card(CardPattern.HEART, CardNumber.ACE)
                ), 21, "ACE 카드를 1로 사용하는 경우")
        );
    }

    private static Cards createCards(Card... inputCards) {
        return new Cards(new ArrayList<>(Arrays.asList(inputCards)));
    }
}
