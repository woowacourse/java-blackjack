package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("딜러가 카드 두장을 뽑는지 확인")
    void drawCardTest() {
        Gamer dealer = Dealer.init();
        dealer.hit();
        assertThat(dealer.getCards().getCards().size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("딜러가 16이하일 때 카드 추가하는지 확인")
    void checkHitAddCardTest() {
        Dealer dealer = Dealer.init();
        int dealerScore = dealer.getCards().calculateScore();
        dealer.hit();
        int cardSize = 2;
        if (dealerScore <= 16) {
            cardSize++;
        }
        assertThat(dealer.getCards().getCards().size()).isEqualTo(cardSize);
    }

    @Test
    @DisplayName("딜러의 승패 결과 확인")
    void dealerWinDrawLoseTest() {
        Dealer dealer = Dealer.init();
        dealer.win();
        dealer.win();
        dealer.lose();

        assertThat(dealer.getWinDrawLoseString()).isEqualTo("2승 1패");
    }
}
