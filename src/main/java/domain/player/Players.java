package domain.player;

import domain.card.Card;
import domain.card.Deck;
import domain.game.AddCardCommand;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;
    private final Map<Player, Double> betAmounts;
    
    public Players(String participantNames) {
        this.players = initPlayers(participantNames);
        this.betAmounts = new HashMap<>();
    }
    
    private List<Player> initPlayers(String playerNames) {
        List<Player> players = new ArrayList<>(List.of(new Dealer()));
        players.addAll(initParticipants(playerNames));
        return players;
    }
    
    private List<Participant> initParticipants(String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(Participant::new)
                .collect(Collectors.toUnmodifiableList());
    }
    
    public void giveTwoCardToPlayers(Deck deck) {
        for (Player player : players) {
            giveTwoCardToPerPlayer(player, deck);
        }
    }
    
    private void giveTwoCardToPerPlayer(Player player, Deck deck) {
        Card firstCard = deck.draw();
        Card secondCard = deck.draw();
        player.initCards(firstCard, secondCard);
    }
    
    public void settingBetAmountToParticipantsBy(ToDoubleFunction<String> supplyBetAmount) {
        for (Player participant : getParticipants()) {
            double betAmount = participant.supplyBetAmount(supplyBetAmount);
            betAmounts.put(participant, betAmount);
        }
    }
    
    public void giveCardToParticipantsBy(
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        for (Player participant : getParticipants()) {
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
        drawOrFinishDealerBy(getDealer(), deck, printGiveDealerCardMessage);
    }
    
    private void drawOrFinishDealerBy(Player dealer, Deck deck, Consumer<List<Player>> printParticipantCardStatus) {
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
    
    public double findBetAmountByPlayer(Player player) {
        return betAmounts.get(player);
    }
    
    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 존재하지 않습니다."));
    }
    
    public List<Player> getParticipants() {
        return players.stream()
                .filter(Predicate.not(Player::isDealer))
                .collect(Collectors.toUnmodifiableList());
    }
    
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
