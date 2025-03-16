package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.test_util.TestConstants.*;

public class PlayerBettingBlackjackCardHandTest {

    @Test
    void 베팅_금액이_1000원보다_적으면_예외가_발생한다() {
        // given
        final int bettingAmount = 999;

        // expected
        Assertions.assertThatThrownBy(() -> PlayerBettingBlackjackCardHand.createWithInitialCards(DEFAULT_PLAYER, bettingAmount, List::of))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원 이상, 100000원 이하여야 합니다.");
    }

    @Test
    void 베팅_금액이_100000원보다_크면_예외가_발생한다() {
        // given
        final int bettingAmount = 100001;

        // expected
        Assertions.assertThatThrownBy(() -> PlayerBettingBlackjackCardHand.createWithInitialCards(DEFAULT_PLAYER, bettingAmount, List::of))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원 이상, 100000원 이하여야 합니다.");
    }
    
    @Test
    void 베팅_금액이_1000원_단위가_아니면_예외가_발생한다() {
        // given
        final int bettingAmount = 1500;
        
        // expected
        Assertions.assertThatThrownBy(() -> PlayerBettingBlackjackCardHand.createWithInitialCards(DEFAULT_PLAYER, bettingAmount, List::of))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1000원 단위여야 합니다.");
    }

    @ParameterizedTest
    @MethodSource("provideLess16Cards")
    void 플레이어가_21에_더_근접하면_플레이어가_이기고_베팅금액만큼_번다(List<Card> less16Cards) {
        // given
        final var player = bettingPlayerWith(1000, HEART_6, HEART_10);
        final var dealer = dealerWith(less16Cards);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(1000);
    }

    @ParameterizedTest
    @MethodSource("provideLess16Cards")
    void 딜러가_21에_더_근접하면_플레이어가_패배하고_베팅금액을_잃는다(List<Card> less16Cards) {
        // given
        final var player = bettingPlayerWith(1000, less16Cards);
        final var dealer = dealerWith(HEART_6, HEART_10);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(-1000);
    }

    @ParameterizedTest
    @MethodSource("provideSameSumCards")
    void 플레이어와_딜러가_같은_합이면_무승부이고_이익은_없다(List<Card> cards1, List<Card> cards2) {
        // given
        final var player = bettingPlayerWith(1000, cards1);
        final var dealer = dealerWith(cards2);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideNotBustNotBlackjackCards")
    void 플레이어만_버스트라면_플레이어가_패배하고_베팅금액을_잃는다(List<Card> notBustNotBlackjackCards) {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_10, HEART_10, HEART_2);
        final var dealer = dealerWith(notBustNotBlackjackCards);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(-1000);
    }

    @ParameterizedTest
    @MethodSource("provideBustCards")
    void 플레이어와_딜러가_버스트라면_플레이어가_패배하고_베팅금액을_잃는다(List<Card> bustCards) {
        // given
        final var player = bettingPlayerWith(1000, bustCards);
        final var dealer = dealerWith(bustCards);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(-1000);
    }

    @ParameterizedTest
    @MethodSource("provideNotBustNotBlackjackCards")
    void 딜러만_버스트라면_플레이어가_승리하고_베팅금액만큼_번다(List<Card> notBustNotBlackjackCards) {
        // given
        final var player = bettingPlayerWith(1000, notBustNotBlackjackCards);
        final var dealer = dealerWith(DIAMOND_9, DIAMOND_10, HEART_3);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(1000);
    }

    @ParameterizedTest
    @MethodSource("provideBlackjackCards")
    @DisplayName("플레이어만 블랙잭이라면 베팅금액의 1.5배를 번다")
    void 플레이어만_블랙잭이라면_플레이어가_승리하고_베팅금액의_1점5배를_번다(List<Card> blackjackCards) {
        // given
        final var player = bettingPlayerWith(1000, blackjackCards);
        final var dealer = dealerWith(DIAMOND_5, DIAMOND_1);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(1500);
    }

    @ParameterizedTest
    @MethodSource("provideBlackjackCards")
    void 플레이어와_딜러_모두_블랙잭이라면_무승부이고_이익은_없다(List<Card> blackjackCards) {
        // given
        final var player = bettingPlayerWith(1000, blackjackCards);
        final var dealer = dealerWith(blackjackCards);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideBlackjackCards")
    void 딜러만_블랙잭이라면_플레이어가_패배한다(List<Card> blackjackCards) {
        // given
        final var player = bettingPlayerWith(1000, DIAMOND_5, DIAMOND_1);
        final var dealer = dealerWith(blackjackCards);

        // when
        final var result = player.calculateIncome(dealer);

        // then
        Assertions.assertThat(result).isEqualTo(-1000);
    }

    private static Stream<Arguments> provideLess16Cards() {
        return Stream.of(
                Arguments.of(List.of(HEART_10, HEART_5)),
                Arguments.of(List.of(HEART_10, HEART_4)),
                Arguments.of(List.of(HEART_10, HEART_3))
        );
    }

    private static Stream<Arguments> provideSameSumCards() {
        return Stream.of(
                Arguments.of(List.of(HEART_10, HEART_5), List.of(HEART_9, HEART_6)),
                Arguments.of(List.of(HEART_10, HEART_10), List.of(HEART_9, HEART_1)),
                Arguments.of(List.of(HEART_1, HEART_5, HEART_6), List.of(HEART_2, HEART_3, HEART_7))
        );
    }

    private static Stream<Arguments> provideNotBustNotBlackjackCards() {
        return Stream.of(
                Arguments.of(List.of(HEART_10, HEART_5)),
                Arguments.of(List.of(HEART_10, HEART_10)),
                Arguments.of(List.of(HEART_2))
        );
    }

    private static Stream<Arguments> provideBustCards() {
        return Stream.of(
                Arguments.of(List.of(HEART_10, HEART_10, HEART_2)),
                Arguments.of(List.of(HEART_10, HEART_9, HEART_5)),
                Arguments.of(List.of(HEART_2, HEART_12, HEART_13))
        );
    }

    private static Stream<Arguments> provideBlackjackCards() {
        return Stream.of(
                Arguments.of(List.of(HEART_1, HEART_10)),
                Arguments.of(List.of(HEART_1, HEART_11)),
                Arguments.of(List.of(HEART_1, HEART_12)),
                Arguments.of(List.of(HEART_1, HEART_13))
        );
    }

    private static blackjack.domain.card_hand.PlayerBettingBlackjackCardHand bettingPlayerWith(int money, Card... cards) {
        return blackjack.domain.card_hand.PlayerBettingBlackjackCardHand.createWithInitialCards(DEFAULT_PLAYER, money, () -> List.of(cards));
    }

    private static blackjack.domain.card_hand.PlayerBettingBlackjackCardHand bettingPlayerWith(int money, List<Card> cards) {
        return blackjack.domain.card_hand.PlayerBettingBlackjackCardHand.createWithInitialCards(DEFAULT_PLAYER, money, () -> cards);
    }

    private static DealerBlackjackCardHand dealerWith(Card... cards) {
        return DealerBlackjackCardHand.createWithInitialCards(() -> List.of(cards));
    }

    private static DealerBlackjackCardHand dealerWith(List<Card> cards) {
        return DealerBlackjackCardHand.createWithInitialCards(() -> cards);
    }
}
