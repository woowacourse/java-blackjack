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
    void 블랙잭_게임을_준비한다() {
        //given
        Players players = new Players(
                List.of(new Player("pobi", new Cards(new ArrayList<>(), new ScoreCalculator())),
                        new Player("surf", new Cards(new ArrayList<>(), new ScoreCalculator()))
                ));
        Stack<Card> cards = new Stack<>();
        cards.addAll(List.of(
                new Card(Suit.CLUB, Rank.FOUR),
                new Card(Suit.CLUB, Rank.FIVE),
                new Card(Suit.CLUB, Rank.ONE),
                new Card(Suit.DIAMOND, Rank.ONE),
                new Card(Suit.HEART, Rank.ONE),
                new Card(Suit.SPADE, Rank.ONE)
        ));

        Dealer dealer = new Dealer(
                players,
                new Deck(cards),
                scoreCalculator);

        //when
        dealer.prepareBlackjack(new FixCardsShuffler());

        //then
        Assertions.assertThat(dealer.getCards())
                .isEqualTo(List.of(
                        new Card(Suit.SPADE, Rank.ONE),
                        new Card(Suit.HEART, Rank.ONE)
                ));
        Assertions.assertThat(players.getPlayers().get(0).getCards())
                .isEqualTo(List.of(
                        new Card(Suit.DIAMOND, Rank.ONE),
                        new Card(Suit.CLUB, Rank.ONE)));
        Assertions.assertThat(players.getPlayers().get(1).getCards())
                .isEqualTo(List.of(
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.CLUB, Rank.FOUR)));

    }

    @Test
    void 딜러의_카드쌍의_합이_16이하인_경우_16초과가_될_때_까지_카드를_추가로_뽑는다() {
        //given
        Players players = new Players(
                List.of(
                        new Player("pobi", new Cards(new ArrayList<>(), new ScoreCalculator())),
                        new Player("surf", new Cards(new ArrayList<>(), new ScoreCalculator())),
                        new Player("fora", new Cards(new ArrayList<>(), new ScoreCalculator()))
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
