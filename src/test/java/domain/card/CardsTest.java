package domain.card;

import static domain.GameManager.BLACKJACK_SCORE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @Test
    void 카드를_입력_받아_리스트에_추가한다() {
        // given
        Card card = Card.of(TrumpNumber.ACE, TrumpShape.CLUB);
        Cards cards = Cards.of();

        // when
        cards.add(card);

        // then
        assertThat(cards.getCards()).hasSize(1);
    }

    @Test
    void 가지고_있는_카드의_점수_합계를_계산한다() {
        // given
        Card card1 = Card.of(TrumpNumber.TWO, TrumpShape.CLUB);
        Card card2 = Card.of(TrumpNumber.FOUR, TrumpShape.CLUB);
        Card card3 = Card.of(TrumpNumber.SIX, TrumpShape.CLUB);
        Cards cards = Cards.of();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        // when
        int score = cards.calculateScore();

        // then
        assertThat(score).isEqualTo(12);
    }

    @ParameterizedTest
    @MethodSource("createCardsAndResult")
    void 에이스에_대한_최적의_선택을_한_점수를_계산한다(Cards cards, int expectedResult) {

        // when
        int score = cards.calculateScore();

        // then
        assertThat(score).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> createCardsAndResult() {
        return Stream.of(
                Arguments.of(createCards(
                        Card.of(TrumpNumber.ACE, TrumpShape.CLUB),
                        Card.of(TrumpNumber.KING, TrumpShape.CLUB)
                ), BLACKJACK_SCORE),
                Arguments.of(createCards(
                        Card.of(TrumpNumber.ACE, TrumpShape.CLUB),
                        Card.of(TrumpNumber.EIGHT, TrumpShape.CLUB),
                        Card.of(TrumpNumber.NINE, TrumpShape.CLUB)
                ), 18)
        );
    }

    private static Cards createCards(Card... inputCards) {
        Cards cards = Cards.of();
        for (Card card : inputCards) {
            cards.add(card);
        }
        return cards;
    }
}
