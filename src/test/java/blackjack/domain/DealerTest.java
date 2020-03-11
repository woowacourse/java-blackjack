package blackjack.domain;

import blackjack.domain.Card.CardDeck;
import blackjack.domain.Card.CardNumber;
import blackjack.domain.Card.Figure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 합이 16 초과하지 않는 것 확인")
    @Test
    void getMoreCardTest() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.addCard(cardDeck.of(CardNumber.FIVE, Figure.CLOVER));
        dealer.addCard(cardDeck.of(CardNumber.FIVE, Figure.HEART));

        boolean expected = true;
        assertThat(dealer.needMoreCard()).isEqualTo(expected);
    }

    @DisplayName("딜러의 카드 합이 16 초과할 때 확인")
    @Test
    void getMoreCardTest2() {
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.addCard(cardDeck.of(CardNumber.QUEEN, Figure.CLOVER));
        dealer.addCard(cardDeck.of(CardNumber.KING, Figure.CLOVER));

        boolean expected = false;
        assertThat(dealer.needMoreCard()).isEqualTo(expected);
    }


}
