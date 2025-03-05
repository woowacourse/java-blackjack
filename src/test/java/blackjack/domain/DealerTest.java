package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private Stack<Card> deck;
    private ScoreCalculator scoreCalculator;

    @BeforeEach
    public void setUp() {
        deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        scoreCalculator = new ScoreCalculator();
    }


    @Test
    void 딜러는_본인의_카드_2장을_스스로_뽑는다() {
        //given
        Dealer dealer = new Dealer(
                new Players(
                        List.of(new Player("pobi", List.of(), scoreCalculator),
                                new Player("surf", List.of(), scoreCalculator))
                ),
                new Deck(deck), scoreCalculator);

        //when
        dealer.pickCards();

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
        Players players = new Players(
                List.of(
                        new Player("pobi", new ArrayList<>(), scoreCalculator),
                        new Player("surf", new ArrayList<>(), scoreCalculator),
                        new Player("fora", new ArrayList<>(), scoreCalculator)
                )
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
        Dealer dealer = new Dealer(players, deck, scoreCalculator);

        //when
        dealer.handOutCard();

        //then
        Assertions.assertThat(players.getPlayers().get(0).getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.CLUB, Rank.FOUR)));
        Assertions.assertThat(players.getPlayers().get(1).getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.TWO)));
        Assertions.assertThat(players.getPlayers().get(2).getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.ACE)));
    }

    @Test
    void 딜러의_카드쌍의_합이_16이하인_경우_16초과가_될_때_까지_카드를_추가로_뽑는다() {
        //given
        Players players = new Players(
                List.of(
                        new Player("pobi", new ArrayList<>(), scoreCalculator),
                        new Player("surf", new ArrayList<>(), scoreCalculator),
                        new Player("fora", new ArrayList<>(), scoreCalculator)
                )
        );
        Stack<Card> cards = new Stack<>();
        cards.addAll(
                List.of(
                        new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.CLUB, Rank.ONE),
                        new Card(Suit.CLUB, Rank.TEN),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE))
        );
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(players, deck, scoreCalculator);

        //when
        dealer.pickAdditionalCard();

        //then
        Assertions.assertThat(dealer.getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.THREE),
                        new Card(Suit.CLUB, Rank.TEN)
                ));
    }
}
