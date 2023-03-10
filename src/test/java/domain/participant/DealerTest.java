package domain.participant;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dealer는 ")
class DealerTest {

    @Test
    void 카드를_가질_수_있다() {
        //given
        Dealer dealer = new Dealer();
        int beforeCount = dealer.getCards().size();

        //when
        dealer.addCard(new Card(Denomination.ACE, Suit.CLUB));

        //then
        int afterCount = dealer.getCards().size();

        assertThat(beforeCount + 1).isEqualTo(afterCount);
    }

    @Test
    void 카드들의_합을_계산_할_수_있다() {
        //given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(new Card(Denomination.ACE, Suit.CLUB), new Card(Denomination.EIGHT, Suit.HEART));
        cards.forEach(dealer::addCard);

        //when
        int totalScore = dealer.calculateScore();

        //then
        assertThat(totalScore).isEqualTo(19);
    }

    @Test
    void 카드들의_합이_21_이하라면_bust_가_아니다() {
        //given
        Dealer dealer = new Dealer();

        //when

        //then
        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    void 카드들의_합이_21_초과라면_bust() {
        //given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(new Card(Denomination.FIVE, Suit.CLUB),
                new Card(Denomination.TEN, Suit.HEART),
                new Card(Denomination.TEN, Suit.SPADE));

        //when
        cards.forEach(dealer::addCard);

        //then
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    void 히트_여부를_계산할_수_있다() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(new Card(Denomination.FIVE, Suit.CLUB),
                new Card(Denomination.TEN, Suit.SPADE));

        //when
        cards.forEach(dealer::addCard);

        // then
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    void 초기에_먼저_받은_한_장의_카드만_보여줄_수_있다() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(new Card(Denomination.FIVE, Suit.CLUB),
                new Card(Denomination.TEN, Suit.SPADE));

        //when
        cards.forEach(dealer::addCard);

        // then
        assertThat(dealer.getCardWithInvisible()).isEqualTo(new Card(Denomination.FIVE, Suit.CLUB));
    }
}
