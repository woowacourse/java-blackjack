package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
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
        final Cards cards = new Cards(createReceivedCard(CardNumber.KING, CardNumber.JACK));
        final int expected = 3;

        cards.addCard(new Card(CardPattern.DIAMOND, CardNumber.TWO));
        final int actual = cards.getCards().size();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "카드의 블랙잭 여부는 {1}이다.")
    @MethodSource("provideCardsAndExpectedForBlackJack")
    @DisplayName("블랙잭인지 확인한다.")
    void isBlackJack(Cards cards, boolean expected) {
        final boolean actual = cards.isBlackJack();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedForBlackJack() {
        return Stream.of(
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING)), true),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.ACE)), false),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.EIGHT)), false),
                Arguments.of(new Cards(
                        createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.JACK, CardNumber.TWO)), false)
        );
    }

    @ParameterizedTest(name = "카드의 처음 받은 카드인지 여부는 {1}이다.")
    @MethodSource("provideCardsAndExpectedForFirstReceivedCards")
    @DisplayName("처음받은 카드인지(2장인지) 확인한다.")
    void isFirstReceivedCards(Cards cards, boolean expected) {
        final boolean actual = cards.isFirstReceivedCards();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedForFirstReceivedCards() {
        return Stream.of(
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING)), true),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.ACE)), true),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.EIGHT)), false),
                Arguments.of(new Cards(
                        createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.JACK, CardNumber.TWO)), false)
        );
    }

    @ParameterizedTest(name = "카드의 총합이 21을 넘었는지의 여부는 {1}이다.")
    @MethodSource("provideCardsAndExpectedForExceed")
    @DisplayName("카드의 총합이 21을 넘었는지 확인한다.")
    void exceedMaxScore(Cards cards, boolean expected) {
        final boolean actual = cards.exceedMaxScore();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedForExceed() {
        return Stream.of(
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING)), false),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.ACE)), false),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.EIGHT)), false),
                Arguments.of(new Cards(
                        createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.JACK, CardNumber.TWO)), true)
        );
    }

    @Test
    @DisplayName("Ace를 포함하지 않는 카드의 총합을 구한다.")
    void calculateScore() {
        final Cards cards = new Cards(createReceivedCard(CardNumber.KING, CardNumber.JACK));
        final int expected = 20;

        final int actual = cards.calculateScore();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "카드의 총합은 {1}이다.")
    @MethodSource("provideCardsAndScore")
    @DisplayName("Ace를 포함한 카드의 총합을 구한다.")
    void calculateScoreIncludeAce(Cards cards, int expected) {
        final int actual = cards.calculateScore();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING)), 21),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.ACE)), 12),
                Arguments.of(new Cards(createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.EIGHT)), 19),
                Arguments.of(new Cards(
                        createReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.JACK, CardNumber.TWO)), 23)
        );
    }

    private static List<Card> createReceivedCard(CardNumber... cardNumbers) {
        List<Card> cards = new ArrayList<>();
        for (CardNumber cardNumber : cardNumbers) {
            cards.add(new Card(CardPattern.DIAMOND, cardNumber));
        }
        return cards;
    }
}
