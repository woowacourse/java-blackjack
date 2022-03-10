package blackjack.domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantTest {

    @ParameterizedTest(name = "[{index}] cards {0}, canAddCard {1}")
    @MethodSource("parameters1")
    @DisplayName("카드를 추가할 수 있는 지 확인한다.")
    void canAddCard(List<Card> cards) {
        Participant participant = new Participant("배카라");
        cards.forEach(participant::addCard);

        Assertions.assertThat(participant.canAddCard()).isTrue();
    }

    static Stream<Arguments> parameters1() {
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
    @MethodSource("parameters2")
    @DisplayName("21점을 초과할 경우 카드를 추가하지 못한다.")
    void cantAddCard(List<Card> cards, Card card) {
        Participant participant = new Participant("배카라");
        cards.forEach(participant::addCard);
        participant.addCard(card);

        Assertions.assertThat(participant.canAddCard()).isFalse();
    }

    static Stream<Arguments> parameters2() {
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
