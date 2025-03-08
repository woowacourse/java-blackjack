package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 카드를_카드_셔플러로_섞을_수_있다() {
        //given
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.CLUB, Rank.TEN)
        ));
        Deck deck = new Deck(cards);

        //when
        deck.shuffleCards(new FixCardsShuffler());

        //then
        assertThat(deck)
                .extracting("cards")
                .isEqualTo(cards);
    }

    @Test
    void 덱에_존재하는_하나의_카드를_들고올_수_있다() {
        //given
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.CLUB, Rank.TEN)
        ));
        Deck deck = new Deck(cards);

        //when
        Card drawCard = deck.draw();

        //then
        assertThat(drawCard).isEqualTo(new Card(Suit.CLUB, Rank.EIGHT));
    }

    @Test
    void 남아있는_카드가_없다면_카드를_뽑을_수_없다() {
        //given
        Deck deck = new Deck(new ArrayDeque<>());

        //when
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("남아있는 카드가 없습니다.");
    }
}
