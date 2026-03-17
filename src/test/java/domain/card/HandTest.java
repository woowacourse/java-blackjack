package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.CardGenerator;
import service.TestCardGenerator;

class HandTest {

    private List<Card> cards;

    @BeforeEach
    void setUp() {
        CardGenerator cardGenerator = new TestCardGenerator();
        cards = cardGenerator.generate();
    }

    @DisplayName("카드패에 카드가 정상적으로 추가되나 테스트합니다.")
    @Test
    void 카드를_정상적으로_추가한다() {
        //given
        Hand hand = new Hand();
        //when
        hand.addAll(cards);
        //then
        assertThat(hand.getCards()).isEqualTo(cards);
        assertThat(hand.getCards().size()).isEqualTo(6);
    }

    private static Stream<Arguments> calculatingScoreCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER),
                        new Card(Rank.ACE, Suit.SPADE)), 12),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER)), 21),
                Arguments.of(List.of(new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART)), 11),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.SPADE), new Card(Rank.JACK, Suit.HEART),
                        new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.JACK, Suit.DIAMOND)), 40)
        );
    }

    @DisplayName("카드패의 점수 계산을 테스트합니다.")
    @ParameterizedTest
    @MethodSource("calculatingScoreCards")
    void 카드_점수를_정상적으로_계산한다(List<Card> cards, int expectedScore) {
        Hand hand = new Hand();

        hand.addAll(cards);

        assertThat(hand.calculateScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> bustResults() {
        return Stream.of(
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER),
                        new Card(Rank.ACE, Suit.SPADE), new Card(Rank.KING, Suit.HEART)), true),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER)), false),
                Arguments.of(List.of(new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART)), false)
        );
    }

    @DisplayName("카드패의 버스트 여부를 테스트합니다.")
    @ParameterizedTest
    @MethodSource("bustResults")
    void 버스트_여부를_판별한다(List<Card> cards, boolean expectedBust) {
        Hand hand = new Hand();

        hand.addAll(cards);

        assertThat(hand.isBust()).isEqualTo(expectedBust);
    }
}
