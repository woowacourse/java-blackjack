package test_util;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.Hand;
import domain.pariticipant.BettingAmount;
import domain.pariticipant.Dealer;
import domain.pariticipant.Name;
import domain.pariticipant.Player;

import java.util.List;

public class TestUtil {

    public static Player createPlayer(String name, List<Card> handCards) {
        return createPlayer(name, handCards, 10000);
    }

    public static Player createPlayer(String name, List<Card> handCards, int price) {
        return new Player(
                new Name(name),
                new Hand(handCards),
                new BettingAmount(price));
    }

    public static Card createCard(CardSuit cardSuit, CardRank cardRank) {
        return new Card(cardSuit, cardRank);
    }

    public static Dealer creteDealer(String name, List<Card> handCards) {
        return new Dealer(
                new Name(name),
                new Hand(handCards));
    }
}
