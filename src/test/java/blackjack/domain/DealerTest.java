package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domian.Card;
import blackjack.domian.Dealer;
import blackjack.domian.Deck;
import blackjack.domian.Player;
import blackjack.domian.Rank;
import blackjack.domian.Suit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {
    Stack<Card> deck;

    @BeforeEach
    public void setUp() {
        deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }

    }

    @Test
    void 플레이어_수가_최대_10명을_넘으면_예외가_발생한다() {
        //given
        List<Player> players = List.of(
                new Player(List.of()), new Player(List.of()), new Player(List.of()), new Player(List.of()),
                new Player(List.of()), new Player(List.of()), new Player(List.of()), new Player(List.of()),
                new Player(List.of()), new Player(List.of()), new Player(List.of())
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
                new Player(List.of())
        );

        //when & then
        assertThatThrownBy(() -> new Dealer(players, new Deck(new Stack<>())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 수는 최소 2명입니다.");
    }

    @Test
    void 딜러는_본인의_카드_2장을_스스로_뽑는다() {
        //given
        Dealer dealer = new Dealer(List.of(new Player(List.of()), new Player(List.of())), new Deck(deck));

        //when
        dealer.pickCars();

        //then
        Assertions.assertThat(dealer.getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.CLUB, Rank.KING)
                ));
    }

    @Test
    void 딜러는_한_플레이어에게_카드_2장씩_나누어준다() {
        //given

        List<Player> players = List.of(
                new Player(new ArrayList<>()),
                new Player(new ArrayList<>()),
                new Player(new ArrayList<>())
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
        assertThat(players.get(0).getCards())
                .isEqualTo(Set.of(new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.FIVE)));
        assertThat(players.get(1).getCards())
                .isEqualTo(Set.of(new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.THREE)));
        assertThat(players.get(2).getCards())
                .isEqualTo(Set.of(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.ONE)));
    }
}
