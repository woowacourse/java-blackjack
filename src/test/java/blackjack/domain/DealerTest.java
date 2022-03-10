package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러가 16이하일 때만 카드 추가하는지 확인")
    void checkHitAddCardTest() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        while (dealer.checkHitFlag()) {
            dealer.hit(deck.draw());
        }
        int dealerScore = dealer.getCards().calculateScore();
        assertThat(dealerScore > 16).isTrue();
    }

    @Test
    @DisplayName("딜러의 승패 결과 확인")
    void dealerWinDrawLoseTest() {
        Gamer dealer = new Dealer();
        dealer.win();
        dealer.win();
        dealer.lose();
        assertThat(dealer.getWinDrawLoseString()).isEqualTo("2승 1패");
    }
}
