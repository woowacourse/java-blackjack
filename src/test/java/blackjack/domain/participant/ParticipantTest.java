package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.testutil.CustomDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {
    private static TestParticipant createParticipant(List<Number> numbers, List<Shape> shapes, String name) {
        Deck deck = new CustomDeck(numbers, shapes);
        HandGenerator handGenerator = new HandGenerator(deck);
        return new TestParticipant(new Name(name), handGenerator);
    }

    @DisplayName("입력된 숫자만큼 카드를 반환한다.")
    @Test
    void getCardsByCountTest() {
        List<Number> numbers = List.of(Number.ACE, Number.FOUR, Number.NINE);
        List<Shape> shapes = List.of(Shape.SPADE, Shape.DIAMOND, Shape.CLOVER);
        TestParticipant participant = createParticipant(numbers, shapes, "감자");

        List<String> cardSignatures = participant.getCardsByCount(1)
                .stream()
                .map(Card::getSignature)
                .toList();

        assertThat(cardSignatures).containsExactly("A스페이드");
    }


    private static class TestParticipant extends Participant {
        public TestParticipant(Name name, HandGenerator handGenerator) {
            super(name, handGenerator);
        }

        @Override
        public List<Card> getInitialOpenedCards() {
            return getCards();
        }
    }
}
