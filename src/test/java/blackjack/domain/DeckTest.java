package blackjack.domain;

import blackjack.domian.Card;
import blackjack.domian.Deck;
import blackjack.domian.Rank;
import blackjack.domian.Suit;
import java.util.List;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드를_카드_셔플러로_섞을_수_있다() {
        //given
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.CLUB, Rank.TEN)
        ));
        Deck deck = new Deck(cards, new FixCardsShuffler());

        //when
        deck.shuffleCards();

        //then
        Assertions.assertThat(deck.getCards()).isEqualTo(cards);
    }
}
