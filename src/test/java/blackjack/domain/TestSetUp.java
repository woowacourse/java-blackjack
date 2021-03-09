package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.exception.InvalidNameInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestSetUp {

    public static Player createWinner() {
        Player winner = new Player("winner");
        for (Card card : getWinningDeck()) {
            winner.draw(card);
        }
        return winner;
    }

    public static Player createTiePlayer() {
        Player tiePlayer = new Player("tie player");
        for (Card card : getCompareDeck()) {
            tiePlayer.draw(card);
        }
        return tiePlayer;
    }

    public static Player createLoser() {
        Player loser = new Player("loser");
        for (Card card : getLosingDeck()) {
            loser.draw(card);
        }
        return loser;
    }

    public static Player createBustPlayer() {
        Player bust = new Player("bust player");
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

    private static List<Card> getWinningDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.ACE)));
    }

    private static List<Card> getCompareDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.QUEEN)));
    }

    private static List<Card> getLosingDeck() {
        return new ArrayList<>(Collections.emptyList());
    }

    private static List<Card> getBustDeck() {
        return new ArrayList<>(Arrays.asList(Card.valueOf(Shape.SPADE, CardValue.KING),
                Card.valueOf(Shape.SPADE, CardValue.QUEEN),
                Card.valueOf(Shape.SPADE, CardValue.JACK)));
    }
}
