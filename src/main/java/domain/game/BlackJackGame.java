package domain.game;

import domain.card.Deck;
import domain.player.Player;
import domain.player.Players;
import domain.strategy.ShuffleStrategy;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class BlackJackGame {
    private final Players players;
    private final Deck deck;

    public BlackJackGame(String participantNames, ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.players = new Players(participantNames);
    }
    
    public void giveTwoCardToPlayers() {
        players.giveTwoCardToPlayers(deck);
    }
    
    public void settingBetAmountToParticipantsBy(ToDoubleFunction<String> supplyBetAmount) {
        players.settingBetAmountToParticipantsBy(supplyBetAmount);
    }
    
    public void giveCardToParticipantsBy(
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        players.giveCardToParticipantsBy(deck, supplyCommand, printParticipantCardStatus);
    }
    
    public void giveCardToDealerBy(Consumer<List<Player>> printGiveDealerCardMessage) {
        players.giveCardToDealerBy(deck, printGiveDealerCardMessage);
    }
    
    public Player getDealer() {
        return players.getDealer();
    }
    
    public List<Player> getParticipants() {
        return players.getParticipants();
    }
    
    public List<Player> getPlayers() {
        return players.getPlayers();
    }
    
    public double findBetAmountByPlayer(Player player) {
        return players.findBetAmountByPlayer(player);
    }
}
