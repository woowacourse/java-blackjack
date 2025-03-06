package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Rank;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinnerDeciderTest {


    @Test
    void 딜러의_점수와_같으면_무승부다() {
        //given
        Player pobi = new Player(
                "pobi",
                new Cards(Arrays.asList(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.CLUB, Rank.TEN)
                ), new ScoreCalculator()));
        List<Card> dealerCards = List.of(
                new Card(Suit.HEART, Rank.NINE),
                new Card(Suit.HEART, Rank.TEN)
        );
        Dealer dealer = new Dealer(
                new Players(
                        List.of(
                                pobi,
                                new Player("neo", new Cards(new ArrayList<>(), new ScoreCalculator()))
                        )
                ),
                new Deck(new Stack<>()),
                new Cards(dealerCards, new ScoreCalculator()));

        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        //when
        WinningResult result = winnerDecider.decidePlayerWinning(pobi);

        //then
        assertThat(result).isEqualTo(WinningResult.DRAW);
    }

    @Test
    void 딜러보다_점수가_낮다면_패배이다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.CLUB, Rank.EIGHT),
                new Card(Suit.HEART, Rank.TEN)
        );

        Player pobi = new Player(
                "pobi",
                new Cards(playerCards, new ScoreCalculator()));
        Dealer dealer = new Dealer(
                new Players(List.of(pobi, new Player("neo", new Cards(new ArrayList<>(), new ScoreCalculator())))),
                new Deck(new Stack<>()),
                new Cards(dealerCards, new ScoreCalculator()));

        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        //when
        WinningResult result = winnerDecider.decidePlayerWinning(pobi);

        //then
        assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 딜러보다_점수보다_높다면_승리이다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TEN)
        );

        Player pobi = new Player(
                "pobi",
                new Cards(playerCards, new ScoreCalculator())
        );
        Dealer dealer = new Dealer(
                new Players(List.of(pobi, new Player("neo", new Cards(new ArrayList<>(), new ScoreCalculator())))),
                new Deck(new Stack<>()),
                new Cards(dealerCards, new ScoreCalculator()));

        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        //when
        WinningResult result = winnerDecider.decidePlayerWinning(pobi);

        //then
        assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 딜러가_21을_초과하면_남아있는_플레이어들의_승리이다() {
        //given
        Player pobi = new Player(
                "pobi",
                new Cards(List.of(
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.CLUB, Rank.TEN)
                ), new ScoreCalculator()));
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.NINE),
                new Card(Suit.CLUB, Rank.SEVEN),
                new Card(Suit.CLUB, Rank.EIGHT)
        );
        Dealer dealer = new Dealer(
                new Players(List.of(pobi, new Player("neo", new Cards(new ArrayList<>(), new ScoreCalculator())))),
                new Deck(new Stack<>()),
                new Cards(dealerCards, new ScoreCalculator()));

        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        //when
        WinningResult result = winnerDecider.decidePlayerWinning(pobi);

        //then
        assertThat(result).isEqualTo(WinningResult.WIN);
    }

    @Test
    void 딜러가_블랙잭일_경우_딜러가_승리한다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.HEART, Rank.TEN),
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.ONE)
        );

        Player pobi = new Player(
                "pobi",
                new Cards(playerCards, new ScoreCalculator()));
        Dealer dealer = new Dealer(
                new Players(List.of(pobi, new Player("neo", new Cards(new ArrayList<>(), new ScoreCalculator())))),
                new Deck(new Stack<>()),
                new Cards(dealerCards, new ScoreCalculator()));

        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        //when
        WinningResult result = winnerDecider.decidePlayerWinning(pobi);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.LOSE);
    }

    @Test
    void 딜러와_플레이어_모두_블랙잭인_경우_무승부이다() {
        //given
        List<Card> dealerCards = List.of(
                new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.HEART, Rank.TEN)
        );

        List<Card> playerCards = List.of(
                new Card(Suit.HEART, Rank.ACE),
                new Card(Suit.CLUB, Rank.TEN)
        );

        Player pobi = new Player(
                "pobi",
                new Cards(playerCards, new ScoreCalculator()));
        Dealer dealer = new Dealer(
                new Players(List.of(pobi, new Player("neo", new Cards(new ArrayList<>(), new ScoreCalculator())))),
                new Deck(new Stack<>()),
                new Cards(dealerCards,
                        new ScoreCalculator()));

        WinnerDecider winnerDecider = new WinnerDecider(dealer);

        //when
        WinningResult result = winnerDecider.decidePlayerWinning(pobi);

        //then
        Assertions.assertThat(result).isEqualTo(WinningResult.DRAW);
    }
}
