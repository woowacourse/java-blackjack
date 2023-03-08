package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("딜러의 카드가 16이하라면 카드를 한장 더 받을 수 있다")
    void canAddCardTrue() {
        //given 
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.FIVE), new Card(Suit.CLOVER, Number.SIX)));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.canAddCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드가 16을 초과하면 카드를 한장 더 받을 수 없다")
    void canAddCardFalse() {
        //given
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.A), new Card(Suit.CLOVER, Number.SIX)));

        //when
        Dealer dealer = new Dealer(cards);

        //then
        assertThat(dealer.canAddCard()).isFalse();
    }

    @Test
    @DisplayName("딜러가 뽑은 카드를 자신의 카드 목록에 추가할 경우 카드의 개수가 한장 추가된다")
    void pickCard() {
        //given
        CardDeck cardDeck = new CardDeck();
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(Suit.CLOVER, Number.A), new Card(Suit.DIAMOND, Number.K))));
        Dealer dealer = new Dealer(cards);
        int cardSize = dealer.getCards().getSize();

        //when
        Card card = dealer.pickCard(cardDeck);
        dealer.hit(card);

        //then
        assertThat(dealer.getCards().getSize()).isEqualTo(cardSize + 1);
    }
}



