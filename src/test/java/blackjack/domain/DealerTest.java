package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

    @Test
    void addCard() {
    }

    @Test
    void showCards() {
    }

    @Test
    void isHit() {
    }

    @Test
    void calculateScore() {
    }
}
