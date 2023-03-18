package domain.player;

import domain.card.Deck;
import domain.game.AddCardCommand;

import java.util.*;
import java.util.function.*;

public class BlackJackSystem {
    private final Players players;
    private final Map<Player, Double> betAmounts;
    
    public BlackJackSystem(String participantNames) {
        this.players = new Players(participantNames);
        this.betAmounts = new HashMap<>();
    }
    
    public void giveTwoCardToPlayers(Deck deck) {
        players.giveTwoCardToPlayers(deck);
    }
    
    public void betParticipants(ToDoubleFunction<String> supplyBetAmount) {
        for (Player participant : participants()) {
            double betAmount = participant.supplyBetAmount(supplyBetAmount);
            betAmounts.put(participant, betAmount);
        }
    }
    
    public void giveCardToParticipantsBy(
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        players.giveCardToParticipantsBy(deck, supplyCommand, printParticipantCardStatus);
    }
    
    public void giveCardToDealerBy(Deck deck, Consumer<List<Player>> printGiveDealerCardMessage) {
        players.giveCardToDealerBy(deck, printGiveDealerCardMessage);
    }
    
    public double findBetAmountByPlayer(Player player) {
        return betAmounts.get(player);
    }
    
    public Players players() {
        return players;
    }
    
    public Player dealer() {
        return players.dealer();
    }
    
    public List<Player> participants() {
        return players.participants();
    }
    
    public Map<Player, Double> betAmounts() {
        return Collections.unmodifiableMap(betAmounts);
    }
}
