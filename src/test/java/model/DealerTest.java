package model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 객체가 잘 생성되는 지")
    void newDealer() {

        // given
        final Dealer dealer = new Dealer();
        String expected = "딜러";

        // when
        String nickname = dealer.getNickname();

        // then
        Assertions.assertThat(nickname).isEqualTo(expected);
    }
    @Test
    @DisplayName("카드 추가 기능이 잘 작동하는 지")
    void addCardsSuccess() {

        // given
        final int amount = 2;
        final List<Card> cards = new CardDeck().pickCard(2);

        // when
        Dealer dealer = new Dealer();
        dealer.addCards(cards);

        // then
        Assertions.assertThat(dealer.getHands().size()).isEqualTo(amount);
    }
}
