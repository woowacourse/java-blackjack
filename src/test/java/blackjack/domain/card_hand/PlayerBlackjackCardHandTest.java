package blackjack.domain.card_hand;

import blackjack.domain.player.Player;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.BlackjackDeck;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerBlackjackCardHandTest {
    
    @Test
    void 생성자의_파라미터가_NULL이면_예외를_발생시킨다() {
        // given
        
        // expected
        assertAll(
                () -> assertThatThrownBy(() -> new PlayerBlackjackCardHand(null, List::of))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("플레이어는 null이 될 수 없습니다."),
                () -> assertThatThrownBy(() -> new PlayerBlackjackCardHand(DEFAULT_PLAYER, null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("초기 카드 지급 방식은 null이 될 수 없습니다.")
        );
    }
    
    @Test
    void 손패를_가진_플레이어의_이름을_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(new Player("may"), new BlackjackDeck());

        // expected
        Assertions.assertThat(PlayerBlackjackCardHand.getPlayerName()).isEqualTo("may");
    }

    @Test
    void 카드를_2개_받고_시작한다() {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, new BlackjackDeck());

        // expected
        assertThat(PlayerBlackjackCardHand.getCards().size()).isEqualTo(2);
    }

    @Test
    void 카드_손패에_카드를_추가할_수_있다() {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        final Card newCard = DIAMOND_1;

        // expected
        assertDoesNotThrow(() -> PlayerBlackjackCardHand.addCard(newCard));
    }

    @Test
    void 카드_손패에_있는_카드들을_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        PlayerBlackjackCardHand.addCard(DIAMOND_1);
        PlayerBlackjackCardHand.addCard(HEART_2);

        // when
        final List<Card> result = PlayerBlackjackCardHand.getCards();

        // then
        assertThat(result).extracting(
                "number", "shape"
        ).containsExactlyInAnyOrder(
                Tuple.tuple(CardNumber.ACE, DIAMOND),
                Tuple.tuple(CardNumber.TWO, HEART)
        );
    }

    @Test
    void 카드_손패에_있는_카드들의_숫자의_합을_계산할_수_있다() {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        PlayerBlackjackCardHand.addCard(DIAMOND_1);
        PlayerBlackjackCardHand.addCard(HEART_2);

        // when
        final int sum = PlayerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(13);
    }

    @Test
    void JQK를_10으로_계산하여_숫자의_합을_반환한다() {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        PlayerBlackjackCardHand.addCard(HEART_11);
        PlayerBlackjackCardHand.addCard(HEART_12);
        PlayerBlackjackCardHand.addCard(HEART_13);

        // when
        final int sum = PlayerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(30);
    }

    @Test
    void 손패에_존재하는_카드의_숫자의_합을_계산할_수_있다() {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        PlayerBlackjackCardHand.addCard(DIAMOND_1);
        PlayerBlackjackCardHand.addCard(HEART_7);
        PlayerBlackjackCardHand.addCard(HEART_10);

        // when
        final int sum = PlayerBlackjackCardHand.getBlackjackSum();

        // then
        assertThat(sum).isEqualTo(18);
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndSumWithBust")
    void A를_11로_계산하여_버스트_되는_경우_A는_1로_계산된다(List<Card> cards, int expected) {
        // given
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        for (Card card : cards) {
            PlayerBlackjackCardHand.addCard(card);
        }
        PlayerBlackjackCardHand.addCard(DIAMOND_1);

        // when
        final int sum = PlayerBlackjackCardHand.getBlackjackSum();

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
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        for (Card card : cards) {
            PlayerBlackjackCardHand.addCard(card);
        }
        PlayerBlackjackCardHand.addCard(DIAMOND_1);

        // when
        final int sum = PlayerBlackjackCardHand.getBlackjackSum();

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
        final PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of);
        for (Card card : cards) {
            PlayerBlackjackCardHand.addCard(card);
        }
        PlayerBlackjackCardHand.addCard(DIAMOND_1);
        PlayerBlackjackCardHand.addCard(HEART_1);

        // when
        final int sum = PlayerBlackjackCardHand.getBlackjackSum();

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
        final PlayerBlackjackCardHand player = new PlayerBlackjackCardHand(DEFAULT_PLAYER, () -> List.of(
                HEART_10,
                DIAMOND_10,
                HEART_2
        ));

        // when
        final boolean result = player.isBust();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 내_손패가_21인지_알_수_있다() {
        // given
        final PlayerBlackjackCardHand player = new PlayerBlackjackCardHand(DEFAULT_PLAYER, () -> List.of(
                HEART_10,
                DIAMOND_1
        ));

        // when
        final boolean result = player.isAddedTo21();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 플레이어는_처음에_두_장_공개해야_한다() {
        // given
        PlayerBlackjackCardHand PlayerBlackjackCardHand = new PlayerBlackjackCardHand(DEFAULT_PLAYER, () -> List.of(
                HEART_3,
                HEART_5
        ));

        // expected
        Assertions.assertThat(PlayerBlackjackCardHand.getInitialCards()).containsExactly(HEART_3, HEART_5);
    }
}
