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
        Player player = new Player("pobi", new Cards());

        //when
        player.prepareCards(deck);

        //then
        assertThat(player).isEqualTo(new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE)
        )));
    }

    @Test
    void 카드를_한장_뽑는다() {
        //given
        Deck deck = new Deck(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );
        Player player = new Player("pobi", new Cards());

        //when
        player.drawCard(deck);

        //then
        assertThat(player).isEqualTo(new Player("pobi", new Cards(
                new Card(Suit.CLUB, Rank.EIGHT)
        )));
    }

}
