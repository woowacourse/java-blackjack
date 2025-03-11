package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 점수가_16이하일때_카드를_계속_뽑는다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );
        Dealer dealer = new Dealer(new Cards());

        //when
        dealer.drawAdditionalCard(deck);

        //then
        assertThat(dealer).isEqualTo(new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        )));
    }

    @Test
    void 카드를_2장_준비한다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );
        Dealer dealer = new Dealer(new Cards());

        //when
        dealer.prepareCards(deck);

        //then
        assertThat(dealer).isEqualTo(new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        )));
    }
}
