package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Deck;
import blackjack.domain.HandGenerator;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.testutil.CustomDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}