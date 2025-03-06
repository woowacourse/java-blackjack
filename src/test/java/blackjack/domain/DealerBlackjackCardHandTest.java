package blackjack.domain;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.testutil.TestConstants.*;

import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.deck.Deck;
import blackjack.testutil.CardDrawerStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerBlackjackCardHandTest {

    @Test
    void 딜러_손패_숫자의_합이_16_이하인_경우_카드를_뽑는다() {
        //given
        DealerBlackjackCardHand DealerBlackjackCardHand = new DealerBlackjackCardHand(List::of);

        //when
        DealerBlackjackCardHand.startAdding(new Deck());

        //then
        Assertions.assertThat(DealerBlackjackCardHand.getCards().size()).isGreaterThanOrEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("provideInitCardsAndDeckCardsAndExpectedSum")
    void 딜러_손패_숫자의_합이_15_이고_뽑을_수_있는_숫자가_1_2_3인_경우_18까지_뽑는다(
            List<Card> initCards,
            List<Card> deckCards,
            int expectedSum
    ) {
        //given
        DealerBlackjackCardHand DealerBlackjackCardHand = new DealerBlackjackCardHand(() -> initCards);

        //when
        DealerBlackjackCardHand.startAdding(new CardDrawerStub(deckCards));

        //then
        Assertions.assertThat(DealerBlackjackCardHand.getBlackjackSum()).isEqualTo(expectedSum);
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
        DealerBlackjackCardHand DealerBlackjackCardHand = new DealerBlackjackCardHand(() -> List.of(HEART_3, HEART_5));

        // expected
        Assertions.assertThat(DealerBlackjackCardHand.getInitialCards()).containsExactly(HEART_3);
    }
}
