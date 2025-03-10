package domain;

import static domain.Rank.ACE;
import static domain.Rank.FOUR;
import static domain.Rank.NINE;
import static domain.Rank.SIX;
import static domain.Rank.TEN;
import static domain.Rank.THREE;
import static domain.Rank.TWO;
import static domain.Suit.CLOVER;
import static domain.Suit.DIAMOND;
import static domain.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import fixture.CardFixture;
import fixture.TestShuffler;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private final Deck deck = new Deck(new TestShuffler());

    @Test
    @DisplayName("자기 점수를 계산한다.")
    void testCalculateScore() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TWO, HEART), CardFixture.of(THREE, DIAMOND), CardFixture.of(FOUR, CLOVER)));
        Dealer dealer = new Dealer(deck, cardHand);
        // when
        int score = dealer.calculateScore();
        // then
        assertThat(score).isEqualTo(9);
    }

    @Test
    @DisplayName("자기가 버스트인지 판단한다.")
    void testIsBust() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND), CardFixture.of(TWO, CLOVER)));
        Dealer dealer = new Dealer(deck, cardHand);
        // when & then
        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    @DisplayName("자기가 블랙잭인지 판단한다.")
    void testIsBlackJack() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(ACE, DIAMOND)));
        Dealer dealer = new Dealer(deck, cardHand);
        // when & then
        assertThat(dealer.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드 2장의 합계가 16 이하인지 판단한다")
    void testDoesNeedCard() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(SIX, DIAMOND)));
        Dealer dealer = new Dealer(deck, cardHand);
        // when & then
        assertThat(dealer.doesNeedCard()).isTrue();
    }

    @Test
    @DisplayName("승패를 기록한다")
    void testRecordGameResult() {
        // given
        CardHand cardHand = new CardHand(Set.of(CardFixture.of(TEN, HEART)));
        Dealer dealer = new Dealer(deck, cardHand);
        // when
        dealer.recordGameResult(GameResult.WIN);
        // then
        assertThat(dealer.getGameResultCount(GameResult.WIN)).isEqualTo(1);
    }
}
