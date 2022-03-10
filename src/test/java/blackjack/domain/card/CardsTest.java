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
    @DisplayName("Ace를 포함하지 않는 카드의 총합을 구한다.")
    void calculateScore() {
        final Cards cards = new Cards(createFirstReceivedCard(CardNumber.KING, CardNumber.JACK));
        final int expected = 20;

        final int actual = cards.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 리스트에 카드를 추가한다.")
    void addCard() {
        final Cards cards = new Cards(createFirstReceivedCard(CardNumber.KING, CardNumber.JACK));
        final int expected = 3;

        cards.addCard(new Diamond(CardNumber.TWO));
        final int actual = cards.getCards().size();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    @DisplayName("Ace를 포함한 카드의 총합을 구한다.")
    void calculateScoreIncludeAce(Cards cards, int expected) {
        final int actual = cards.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(
                Arguments.of(new Cards(createFirstReceivedCard(CardNumber.ACE, CardNumber.KING)), 21),
                Arguments.of(new Cards(createFirstReceivedCard(CardNumber.ACE, CardNumber.ACE)), 12),
                Arguments.of(new Cards(createFirstReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.EIGHT)), 19),
                Arguments.of(new Cards(
                        createFirstReceivedCard(CardNumber.ACE, CardNumber.KING, CardNumber.JACK, CardNumber.TWO)), 23)
        );
    }

    private static List<Card> createFirstReceivedCard(CardNumber... cardNumbers) {
        List<Card> cards = new ArrayList<>();
        for (CardNumber cardNumber : cardNumbers) {
            cards.add(new Diamond(cardNumber));
        }
        return cards;
    }
}
