package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Player;
import domain.player.Players;
import domain.strategy.ShuffleStrategy;
import view.AddCardCommand;

import java.util.List;
import java.util.function.*;

public class BlackJackGame {
    private final Deck deck;
    private final Players players;

    public BlackJackGame(String participantNames, ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.players = new Players(participantNames);
    }
    
    public void giveTwoCardToPlayers() {
        players.giveTwoCardToPlayers(deck);
    }
    
    public void settingBetAmountToParticipantsBy(
            Consumer<Player> printBetAmountInputGuide,
            DoubleSupplier supplyBetAmount,
            ObjDoubleConsumer<Player> saveParticipantBetAmount
    ) {
        players.settingBetAmountToParticipantsBy(printBetAmountInputGuide, supplyBetAmount, saveParticipantBetAmount);
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
}
