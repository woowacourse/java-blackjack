package domain;

import static org.assertj.core.api.Assertions.assertThat;

import fixture.DealerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Rank;
import vo.Suit;

public class DealerTest {
    @Test
    @DisplayName("딜러가 카드를 받고, 한 장을 보여준다. - receiveCard(Card card) + getOneCardDisplay() + getCardsDisplay()")
    void 딜러_카드_받기_테스트() {
        // given
        Dealer dealer = DealerFixture.createDefaultDealer();

        // when
        dealer.receiveCard(new Card(Suit.SPADE, Rank.ACE));
        dealer.receiveCard(new Card(Suit.HEART, Rank.JACK));
        Card exceptedFirstCard = new Card(Suit.SPADE, Rank.ACE);
        String expectedCardsResult = "A스페이드, J하트";

        // then
        assertThat(dealer.getFirstCard()).isEqualTo(exceptedFirstCard);
        assertThat(dealer.getCardsDisplay()).isEqualTo(expectedCardsResult);
    }

    @Test
    @DisplayName("딜러가 가진 카드 점수를 계산한다. - calculateScore() + getTotalScore()")
    void 딜러_카드_점수_계산_테스트() {
        // given
        Dealer dealer = DealerFixture.createDefaultDealer();
        dealer.receiveCard(new Card(Suit.SPADE, Rank.ACE));
        dealer.receiveCard(new Card(Suit.HEART, Rank.JACK));

        // when
        dealer.calculateScore();

        // then
        assertThat(dealer.getTotalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("딜러는 카드 합이 16점 이하이면 카드를 더 받아야 한다.")
    void 딜러_카드_히트_조건_충족_테스트() {
        // given
        Dealer dealer = DealerFixture.createDealerWithCards(
                List.of(new Card(Suit.SPADE, Rank.ACE),
                        new Card(Suit.CLUB, Rank.FIVE))
        );

        // when & then
        assertThat(dealer.determineDealerDealMore()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 합이 17점 이상이면 카드를 더 받을 수 없다.")
    void 딜러_카드_히트_조건_불충족_테스트() {
        // given
        Dealer dealer = DealerFixture.createDealerWithCards(
                List.of(new Card(Suit.SPADE, Rank.ACE),
                        new Card(Suit.CLUB, Rank.SIX))
        );

        // when & then
        assertThat(dealer.determineDealerDealMore()).isFalse();
    }

    @Test
    @DisplayName("처음 받은 두 장의 카드 합이 21점인 경우, 블랙잭이다.")
    void 딜러_카드_두_장_블랙잭_테스트() {
        // given
        Dealer dealer = DealerFixture.createDealerWithCards(
                List.of(new Card(Suit.SPADE, Rank.ACE),
                        new Card(Suit.CLUB, Rank.JACK))
        );

        // when & then
        assertThat(dealer.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드가 3장 이상이고, 합이 21점인 경우, 블랙잭이 아니다.")
    void 딜러_카드_두_장_초과_블랙잭_아님_테스트() {
        // given
        Dealer dealer = DealerFixture.createDealerWithCards(
                List.of(new Card(Suit.SPADE, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.SEVEN),
                        new Card(Suit.HEART, Rank.SEVEN))
        );

        // when & then
        assertThat(dealer.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드 합이 21점 초과인 경우, 버스트다.")
    void 딜러_카드_버스트_초과_테스트() {
        // given
        Dealer dealer = DealerFixture.createDealerWithCards(
                List.of(new Card(Suit.SPADE, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.JACK))
        );

        // when & then
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("카드 합이 21점 이하인 경우, 버스트가 아니다.")
    void 딜러_카드_버스트_이하_테스트() {
        // given
        Dealer dealer = DealerFixture.createDealerWithCards(
                List.of(new Card(Suit.SPADE, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.SEVEN))
        );

        // when & then
        assertThat(dealer.isBust()).isFalse();
    }
}
