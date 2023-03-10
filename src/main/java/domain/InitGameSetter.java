package domain;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.*;

public class InitGameSetter {

    public static Players generatePlayers(Deck deck, List<String> playerNames) {
        List<Player> generatedPlayers = new ArrayList<>();
        for (String playerName : playerNames) {
            List<Card> initCards = new ArrayList<>();
            drawTwoCard(deck, initCards);
            generatedPlayers.add(new Player(new Name(playerName), new Hand(initCards)));
        }
        return new Players(generatedPlayers);
    }

    public static Dealer generateDealer(Deck deck) {
        List<Card> initCards = new ArrayList<>();
        drawTwoCard(deck, initCards);
        return new Dealer(new Hand(initCards));
    }

    private static void drawTwoCard(Deck deck, List<Card> initCards) {
        initCards.add(deck.drawCard());
        initCards.add(deck.drawCard());
    }
}
