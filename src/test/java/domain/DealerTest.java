package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Number;
import domain.card.Suit;
import domain.participant.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}



