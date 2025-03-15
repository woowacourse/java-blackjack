package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.state.Created;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.running.Hit;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 블랙잭_게임을_준비한다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );
        Dealer dealer = new Dealer(new Created());

        //when
        dealer.prepareBlackjack(deck);

        //then
        assertThat(dealer).isEqualTo(new Dealer(
                new Hit(new Cards(new Card(Suit.CLUB, Rank.EIGHT), new Card(Suit.DIAMOND, Rank.NINE)))
        ));
    }

    @Test
    void 점수가_16이하일때_카드를_계속_뽑는다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );
        Dealer dealer = new Dealer(new Hit(new Cards()));

        //when
        dealer.drawAdditionalCard(deck);

        //then
        assertThat(dealer).isEqualTo(new Dealer(new Hit(
                new Cards(
                        new Card(Suit.CLUB, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.NINE)
                )
        )));
    }

    @Test
    void 수익을_계산할_수_있다() {
        //given
        Dealer dealer = new Dealer(new Stay(
                new Cards(
                        new Card(Suit.CLUB, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.NINE)
                )
        ));

        Players players = new Players(
                new Player("neo",
                        new Stay(new Cards(new Card(Suit.DIAMOND, Rank.TEN), new Card(Suit.SPADE, Rank.TEN))),
                        new BettingMoney(10000)),
                new Player("neo",
                        new Stay(new Cards(new Card(Suit.DIAMOND, Rank.TEN), new Card(Suit.SPADE, Rank.SIX))),
                        new BettingMoney(20000))
        );

        //when
        int profit = dealer.calculateProfit(players);

        //then
        assertThat(profit).isEqualTo(10000);
    }
}
