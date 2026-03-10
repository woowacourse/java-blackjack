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

class CardBoardTest {

    private List<Card> cards;

    @BeforeEach
    void set_up() {
        CardGenerator cardGenerator = new TestCardGenerator();
        cards = cardGenerator.generate();
    }

    @DisplayName("카드패에 카드가 정상적으로 추가되나 테스트합니다.")
    @Test
    void 카드_정상_추가_테스트() {
        //given
        CardBoard cardBoard = new CardBoard();
        //when
        cardBoard.addAll(cards);
        //then
        assertThat(cardBoard.getCards()).isEqualTo(cards);
        assertThat(cardBoard.getCards().size()).isEqualTo(6);
    }

    private static Stream<Arguments> CalculatingScoreCards() {
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
    @MethodSource("CalculatingScoreCards")
    void 카드_점수_정상_테스트(List<Card> cards, int expectedScore) {
        CardBoard cardBoard = new CardBoard();

        cardBoard.addAll(cards);

        assertThat(cardBoard.calculateScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> BustResult() {
        return Stream.of(
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER),
                        new Card(Rank.ACE, Suit.SPADE), new Card(Rank.KING, Suit.HEART)), true),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER)), false),
                Arguments.of(List.of(new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART)), false)
        );
    }

    @DisplayName("카드패의 버스트 여부를 테스트합니다.")
    @ParameterizedTest
    @MethodSource("BustResult")
    void 버스트_여부_테스트(List<Card> cards, boolean expectedBust) {
        CardBoard cardBoard = new CardBoard();

        cardBoard.addAll(cards);

        assertThat(cardBoard.isBust()).isEqualTo(expectedBust);
    }
}
