package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewDealer() {
        assertDoesNotThrow(Dealer::new);
    }

    @DisplayName("딜러에게 카드를 전달하면 딜러는 카드를 받는다.")
    @Test
    void Should_Success_When_AddCard() {
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        Dealer dealer = new Dealer();
        dealer.addCard(card);
        assertThat(dealer.showCards()).contains(card);
    }

    @DisplayName("딜러가 가진 카드를 가져올 수 있다.")
    @Test
    void Should_Success_When_ShowCards() {
        // given
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Dealer dealer = new Dealer();

        dealer.addCard(card);
        dealer.addCard(card2);
        dealer.addCard(card3);

        // when, then
        assertThat(dealer.showCards()).containsAll(List.of(card, card2, card3));
    }

    @DisplayName("딜러가 가진 카드의 점수 합을 구할 수 있다.")
    @Test
    void Should_Success_When_CalculateScore() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.KING, CardSymbol.HEARTS);

        Dealer dealer = new Dealer();

        dealer.addCard(card);
        dealer.addCard(card2);

        // when, then
        assertThat(dealer.calculateScore()).isEqualTo(20);
    }

    @DisplayName("딜러가 A를 가지고 있을 때 딜러의 점수 합이 21 이하면(버스트가 아니면) A는 11점으로 간주한다.")
    @Test
    void Should_AIs11_When_NotBurst() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Dealer dealer = new Dealer();

        dealer.addCard(card);
        dealer.addCard(card2);

        // when, then
        assertThat(dealer.calculateScore()).isEqualTo(21);
    }

    @DisplayName("딜러가 A를 가지고 있을 때 딜러의 점수 합이 21을 초과하면(burst이면) A는 1점으로 간주한다.")
    @Test
    void Should_AIs1_When_Burst() {
        // given
        Card card = new Card(CardNumber.NINE, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.TWO, CardSymbol.HEARTS);
        Card card3 = new Card(CardNumber.ACE, CardSymbol.HEARTS);

        Dealer dealer = new Dealer();

        dealer.addCard(card);
        dealer.addCard(card2);
        dealer.addCard(card3);

        // when, then
        assertThat(dealer.calculateScore()).isEqualTo(12);
    }


    @DisplayName("딜러는 카드의 합이 17 이상일 경우 카드를 지급 받을 수 없다.")
    @Test
    void Should_isHitFalse_When_MoreThan17() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.SEVEN, CardSymbol.HEARTS);

        Dealer dealer = new Dealer();

        dealer.addCard(card);
        dealer.addCard(card2);

        // when, then
        assertThat(dealer.isHit()).isFalse();
    }

    @DisplayName("딜러는 카드의 합이 17을 초과하지 않는다면 카드를 지급 받을 수 있다.")
    @Test
    void Should_isHitTrue_When_LessThan17() {
        // given
        Card card = new Card(CardNumber.JACK, CardSymbol.HEARTS);
        Card card2 = new Card(CardNumber.SIX, CardSymbol.HEARTS);

        Dealer dealer = new Dealer();

        dealer.addCard(card);
        dealer.addCard(card2);

        // when, then
        assertThat(dealer.isHit()).isTrue();
    }
}
