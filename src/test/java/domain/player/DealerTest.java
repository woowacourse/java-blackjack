package domain.player;


import static org.assertj.core.api.Assertions.assertThat;

import domain.FixedDeck;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("처음 출력할 때 1장만 나오게 함")
    void 딜러는_처음_출력_때_1장만_나오게_함() {
        //given
        Card five = new Card(CardRank.FIVE, CardSuit.CLOVER);
        Card ten = new Card(CardRank.TEN, CardSuit.DIAMOND);
        FixedDeck sd = new FixedDeck(List.of(five, ten));

        Dealer dealer = new Dealer();
        dealer.deal(sd);
        dealer.deal(sd);

        //when
        Card card = dealer.getFirstCard();

        //then
        assertThat(card.getCardRank()).isEqualTo(CardRank.FIVE);
        assertThat(card.getCardSuit()).isEqualTo(CardSuit.CLOVER);
    }

    @Test
    @DisplayName("딜러는 16이하 강제 히트")
    void 딜러는_16이하_강제_히트() {
        //given
        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER);
        Card five = new Card(CardRank.FIVE, CardSuit.CLOVER);
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        FixedDeck sd = new FixedDeck(List.of(jack, five, ten));

        Dealer dealer = new Dealer();
        dealer.deal(sd);
        dealer.deal(sd);

        //when
        boolean canStand = dealer.canStand();

        //then
        assertThat(canStand).isFalse();
    }
}
