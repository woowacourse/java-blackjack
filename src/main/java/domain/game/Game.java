package domain.game;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final Dealer dealer;
    private final Players players;

    public Game(List<String> playerNames, Deck deck) {
        dealer = new Dealer(deck, deck.getInitialDeal());
        this.players = new Players(playerNames);
        registerPlayer(playerNames);
    }

    private void registerPlayer(List<String> playerNames) {
        playerNames.forEach(name -> {
            CardHand initialDeal = dealer.pickInitialDeal();
            players.registerPlayer(name, initialDeal);
        });
    }

    public void hit(String name) {
        Card card = dealer.pickCard();
        findParticipantByName(name).hit(card);
    }

    public boolean canHit(String name) {
        return findParticipantByName(name).canHit();
    }

    public GameParticipant findParticipantByName(String name) {
        if (dealer.getName().equals(name)) {
            return dealer;
        }
        return players.findPlayerByName(name);
    }

    public List<GameParticipant> getParticipants() {
        return Stream.concat(Stream.of(dealer), players.getPlayers().stream())
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Card> getCardsOf(String name) {
        return findParticipantByName(name).getCards();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
