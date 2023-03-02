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

    @Test
    void addCard() {
    }

    @Test
    void isHit() {
    }

    @Test
    void calculateScore() {
    }
}
