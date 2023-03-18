package domain.game;

import domain.card.Deck;
import domain.player.Player;
import domain.player.BlackJackSystem;
import domain.player.Players;
import domain.strategy.ShuffleStrategy;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class BlackJackGame {
    private final BlackJackSystem blackJackSystem;
    private final Deck deck;

    public BlackJackGame(String participantNames, ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.blackJackSystem = new BlackJackSystem(participantNames);
    }
    
    public void giveTwoCardToPlayers() {
        blackJackSystem.giveTwoCardToPlayers(deck);
    }
    
    public void settingBetAmountToParticipantsBy(ToDoubleFunction<String> supplyBetAmount) {
        blackJackSystem.betParticipants(supplyBetAmount);
    }
    
    public void giveCardToParticipantsBy(
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        blackJackSystem.giveCardToParticipantsBy(deck, supplyCommand, printParticipantCardStatus);
    }
    
    public void giveCardToDealerBy(Consumer<List<Player>> printGiveDealerCardMessage) {
        blackJackSystem.giveCardToDealerBy(deck, printGiveDealerCardMessage);
    }
    
    public Player dealer() {
        return blackJackSystem.dealer();
    }
    
    public List<Player> participants() {
        return blackJackSystem.participants();
    }
    
    public Players players() {
        return blackJackSystem.players();
    }
    
    public double findBetAmountByPlayer(Player player) {
        return blackJackSystem.findBetAmountByPlayer(player);
    }
    
    public Map<Player, Double> betAmounts() {
        return blackJackSystem.betAmounts();
    }
}
