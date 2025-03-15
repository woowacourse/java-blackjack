package util;

import card.Card;
import participant.Participant;

import java.util.List;

public class TestCardDistributor {
    public static void divideCard(List<Card> cards, Participant participant) {
        for (Card card : cards) {
            participant.addCard(card);
        }
    }
}
