package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.test_util.CardDrawerStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DealerBlackjackCardHandTest {

    @Test
    void CardHandInitializer가_NULL이면_예외를_발생시킨다() {
        // given

        // expected
        assertThatThrownBy(() -> new DealerBlackjackCardHand(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("필수 입력값 중 하나가 null입니다.");
    }

    @Test
    void 딜러_손패_숫자의_합이_16_이하인_경우_카드를_뽑는다() {
        // given
        DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(List::of);

        // when
        dealerBlackjackCardHand.startAddingAndGetAddedSize(new BlackjackDeck());

        // then
        assertThat(dealerBlackjackCardHand.getCards().size()).isGreaterThanOrEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("provideInitCardsAndDeckCardsAndExpectedSum")
    void 딜러_손패_숫자의_합이_15_이고_뽑을_수_있는_숫자가_1_2_3인_경우_18까지_뽑는다(
            List<Card> initCards,
            List<Card> deckCards,
            int expectedSum
    ) {
        // given
        DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(() -> initCards);

        // when
        dealerBlackjackCardHand.startAddingAndGetAddedSize(new CardDrawerStub(deckCards));

        // then
        assertThat(dealerBlackjackCardHand.getBlackjackSum()).isEqualTo(expectedSum);
    }

    @Test
    void 딜러_손패가_추가_카드를_뽑으면_뽑은_개수를_반환한다() {
        // given
        DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(() -> List.of(DIAMOND_9, DIAMOND_5));
        final List<Card> addedCards = List.of(HEART_1, HEART_2, HEART_3);

        // when
        final int result = dealerBlackjackCardHand.startAddingAndGetAddedSize(new CardDrawerStub(addedCards));

        // then
        assertThat(result).isEqualTo(2);
    }

    private static Stream<Arguments> provideInitCardsAndDeckCardsAndExpectedSum() {
        return Stream.of(
                Arguments.of(
                        List.of(HEART_10, HEART_5),
                        List.of(HEART_1, HEART_2, HEART_3),
                        18
                ),
                Arguments.of(
                        List.of(HEART_10, HEART_3),
                        List.of(HEART_1, HEART_2, HEART_3),
                        19
                ),
                Arguments.of(
                        List.of(HEART_10, HEART_5, HEART_2),
                        List.of(HEART_1, HEART_2, HEART_3),
                        17
                )
        );
    }

    @Test
    void 딜러는_처음에_한_장만_공개해야_한다() {
        // given
        DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(() -> List.of(HEART_3, HEART_5));

        // expected
        assertThat(dealerBlackjackCardHand.getInitialCards()).containsExactly(HEART_3);
    }
}
