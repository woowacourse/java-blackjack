package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domian.Card;
import blackjack.domian.Dealer;
import blackjack.domian.Deck;
import blackjack.domian.Player;
import blackjack.domian.Rank;
import blackjack.domian.Suit;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 플레이어_수가_최대_10명을_넘으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player(), new Player(), new Player(), new Player(),
                new Player(), new Player(), new Player(), new Player(),
                new Player(), new Player(), new Player()
        );

        //when & then
        assertThatThrownBy(() -> new Dealer(players, new Deck(new Stack<>())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최대 10명입니다.");
    }

    @Test
    void 플레이어_수가_최소_2명을_넘지_않으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player()
        );

        //when & then
        assertThatThrownBy(() -> new Dealer(players, new Deck(new Stack<>())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최소 2명입니다.");
    }

    @Test
    void 딜러는_한_플레이어에게_카드_2장씩_나누어준다() {
        //given

        List<Player> players = List.of(
                new Player(),
                new Player(),
                new Player()
        );
        Stack<Card> cards = new Stack<>();
        cards.addAll(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.TWO),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE))
        );
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(players, deck);

        //when
        dealer.handOutCard();

        //then
        assertThat(players.get(0).cards)
                .isEqualTo(Set.of(new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.FIVE)));
        assertThat(players.get(1).cards)
                .isEqualTo(Set.of(new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.THREE)));
        assertThat(players.get(2).cards)
                .isEqualTo(Set.of(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.ONE)));
    }
}
