package blackjack.util;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.participant.BlackJackParticipant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameInitializer {

    public static final int STARTING_CARD_COUNT = 2;

    private GameInitializer() {
    }

    public static Deck initializeDeck() {
        List<Card> allCards = Card.getAllCards();
        Collections.shuffle(allCards);
        return new Deck(allCards);
    }

    public static Players initializePlayers(String playersInput, Deck deck) {
        Players players = Players.valueOf(playersInput);
        for (int i=0; i < STARTING_CARD_COUNT; i++) {
            players.unwrap().forEach(player -> player.drawCard(deck));
        }
        return players;
    }

    public static Dealer initializeDealer(Deck deck) {
        Dealer dealer = new Dealer();
        for (int i=0; i < STARTING_CARD_COUNT; i++) {
            dealer.drawCard(deck);
        }
        return dealer;
    }

//    public void prepare() {
//        for (int i = 0; i < STARTING_CARD_COUNT; i++) {
//            unpreparedPlayers.unwrap().forEach(player -> player.drawCard(deck));
//            dealer.drawCard(deck);
//        }
//    }

//    public Player nextPlayer() {
//        Player player = unpreparedPlayers.pop();
//        preparedPlayers.push(player);
//        return player;
//    }
//
//    public boolean isPrepared() {
//        return unpreparedPlayers.isEmpty();
//    }
//
//    public Deck getDeck() {
//        return this.deck;
//    }
//
//    public List<Player> getPlayers() {
//        List<Player> result = unpreparedPlayers.unwrap();
//        result.addAll(preparedPlayers.unwrap());
//        return result;
//    }
//
//    public Dealer getDealer() {
//        return this.dealer;
//    }
//
//    public List<BlackJackParticipant> getParticipants() {
//        List<BlackJackParticipant> participants = new ArrayList(Arrays.asList(dealer));
//        participants.addAll(getPlayers());
//        return participants;
//    }

//    public GameResult getGameResult() {
//        return preparedPlayers.match(dealer);
//    }
}
