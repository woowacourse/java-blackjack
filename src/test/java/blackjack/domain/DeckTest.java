package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
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
        Deck deck = new Deck(cards);

        //when
        deck.shuffleCards(new FixCardsShuffler());

        //then
        Assertions.assertThat(deck.getCards()).isEqualTo(cards);
    }
}
