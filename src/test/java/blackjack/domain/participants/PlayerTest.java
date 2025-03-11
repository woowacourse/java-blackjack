package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 카드를_2장_준비한다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );
        Player player = new Player("pobi", new Cards(), 0);

        //when
        player.prepareCards(deck);

        //then
        assertThat(player).isEqualTo(new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        ), 0));
    }

    @Test
    void 카드를_한장_뽑는다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );
        Player player = new Player("pobi", new Cards(), 0);

        //when
        player.drawCard(deck);

        //then
        assertThat(player).isEqualTo(new Player("pobi", new Cards(new Card(Suit.CLUB, Rank.EIGHT)), 0));
    }

    @Test
    void 딜러에게_승리한다면_수익은_배팅금액의_2배이다() {
        //given
        Dealer dealer = new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));
        Player player = new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.NINE)
        ), 10000);

        //when
        int result = player.calculateRevenue(dealer);

        //then
        assertThat(result).isEqualTo(10000);
    }

    @Test
    void 딜러에게_패매한다면_배팅금액만큼_손해이다() {
        //given
        Dealer dealer = new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));
        Player player = new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        ), 10000);

        //when
        int result = player.calculateRevenue(dealer);

        //then
        assertThat(result).isEqualTo(-10000);
    }

    @Test
    void 딜러와_무승부라면_수익은_0원이다() {
        //given
        Dealer dealer = new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));
        Player player = new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        ), 10000);

        //when
        int result = player.calculateRevenue(dealer);

        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void 플레이어가_버스트라면_배팅금액만큼_손해이다() {
        //given
        Dealer dealer = new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));
        Player player = new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.HEART, Rank.NINE)
        ), 10000);

        //when
        int result = player.calculateRevenue(dealer);

        //then
        assertThat(result).isEqualTo(-10000);
    }

    @Test
    void 플레이어가_블랙잭이면서_승리한다면_수익은_1점5배이다() {
        //given
        Dealer dealer = new Dealer(new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        ));
        Player player = new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.TEN)
        ), 10000);

        //when
        int result = player.calculateRevenue(dealer);

        //then
        assertThat(result).isEqualTo(5000);
    }
}
