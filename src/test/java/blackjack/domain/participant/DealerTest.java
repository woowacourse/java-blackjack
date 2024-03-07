package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.HandGenerator;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.testutil.CustomDeck;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    private static Dealer createDealer(List<Number> numbers, List<Shape> shapes) {
        Deck deck = new CustomDeck(numbers, shapes);
        HandGenerator handGenerator = new HandGenerator(deck);
        return new Dealer(handGenerator);
    }

    @DisplayName("딜러는 처음에 한 장의 카드를 오픈한다.")
    @Test
    void initialOpenedCardsTest() {
        List<Number> numbers = List.of(Number.ACE, Number.EIGHT);
        List<Shape> shapes = List.of(Shape.SPADE, Shape.CLOVER);
        Dealer dealer = createDealer(numbers, shapes);

        List<String> cardSignatures = dealer.getInitialOpenedCards()
                .stream()
                .map(Card::getSignature)
                .toList();

        assertThat(cardSignatures).containsExactly("A스페이드");
    }

    @DisplayName("딜러가 Hit을 할 수 있는지 판단한다.")
    @ParameterizedTest
    @MethodSource("provideNumbersWithCanHit")
    void canHitTest(List<Number> numbers, boolean canHit) {
        Deck deck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(deck);
        Dealer dealer = new Dealer(handGenerator);

        assertThat(dealer.canHit()).isEqualTo(canHit);
    }

    private static Stream<Arguments> provideNumbersWithCanHit() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.JACK), false),
                Arguments.of(List.of(Number.EIGHT, Number.NINE), false),
                Arguments.of(List.of(Number.FOUR, Number.FIVE), true)
        );
    }
}
