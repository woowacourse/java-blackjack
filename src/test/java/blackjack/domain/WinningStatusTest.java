package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class WinningStatusTest {

    @ParameterizedTest
    @MethodSource("provideLess16Cards")
    void 플레이어가_21에_더_근접하면_플레이어가_이긴다(List<Card> less16Cards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(HEART_6, HEART_10);
        final DealerBlackjackCardHand dealer = dealerWith(less16Cards);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_승리);
    }

    @ParameterizedTest
    @MethodSource("provideLess16Cards")
    void 딜러가_21에_더_근접하면_플레이어가_패배한다(List<Card> less16Cards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(less16Cards);
        final DealerBlackjackCardHand dealer = dealerWith(HEART_6, HEART_10);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
    }

    @ParameterizedTest
    @MethodSource("provideSameSumCards")
    void 플레이어와_딜러가_같은_합이면_무승부이다(List<Card> cards1, List<Card> cards2) {
        // given
        final PlayerBlackjackCardHand player = playerWith(cards1);
        final DealerBlackjackCardHand dealer = dealerWith(cards2);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.무승부);
    }

    @ParameterizedTest
    @MethodSource("provideNotBustNotBlackjackCards")
    void 플레이어만_버스트라면_플레이어가_패배한다(List<Card> notBustNotBlackjackCards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_10, HEART_10, HEART_2);
        final DealerBlackjackCardHand dealer = dealerWith(notBustNotBlackjackCards);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
    }

    @ParameterizedTest
    @MethodSource("provideBustCards")
    void 플레이어와_딜러가_버스트라면_플레이어가_패배한다(List<Card> bustCards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(bustCards);
        final DealerBlackjackCardHand dealer = dealerWith(bustCards);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
    }

    @ParameterizedTest
    @MethodSource("provideNotBustNotBlackjackCards")
    void 딜러만_버스트라면_플레이어가_승리한다(List<Card> notBustNotBlackjackCards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(notBustNotBlackjackCards);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_10, HEART_10, HEART_2);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_승리);
    }

    @ParameterizedTest
    @MethodSource("provideBlackjackCards")
    void 플레이어만_블랙잭이라면_플레이어가_승리한다(List<Card> blackjackCards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(blackjackCards);
        final DealerBlackjackCardHand dealer = dealerWith(DIAMOND_5, DIAMOND_1);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_승리);
    }

    @ParameterizedTest
    @MethodSource("provideBlackjackCards")
    void 플레이어와_딜러_모두_블랙잭이라면_무승부이다(List<Card> blackjackCards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(blackjackCards);
        final DealerBlackjackCardHand dealer = dealerWith(blackjackCards);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.무승부);
    }

    @ParameterizedTest
    @MethodSource("provideBlackjackCards")
    void 딜러만_블랙잭이라면_플레이어가_패배한다(List<Card> blackjackCards) {
        // given
        final PlayerBlackjackCardHand player = playerWith(DIAMOND_5, DIAMOND_1);
        final DealerBlackjackCardHand dealer = dealerWith(blackjackCards);

        // when
        final WinningStatus result = WinningStatus.determineWinningStatus(player, dealer);

        // then
        assertThat(result).isEqualTo(WinningStatus.플레이어_패배);
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

    private static PlayerBlackjackCardHand playerWith(Card... cards) {
        return new PlayerBlackjackCardHand(() -> List.of(cards), DEFAULT_PLAYER);
    }

    private static PlayerBlackjackCardHand playerWith(List<Card> cards) {
        return new PlayerBlackjackCardHand(() -> cards, DEFAULT_PLAYER);
    }

    private static DealerBlackjackCardHand dealerWith(Card... cards) {
        return new DealerBlackjackCardHand(() -> List.of(cards));
    }

    private static DealerBlackjackCardHand dealerWith(List<Card> cards) {
        return new DealerBlackjackCardHand(() -> cards);
    }
}
