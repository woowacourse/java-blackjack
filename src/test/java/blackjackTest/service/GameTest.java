package blackjackTest.service;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.*;
import blackjack.service.CardDistributor;
import blackjack.service.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void dealer_should_draw_card_until_score_at_least_17() {
             List<Card> cards = new ArrayList<>(List.of(
                new Card(Rank.EIGHT, Shape.HEART), // 8
                new Card(Rank.NINE, Shape.SPADE)   // 9
        ));

        CardPicker cardPicker = cards::removeFirst;

        CardDistributor cardDistributor = new CardDistributor(cardPicker);
        Game game = new Game(cardDistributor);
        Dealer dealer = new Dealer();

        game.dealerDrawsCardsUntilDone(dealer);
        assertThat(dealer.calculateTotalScore()).isEqualTo(17);
    }

    @Test
    void judge_total_winner_result() {
        Player pobi = createPlayer("pobi",
                new Card(Rank.TWO, Shape.HEART),
                new Card(Rank.EIGHT, Shape.SPADE),
                new Card(Rank.ACE, Shape.CLOVER));
        Player jason = createPlayer("jason",
                new Card(Rank.SEVEN, Shape.CLOVER),
                new Card(Rank.KING, Shape.SPADE));
        Player brown = createPlayer("brown",
                new Card(Rank.TEN, Shape.HEART),
                new Card(Rank.TEN, Shape.CLOVER));

        Dealer dealer = createDealer(new Card(Rank.THREE, Shape.DIAMOND),
                new Card(Rank.NINE, Shape.CLOVER),
                new Card(Rank.EIGHT, Shape.DIAMOND));

        GameResult expected = new GameResult(
                Map.of(
                        ScoreCompareResult.DEALER_WIN, 1,
                        ScoreCompareResult.DEALER_LOSS, 1,
                        ScoreCompareResult.PUSH, 1),
                Map.of(
                        pobi, ScoreCompareResult.PLAYER_WIN,
                        jason, ScoreCompareResult.PLAYER_LOSS,
                        brown, ScoreCompareResult.PUSH)
        );

        GameResult actual = dealer.judgeResult(List.of(pobi, jason, brown), dealer);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private Player createPlayer(String name, Card... cards) {
        Player player = new Player(name, new Money(10000));
        for (Card card : cards) {
            player.receiveOneCard(card);
        }
        return player;
    }

    private Dealer createDealer(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.receiveOneCard(card);
        }
        return dealer;
    }
}
