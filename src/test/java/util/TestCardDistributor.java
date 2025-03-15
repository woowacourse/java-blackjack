package util;

import card.Card;
import participant.Dealer;
import participant.Player;

import java.util.List;

public class TestCardDistributor {
    public static void divideCardToDealer(List<Card> cards, Dealer dealer) {
        for (Card card : cards) {
            dealer.addCard(card);
        }
    }

    public static void divideCardToPlayer(List<Card> cards, Player player) {
        for (Card card : cards) {
            player.addCard(card);
        }
    }
}
