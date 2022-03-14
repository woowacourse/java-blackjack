package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantTest {

    @ParameterizedTest(name = "[{index}] cards {0}, canAddCard {1}")
    @MethodSource("generateCanTakeCardArguments")
    @DisplayName("카드를 추가할 수 있는 지 확인한다.")
    void canTakeCard(List<Card> cards) {
        Participant participant = new Participant("배카라");
        cards.forEach(participant::takeCard);

        assertThat(participant.canTakeCard()).isTrue();
    }

    static Stream<Arguments> generateCanTakeCardArguments() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Denomination.THREE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART))),
                Arguments.of(
                        List.of(new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART))),
                Arguments.of(
                        List.of(new Card(Denomination.KING, Suit.CLOVER), new Card(Denomination.ACE, Suit.HEART)))
        );
    }

    @ParameterizedTest(name = "[{index}] cards {0}, canAddCard {1}")
    @MethodSource("generateCantTakeCardArguments")
    @DisplayName("21점을 초과할 경우 카드를 추가하지 못한다.")
    void cantTakeCard(List<Card> cards, Card card) {
        Participant participant = new Participant("배카라");
        cards.forEach(participant::takeCard);
        participant.takeCard(card);

        assertThat(participant.canTakeCard()).isFalse();
    }

    static Stream<Arguments> generateCantTakeCardArguments() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Denomination.FIVE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART)),
                        new Card(Denomination.NINE, Suit.SPADE)),
                Arguments.of(
                        List.of(new Card(Denomination.EIGHT, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART)),
                        new Card(Denomination.NINE, Suit.SPADE)),
                Arguments.of(
                        List.of(new Card(Denomination.KING, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART)),
                        new Card(Denomination.NINE, Suit.SPADE))
        );
    }
}
