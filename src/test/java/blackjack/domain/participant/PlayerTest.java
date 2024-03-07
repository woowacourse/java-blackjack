package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.testutil.CardDrawer;
import blackjack.testutil.CustomDeck;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    private static Player createPlayer(List<Number> numbers, List<Shape> shapes, String name) {
        Deck deck = new CustomDeck(numbers, shapes);
        HandGenerator handGenerator = new HandGenerator(deck);
        return new Player(new Name(name), handGenerator);
    }

    @DisplayName("플레이어는 처음에 두 장의 카드를 오픈한다.")
    @Test
    void initialOpenedCardsTest() {
        List<Number> numbers = List.of(Number.ACE, Number.EIGHT);
        List<Shape> shapes = List.of(Shape.SPADE, Shape.CLOVER);
        Player player = createPlayer(numbers, shapes, "mason");

        List<String> cardSignatures = player.getInitialOpenedCards()
                .stream()
                .map(Card::getSignature)
                .toList();

        assertThat(cardSignatures).containsExactly("A스페이드", "8클로버");
    }

    @DisplayName("플레이어가 Hit을 할 수 있는지 판단한다.")
    @ParameterizedTest
    @MethodSource("provideNumbersWithCanHit")
    void canHitTest(List<Number> numbers, boolean canHit) {
        Deck deck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(deck);
        Player player = new Player(new Name("gamza"), handGenerator);
        CardDrawer.addAllCards(deck, player);
        assertThat(player.canHit()).isEqualTo(canHit);
    }

    private static Stream<Arguments> provideNumbersWithCanHit() {
        return Stream.of(
                Arguments.of(List.of(Number.ACE, Number.JACK), true),
                Arguments.of(List.of(Number.EIGHT, Number.NINE), true),
                Arguments.of(List.of(Number.EIGHT, Number.NINE, Number.QUEEN), false)
        );
    }
}
