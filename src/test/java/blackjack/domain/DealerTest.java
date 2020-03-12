package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardFigure;
import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 합이 16 초과하지 않는 것 확인")
    @Test
    void getMoreCardTest() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.addCard(cardDeck.of(CardNumber.FIVE, CardFigure.CLOVER));
        dealer.addCard(cardDeck.of(CardNumber.FIVE, CardFigure.HEART));

        boolean expected = true;
        assertThat(dealer.needMoreCard()).isEqualTo(expected);
    }

    @DisplayName("딜러의 카드 합이 16 초과할 때 확인")
    @Test
    void getMoreCardTest2() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.addCard(cardDeck.of(CardNumber.QUEEN, CardFigure.CLOVER));
        dealer.addCard(cardDeck.of(CardNumber.KING, CardFigure.CLOVER));

        boolean expected = false;
        assertThat(dealer.needMoreCard()).isEqualTo(expected);
    }


}
