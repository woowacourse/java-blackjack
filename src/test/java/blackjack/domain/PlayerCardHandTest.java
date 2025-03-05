package blackjack.domain;

import blackjack.domain.card_hand.DealerCardHand;
import blackjack.domain.card_hand.PlayerCardHand;
import blackjack.testutil.CardHandInitializerDummy;
import blackjack.testutil.CardHandInitializerStub;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.CardShape.*;
import static blackjack.testutil.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerCardHandTest {
    
    private static final Player DEFAULT_PLAYER = new Player("dompoo");
    
    @Test
    void 손패를_가진_플레이어의_이름을_확인할_수_있다() {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(new Player("may"), new Deck());
        
        // expected
        Assertions.assertThat(playerCardHand.getPlayerName()).isEqualTo("may");
    }
    
    @Test
    void 카드를_2개_받고_시작한다() {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new Deck());
        
        // expected
        assertThat(playerCardHand.getCards().size()).isEqualTo(2);
    }
    
    @Test
    void 카드_손패에_카드를_추가할_수_있다() {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        final Card newCard = DIAMOND_1;
        
        // expected
        assertDoesNotThrow(() -> playerCardHand.addCard(newCard));
    }
    
    @Test
    void 카드_손패에_있는_카드들을_확인할_수_있다() {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        playerCardHand.addCard(DIAMOND_1);
        playerCardHand.addCard(HEART_2);
        
        // when
        final List<Card> result = playerCardHand.getCards();
        
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
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        playerCardHand.addCard(DIAMOND_1);
        playerCardHand.addCard(HEART_2);
        
        // when
        final int sum = playerCardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(13);
    }
    
    @Test
    void JQK를_10으로_계산하여_숫자의_합을_반환한다() {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        playerCardHand.addCard(HEART_11);
        playerCardHand.addCard(HEART_12);
        playerCardHand.addCard(HEART_13);
        
        // when
        final int sum = playerCardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(30);
    }
    
    @Test
    void 손패에_존재하는_카드의_숫자의_합을_계산할_수_있다() {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        playerCardHand.addCard(DIAMOND_1);
        playerCardHand.addCard(HEART_7);
        playerCardHand.addCard(HEART_10);
        
        // when
        final int sum = playerCardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(18);
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsAndSumWithBurst")
    void A를_11로_계산하여_버스트_되는_경우_A는_1로_계산된다(List<Card> cards, int expected) {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        for (Card card : cards) {
            playerCardHand.addCard(card);
        }
        playerCardHand.addCard(DIAMOND_1);
        
        // when
        final int sum = playerCardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(expected);
    }
    
    private static Stream<Arguments> provideCardsAndSumWithBurst() {
        return Stream.of(
                Arguments.of(List.of(HEART_10, HEART_5), 16),
                Arguments.of(List.of(HEART_10, HEART_4), 15),
                Arguments.of(List.of(HEART_10, HEART_3), 14),
                Arguments.of(List.of(HEART_10, HEART_2), 13),
                Arguments.of(List.of(HEART_10, HEART_1), 12)
        );
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsAndSumWithoutBurst")
    void A를_11로_계산하여_버스트_되지_않는_경우_A는_11로_계산된다(List<Card> cards, int expected) {
        // given
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        for (Card card : cards) {
            playerCardHand.addCard(card);
        }
        playerCardHand.addCard(DIAMOND_1);
        
        // when
        final int sum = playerCardHand.getSum();
        
        // then
        assertThat(sum).isEqualTo(expected);
    }
    
    private static Stream<Arguments> provideCardsAndSumWithoutBurst() {
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
        final PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        for (Card card : cards) {
            playerCardHand.addCard(card);
        }
        playerCardHand.addCard(DIAMOND_1);
        playerCardHand.addCard(HEART_1);
        
        // when
        final int sum = playerCardHand.getSum();
        
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
    void 손패_두_개가_주어졌을_때_두_손패_모두_버스트가_아니라면_21에_근접한_손패가_이긴다() {
        final PlayerCardHand winner = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        winner.addCard(HEART_10);
        winner.addCard(HEART_1);
        
        final PlayerCardHand looser = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        looser.addCard(HEART_9);
        looser.addCard(HEART_1);
        
        assertThat(winner.isWin(looser)).isEqualTo(WinningStatus.승리);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_한_손패가_버스트라면_버스트가_아닌_손패가_이긴다() {
        final PlayerCardHand winner = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        winner.addCard(DIAMOND_10);
        winner.addCard(HEART_10);
        winner.addCard(CLOVER_10);
        
        final PlayerCardHand looser = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        looser.addCard(DIAMOND_9);
        looser.addCard(HEART_1);
        
        assertThat(winner.isWin(looser)).isEqualTo(WinningStatus.패배);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_버스트라면_무승부이다() {
        final PlayerCardHand winner = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        winner.addCard(DIAMOND_10);
        winner.addCard(HEART_10);
        winner.addCard(CLOVER_10);
        
        final PlayerCardHand looser = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        looser.addCard(DIAMOND_9);
        looser.addCard(HEART_8);
        looser.addCard(HEART_7);
        
        assertThat(winner.isWin(looser)).isEqualTo(WinningStatus.무승부);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_모두_21이고_한쪽만_블랙잭이라면_블랙잭이_이긴다() {
        final PlayerCardHand winner = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        winner.addCard(DIAMOND_10);
        winner.addCard(DIAMOND_1);
        
        final PlayerCardHand looser = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        looser.addCard(DIAMOND_9);
        looser.addCard(HEART_9);
        looser.addCard(HEART_3);
        
        assertThat(winner.isWin(looser)).isEqualTo(WinningStatus.승리);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_모두_21이고_둘다_블랙잭이라면_무승부이다() {
        final PlayerCardHand winner = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        winner.addCard(DIAMOND_10);
        winner.addCard(DIAMOND_1);
        
        final PlayerCardHand looser = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        looser.addCard(HEART_10);
        looser.addCard(HEART_1);
        
        assertThat(winner.isWin(looser)).isEqualTo(WinningStatus.무승부);
    }
    
    @Test
    void 손패_두_개가_주어졌을_때_두_손패가_모두_21이고_둘다_블랙잭이_아니라면_무승부이다() {
        final PlayerCardHand winner = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        winner.addCard(DIAMOND_5);
        winner.addCard(DIAMOND_5);
        winner.addCard(DIAMOND_1);
        
        final PlayerCardHand looser = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerDummy());
        looser.addCard(HEART_5);
        looser.addCard(HEART_5);
        looser.addCard(HEART_1);
        
        assertThat(winner.isWin(looser)).isEqualTo(WinningStatus.무승부);
    }
    
    @Test
    void 내_손패가_버스트인지_알_수_있다() {
        // given
        final PlayerCardHand player = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerStub(List.of(
                HEART_10,
                DIAMOND_10,
                HEART_2
        )));
        
        // when
        final boolean result = player.isBurst();
        
        // then
        assertThat(result).isTrue();
    }
    
    @Test
    void 내_손패가_21인지_알_수_있다() {
        // given
        final PlayerCardHand player = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerStub(List.of(
                HEART_10,
                DIAMOND_1
        )));
        
        // when
        final boolean result = player.is21();
        
        // then
        assertThat(result).isTrue();
    }
    
    @Test
    void 플레이어는_처음에_두_장_공개해야_한다() {
        // given
        PlayerCardHand playerCardHand = new PlayerCardHand(DEFAULT_PLAYER, new CardHandInitializerStub(List.of(
                HEART_3,
                HEART_5
        )));
        
        // expected
        Assertions.assertThat(playerCardHand.getInitialCards()).containsExactly(HEART_3, HEART_5);
    }
}
