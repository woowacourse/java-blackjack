package domain;

import static domain.deck.Rank.ACE;
import static domain.deck.Rank.FOUR;
import static domain.deck.Rank.NINE;
import static domain.deck.Rank.SIX;
import static domain.deck.Rank.TEN;
import static domain.deck.Rank.THREE;
import static domain.deck.Rank.TWO;
import static domain.deck.Suit.CLOVER;
import static domain.deck.Suit.DIAMOND;
import static domain.deck.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Deck;
import domain.deck.DeckGenerator;
import fixture.CardFixture;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private final Deck deck = DeckGenerator.generateRandomDeck();

    @Test
    @DisplayName("자기 점수를 계산한다.")
    void testCalculateScore() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TWO, HEART), CardFixture.of(THREE, DIAMOND), CardFixture.of(FOUR, CLOVER)));
        Dealer dealer = new Dealer(deck, cardHand);
        // when
        GameScore score = dealer.calculateScore();
        // then
        assertThat(score).isEqualTo(new GameScore(9));
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
