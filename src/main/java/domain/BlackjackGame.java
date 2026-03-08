package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final GameParticipants participants;

    private BlackjackGame(Deck deck, GameParticipants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static BlackjackGame start(List<String> names) {
        Deck deck = Deck.createDeck();
        Dealer dealer = Dealer.from(new Hand(initCards(deck)));
        Players players = createPlayers(names, deck);
        return new BlackjackGame(deck, GameParticipants.of(dealer, players));
    }

    private static Players createPlayers(List<String> names, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(createPlayer(name, deck));
        }
        return Players.from(players);
    }

    private static Player createPlayer(String name, Deck deck) {
        return Player.of(Name.from(name), new Hand(initCards(deck)));
    }

    private static List<Card> initCards(Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            cards.add(deck.draw());
        }
        return cards;
    }

    public void addPlayerCard(Player player) {
        player.addHandCard(deck.draw());
    }

    public boolean shouldDealerDraw() {
        return getDealer().checkThreshold() && !participants.isAllPlayersBust();
    }

    public void playDealerTurn() {
        while (getDealer().checkThreshold()) {
            getDealer().addHandCard(deck.draw());
        }
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public Players getPlayers() {
        return participants.getPlayers();
    }
}