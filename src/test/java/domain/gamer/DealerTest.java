package domain.gamer;

import static domain.BlackJackConstants.THRESHOLD;
import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("에이스가 없을 때 딜러의 카드가 기준을 넘으면 true를 반환한다.")
    @Test
    void 에이스가_없을_때_딜러의_카드가_기준을_넘으면_true를_반환한다() {

        // given
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(Rank.JACK, Shape.CLOVER));
        dealer.hit(new Card(Rank.SIX, Shape.CLOVER));

        // when
        final boolean expected = dealer.canHit();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealer.getSumOfRank()).isEqualTo(THRESHOLD);
            softly.assertThat(expected).isTrue();
        });
    }

    @DisplayName("에이스가 없을 때딜러의 카드가 기준을 넘지 않으면 false를 반환한다.")
    @Test
    void 에이스가_없을_때_딜러의_카드가_기준을_넘지_않으면_false를_반환한다() {

        // given
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(Rank.JACK, Shape.CLOVER));
        dealer.hit(new Card(Rank.SEVEN, Shape.CLOVER));

        // when
        final boolean expected = dealer.canHit();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealer.getSumOfRank()).isEqualTo(17);
            softly.assertThat(expected).isFalse();
        });
    }

    @DisplayName("에이스가 있을 때 에이스 1장을 11로 가정한 카드의 합계가 기준값을 안 넘을 시 true를 반환한다.")
    @Test
    void 에이스가_있을_때_에이스_1장을_11로_가정한_카드의_합계가_기준값을_안_넘을_시_true를_반환한다() {

        // given
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(Rank.ACE, Shape.CLOVER));
        dealer.hit(new Card(Rank.TWO, Shape.CLOVER));
        // when
        final boolean expected = dealer.canHit();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealer.getSumOfRank()).isEqualTo(13);
            softly.assertThat(expected).isTrue();
        });
    }

    @DisplayName("에이스가 있을 때 에이스 1장을 11로 가정한 카드의 합계가 기준값을 넘을 시 false를 반환한다.")
    @Test
    void 에이스가_있을_때_에이스_1장을_11로_가정한_카드의_합계까_기준값을_넘을_시_false를_반환한다() {

        // given
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(Rank.JACK, Shape.CLOVER));
        dealer.hit(new Card(Rank.ACE, Shape.CLOVER));

        // when
        final boolean expected = dealer.canHit();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealer.getSumOfRank()).isEqualTo(21);
            softly.assertThat(expected).isFalse();
        });
    }

    @DisplayName("딜러의 첫번째 카드를 가져온다.")
    @Test
    void 딜러의_첫번째_카드를_가져온다() {

        // given
        final Dealer dealer = new Dealer();
        final Card card1 = new Card(Rank.JACK, Shape.CLOVER);
        final Card card2 = new Card(Rank.ACE, Shape.CLOVER);
        dealer.receiveInitialCards(List.of(card1, card2));

        // when
        final Card dealerFirstCard = dealer.getFirstCard();

        // then
        assertThat(dealerFirstCard).isEqualTo(card1);
    }

    @DisplayName("블랙잭이면 true를 반환한다.")
    @Test
    void 블랙잭이면_true를_반환한다() {

        // given
        final Dealer dealer = new Dealer();

        // when
        dealer.hit(new Card(Rank.JACK, Shape.CLOVER));
        dealer.hit(new Card(Rank.ACE, Shape.CLOVER));

        // then
        assertThat(dealer.isBlackJack()).isTrue();
    }

    @DisplayName("블랙잭이 아니라면 false를 반환한다.")
    @Test
    void 블랙잭이_아니라면_false를_반환한다() {

        // given
        final Dealer dealer = new Dealer();

        // when
        dealer.hit(new Card(Rank.JACK, Shape.CLOVER));
        dealer.hit(new Card(Rank.FIVE, Shape.CLOVER));
        dealer.hit(new Card(Rank.SIX, Shape.CLOVER));

        // then
        assertThat(dealer.isBlackJack()).isFalse();
    }
}
