package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.BlackjackDeck;
import blackjack.domain.player.Player;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.CardShape.다이아몬드;
import static blackjack.domain.card.CardShape.하트;
import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerBlackjackCardHandTest {

    @Test
    void 생성자의_파라미터가_NULL이면_예외를_발생시킨다() {
        // given

        // expected
        assertAll(
                () -> assertThatThrownBy(() -> new PlayerBlackjackCardHand(List::of, null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("필수 입력값 중 하나가 null입니다."),
                () -> assertThatThrownBy(() -> new PlayerBlackjackCardHand(null, DEFAULT_PLAYER))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("필수 입력값 중 하나가 null입니다.")
        );
    }

    @Test
    void 손패를_가진_플레이어의_이름을_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(
                new BlackjackDeck(), new Player("may")
        );

        // expected
        Assertions.assertThat(playerBlackjackCardHand.getPlayerName()).isEqualTo("may");
    }

    @Test
    void 카드를_2개_받고_시작한다() {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(
                new BlackjackDeck(), DEFAULT_PLAYER
        );

        // expected
        assertThat(playerBlackjackCardHand.getCards().size()).isEqualTo(2);
    }

    @Test
    void 카드_손패에_카드를_추가할_수_있다() {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(
                List::of, DEFAULT_PLAYER
        );
        final Card newCard = DIAMOND_1;

        // expected
        assertDoesNotThrow(() -> playerBlackjackCardHand.addCard(newCard));
    }

    @Test
    void 카드_손패에_있는_카드들을_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(
                List::of, DEFAULT_PLAYER
        );
        playerBlackjackCardHand.addCard(DIAMOND_1);
        playerBlackjackCardHand.addCard(HEART_2);

        // when
        final List<Card> result = playerBlackjackCardHand.getCards();

        // then
        assertThat(result).extracting(
                "number", "shape"
        ).containsExactlyInAnyOrder(
                Tuple.tuple(CardNumber.NUMBER_A, 다이아몬드),
                Tuple.tuple(CardNumber.NUMBER_2, 하트)
        );
    }

    @Test
    void 카드_손패에_있는_카드들의_숫자의_합을_계산할_수_있다() {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(List::of, DEFAULT_PLAYER);
        playerBlackjackCardHand.addCard(DIAMOND_1);
        playerBlackjackCardHand.addCard(HEART_2);

        // when
        final int sum = playerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(13);
    }

    @Test
    void JQK를_10으로_계산하여_숫자의_합을_반환한다() {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(List::of, DEFAULT_PLAYER);
        playerBlackjackCardHand.addCard(HEART_11);
        playerBlackjackCardHand.addCard(HEART_12);
        playerBlackjackCardHand.addCard(HEART_13);

        // when
        final int sum = playerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(30);
    }

    @Test
    void 손패에_존재하는_카드의_숫자의_합을_계산할_수_있다() {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(List::of, DEFAULT_PLAYER);
        playerBlackjackCardHand.addCard(DIAMOND_1);
        playerBlackjackCardHand.addCard(HEART_7);
        playerBlackjackCardHand.addCard(HEART_10);

        // when
        final int sum = playerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(18);
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndSumWithBust")
    void A를_11로_계산하여_버스트_되는_경우_A는_1로_계산된다(List<Card> cards, int expected) {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(List::of, DEFAULT_PLAYER);
        for (Card card : cards) {
            playerBlackjackCardHand.addCard(card);
        }
        playerBlackjackCardHand.addCard(DIAMOND_1);

        // when
        final int sum = playerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndSumWithBust() {
        return Stream.of(
                Arguments.of(List.of(HEART_10, HEART_5), 16),
                Arguments.of(List.of(HEART_10, HEART_4), 15),
                Arguments.of(List.of(HEART_10, HEART_3), 14),
                Arguments.of(List.of(HEART_10, HEART_2), 13),
                Arguments.of(List.of(HEART_10, HEART_1), 12)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndSumWithoutBust")
    void A를_11로_계산하여_버스트_되지_않는_경우_A는_11로_계산된다(List<Card> cards, int expected) {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(List::of, DEFAULT_PLAYER);
        for (Card card : cards) {
            playerBlackjackCardHand.addCard(card);
        }
        playerBlackjackCardHand.addCard(DIAMOND_1);

        // when
        final int sum = playerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndSumWithoutBust() {
        return Stream.of(
                Arguments.of(List.of(HEART_10), 21),
                Arguments.of(List.of(HEART_9), 20),
                Arguments.of(List.of(HEART_8), 19),
                Arguments.of(List.of(HEART_7), 18),
                Arguments.of(List.of(HEART_6), 17)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCardsWithAceAndSum")
    void A가_여러_개_주어진_경우_버스트가_되지_않는_선에서_최대_합을_구한다(List<Card> cards, int expected) {
        // given
        final PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(List::of, DEFAULT_PLAYER);
        for (Card card : cards) {
            playerBlackjackCardHand.addCard(card);
        }
        playerBlackjackCardHand.addCard(DIAMOND_1);
        playerBlackjackCardHand.addCard(HEART_1);

        // when
        final int sum = playerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsWithAceAndSum() {
        return Stream.of(
                Arguments.of(List.of(HEART_9, HEART_10), 21),
                Arguments.of(List.of(HEART_9, HEART_10), 21),
                Arguments.of(List.of(HEART_9, HEART_10), 21),
                Arguments.of(List.of(), 12)
        );
    }

    @Test
    void 내_손패가_버스트인지_알_수_있다() {
        // given
        final PlayerBlackjackCardHand player = new PlayerBlackjackCardHand(() -> List.of(
                HEART_10,
                DIAMOND_10,
                HEART_2
        ), DEFAULT_PLAYER);

        // when
        final boolean result = player.isBust();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 내_손패가_21인지_알_수_있다() {
        // given
        final PlayerBlackjackCardHand player = new PlayerBlackjackCardHand(() -> List.of(
                HEART_10,
                DIAMOND_1
        ), DEFAULT_PLAYER);

        // when
        final boolean result = player.isAddedUpToMax();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 플레이어는_처음에_두_장_공개해야_한다() {
        // given
        PlayerBlackjackCardHand playerBlackjackCardHand = new PlayerBlackjackCardHand(() -> List.of(
                HEART_3,
                HEART_5
        ), DEFAULT_PLAYER);

        // expected
        Assertions.assertThat(playerBlackjackCardHand.getInitialCards()).containsExactly(HEART_3, HEART_5);
    }
}
