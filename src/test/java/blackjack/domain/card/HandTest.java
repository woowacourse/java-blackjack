package blackjack.domain.card;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.JACK;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Number.TEN;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class HandTest {

    private static Stream<Arguments> generateCardsWithoutAce() {
        return Stream.of(
                Arguments.of(List.of(new Card(TWO, SPADE), new Card(JACK, HEART)), 12),
                Arguments.of(List.of(), 0)
        );
    }

    private static Stream<Arguments> generateCardsWithAce() {
        return Stream.of(
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(ACE, HEART), new Card(TEN, CLOVER)), 12),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(QUEEN, HEART), new Card(TEN, CLOVER)), 21),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(ACE, HEART)), 12)
        );
    }

    @Test
    void 카드를_추가한다() {
        final List<Card> cards = List.of(new Card(ACE, SPADE), new Card(TWO, HEART));
        final Hand hand = new Hand(cards);

        final Hand newHand = hand.addCard(new Card(QUEEN, CLOVER));

        assertThat(newHand.count()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithoutAce")
    void 점수를_계산한다(final List<Card> cardPack, final int totalScore) {
        final Hand hand = new Hand(cardPack);

        assertThat(hand.calculateTotalScore()).isEqualTo(totalScore);
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithAce")
    void 에이스가_포함된_경우_최적의_점수를_계산한다(final List<Card> cardPack, final int totalScore) {
        final Hand hand = new Hand(cardPack);

        assertThat(hand.calculateTotalScore()).isEqualTo(totalScore);
    }
}
