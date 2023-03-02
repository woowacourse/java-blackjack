package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Test
    @DisplayName("딜러는 받은 카드를 자신의 패에 추가한다.")
    void addCardInCards() {
        Participant participant = new Participant();

        participant.addCard(new Card(Symbol.CLOVER, Number.ACE));
        participant.addCard(new Card(Symbol.DIAMOND, Number.TEN));

        assertThat(participant.getCards().getCards().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    @DisplayName("참여자는 자신의 카드 점수를 계산한다.")
    void calculateCards(List<Card> cards, int expectedScore) {
        Participant participant = new Participant();
        for (Card card : cards) {
            participant.addCard(card);
        }

        int score = participant.calculateScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Symbol.SPADE, Number.SEVEN), new Card(Symbol.CLOVER, Number.TWO)), 9),
                Arguments.of(List.of(new Card(Symbol.HEART, Number.NINE), new Card(Symbol.HEART, Number.TWO)), 11),
                Arguments.of(List.of(new Card(Symbol.DIAMOND, Number.TEN), new Card(Symbol.SPADE, Number.TEN)), 20),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.THREE), new Card(Symbol.CLOVER, Number.TWO)), 5),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.SEVEN), new Card(Symbol.SPADE, Number.SEVEN), new Card(Symbol.DIAMOND, Number.SEVEN)), 21),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.TEN), new Card(Symbol.SPADE, Number.ACE), new Card(Symbol.DIAMOND, Number.SEVEN)), 18)
        );
    }

}
