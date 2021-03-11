package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSetUp {

    public static Player createBlackJackPlayer() {
        Player winner = new Player("winner", "0");
        for (Card card : getWinningDeck()) {
            winner.draw(card);
        }
        return winner;
    }

    public static Player createBlackJackPlayerWithMoney(String money) {
        Player winner = new Player("winner", money);
        for (Card card : getWinningDeck()) {
            winner.draw(card);
        }
        return winner;
    }

    public static Player createTiePlayer() {
        Player tiePlayer = new Player("tie player", "0");
        for (Card card : getCompareDeck()) {
            tiePlayer.draw(card);
        }
        return tiePlayer;
    }

    public static Player createLoser() {
        Player loser = new Player("loser", "0");
        for (Card card : getLosingDeck()) {
            loser.draw(card);
        }
        return loser;
    }

    public static Player createLoserWithMoney(String money) {
        Player loser = new Player("loser", money);
        for (Card card : getLosingDeck()) {
            loser.draw(card);
        }
        loser.willContinue("n");
        return loser;
    }

    public static Player createBustPlayer() {
        Player bust = new Player("bust player", "0");
        for (Card card : getBustDeck()) {
            bust.draw(card);
        }
        return bust;
    }

    public static Player createBustPlayerWithMoney(String money) {
        Player bust = new Player("bust player", money);
        for (Card card : getBustDeck()) {
            bust.draw(card);
        }
        return bust;
    }

    public static Dealer createDealer() {
        Dealer dealer = new Dealer();
        for (Card card : getCompareDeck()) {
            dealer.draw(card);
        }
        return dealer;
    }

    public static Dealer createBustDealer() {
        Dealer bustDealer = new Dealer();
        for (Card card : getBustDeck()) {
            bustDealer.draw(card);
        }
        return bustDealer;
    }

    public static Dealer createBlackJackDealer() {
        Dealer blackJackDealer = new Dealer();
        for (Card card : getWinningDeck()) {
            blackJackDealer.draw(card);
        }
        return blackJackDealer;
    }

    private static List<Card> getWinningDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.ACE)));
    }

    private static List<Card> getCompareDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.QUEEN)));
    }

    private static List<Card> getLosingDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.TWO),
                Card.valueOf(Shape.SPADE, CardValue.THREE)));
    }

    private static List<Card> getBustDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.SIX),
                Card.valueOf(Shape.SPADE, CardValue.JACK)));
    }
}
