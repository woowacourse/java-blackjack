package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.NINE;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardHand;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.shuffler.RandomShuffler;
import fixture.CardFixture;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private final Deck deck = new Deck(new RandomShuffler());

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
        assertThat(dealer.canHit()).isTrue();
    }
}
