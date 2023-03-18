package domain.player;

import domain.card.Card;
import domain.card.Deck;
import domain.game.AddCardCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {
    private final Dealer dealer;
    private final List<Player> participants;
    
    public Players(String participantNames) {
        this.dealer = new Dealer();
        this.participants = initParticipants(participantNames);
    }
    
    private List<Player> initParticipants(String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(Participant::new)
                .collect(Collectors.toUnmodifiableList());
    }
    
    public void giveTwoCardToPlayers(Deck deck) {
        giveTwoCardToPerPlayer(dealer, deck);
        for (Player player : participants) {
            giveTwoCardToPerPlayer(player, deck);
        }
    }
    
    private void giveTwoCardToPerPlayer(Player player, Deck deck) {
        Card firstCard = deck.draw();
        Card secondCard = deck.draw();
        player.initCards(firstCard, secondCard);
    }
    
    public void giveCardToParticipantsBy(
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        for (Player participant : participants()) {
            drawOrFinishParticipantBy(participant, deck, supplyCommand, printParticipantCardStatus);
        }
    }
    
    private void drawOrFinishParticipantBy(
            Player player,
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        if (player.isFinished()) {
            printParticipantCardStatus.accept(List.of(player));
            return;
        }
        
        decideDrawOrFinish(player, deck, supplyCommand, printParticipantCardStatus);
    }
    
    private void decideDrawOrFinish(
            Player player,
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        AddCardCommand command = supplyCommand.apply(player);
        if (command.isAddCardCommand()) {
            player.draw(deck.draw());
        }
        printParticipantCardStatus.accept(List.of(player));
        
        if (command.isNotAddCardCommand() || player.isFinished()) {
            drawStopIfNotAddCardCommand(player, command);
            return;
        }
        decideDrawOrFinish(player, deck, supplyCommand, printParticipantCardStatus);
    }
    
    private void drawStopIfNotAddCardCommand(Player player, AddCardCommand command) {
        if (command.isNotAddCardCommand()) {
            player.drawStop();
        }
    }
    
    public void giveCardToDealerBy(Deck deck, Consumer<List<Player>> printGiveDealerCardMessage) {
        drawOrFinishDealerBy(deck, printGiveDealerCardMessage);
    }
    
    private void drawOrFinishDealerBy(Deck deck, Consumer<List<Player>> printParticipantCardStatus) {
        if (dealer.isFinished()) {
            return;
        }
        
        dealer.draw(deck.draw());
        printParticipantCardStatus.accept(Collections.emptyList());
        
        if (isDealerNotFinished(dealer)) {
            dealer.drawStop();
        }
    }
    
    private boolean isDealerNotFinished(Player dealer) {
        return !dealer.isFinished();
    }
    
    public List<Player> players() {
        List<Player> players = new ArrayList<>(List.of(dealer));
        players.addAll(participants);
        return players;
    }
    
    public Player dealer() {
        return dealer;
    }
    
    public List<Player> participants() {
        return Collections.unmodifiableList(this.participants);
    }
}
