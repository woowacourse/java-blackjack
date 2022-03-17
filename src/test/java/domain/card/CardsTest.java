package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @ParameterizedTest
    @MethodSource("provideCardAndScore")
    @DisplayName("List에 들어있는 Card의 총합을 계산하여 확인한다.")
    void CalculateSumTest(List<Card> card, int score) {
        Cards cards = Cards.of(card);

        assertThat(cards.calculateScore()).isEqualTo(score);
    }

    private static Stream<Arguments> provideCardAndScore() {
        return Stream.of(
            Arguments.of(List.of(Card.valueOf(Symbol.SPADE, Denomination.EIGHT),
                Card.valueOf(Symbol.SPADE, Denomination.NINE),
                Card.valueOf(Symbol.SPADE, Denomination.QUEEN)), 27),
            Arguments.of(List.of(Card.valueOf(Symbol.SPADE, Denomination.ACE),
                Card.valueOf(Symbol.CLOVER, Denomination.ACE),
                Card.valueOf(Symbol.DIAMOND, Denomination.ACE)), 13),
            Arguments.of(List.of(Card.valueOf(Symbol.SPADE, Denomination.FIVE),
                Card.valueOf(Symbol.CLOVER, Denomination.ACE),
                Card.valueOf(Symbol.DIAMOND, Denomination.ACE),
                Card.valueOf(Symbol.HEART, Denomination.ACE)), 18)
        );
    }

    @Test
    @DisplayName("Cards에 카드가 잘 추가되는지 확인한다.")
    public void addTest() {
        Cards cards = Cards.of(new ArrayList<>());
        Card card = Card.valueOf(Symbol.SPADE, Denomination.NINE);
        Cards addedCards = cards.addCard(card);

        assertThat(addedCards.getCardByIndex(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("Cards가 BlackJack인 경우에 true를 반환한다.")
    public void isBlackJackTest() {
        Cards cards = Cards.of(Cards.getEmptyList());
        Cards addedCards = cards.addCards(
            Arrays.asList(Card.valueOf(Symbol.HEART, Denomination.ACE), Card.valueOf(Symbol.SPADE,
                Denomination.QUEEN)));

        assertThat(addedCards.isBlackJack()).isTrue();
    }
}