package util;

import domain.Dealer;
import domain.Money;
import domain.Player;
import domain.WinningStatus;
import domain.betting.Betting;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.Result;
import domain.result.Results;
import java.util.Arrays;

public class TestUtil {

    public static Player createPlayer(String name) {
        return new Player(name);
    }

    public static Player createPlayer(String name, Card... cards) {
        Player player = new Player(name);
        for (Card card : cards) {
            player.draw(card);
        }
        return player;
    }

    public static Player createPlayer(String name, Rank... ranks) {
        Player player = new Player(name);
        for (Rank rank : ranks) {
            Card card = new Card(Suit.SPADES, rank);
            player.draw(card);
        }
        return player;
    }

    public static Dealer createDealer() {
        return new Dealer();
    }

    public static Dealer createDealer(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.draw(card);
        }
        return dealer;
    }

    public static Dealer createDealer(Rank... ranks) {
        Dealer dealer = new Dealer();
        for (Rank rank : ranks) {
            Card card = new Card(Suit.HEARTS, rank);
            dealer.draw(card);
        }
        return dealer;
    }

    public static Card createCard(Suit suit, Rank rank) {
        return new Card(suit, rank);
    }

    public static Card createSpadesCard(Rank rank) {
        return new Card(Suit.SPADES, rank);
    }

    public static Betting createBetting(String name, long amount) {
        return new Betting(createPlayer(name), new Money(amount));
    }

    public static Result createResult(String name, long amount, WinningStatus winningStatus) {
        return new Result(createBetting(name, amount), winningStatus);
    }

    public static Result createAmount10000Result(String name, WinningStatus winningStatus) {
        return new Result(createBetting(name, 10000), winningStatus);
    }

    public static Results createResults(Result... results) {
        return new Results(Arrays.stream(results).toList());
    }
}
