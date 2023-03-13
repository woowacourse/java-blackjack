package domain.player;

import domain.card.Card;
import domain.card.Deck;
import domain.game.BetAmounts;
import domain.game.AddCardCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;
    private final BetAmounts betAmounts;
    
    public Players(String participantNames) {
        this.players = initPlayers(participantNames);
        this.betAmounts = new BetAmounts();
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
            betAmounts.savePlayerBetAmount(participant, betAmount);
        }
    }
    
    public void giveCardToParticipantsBy(
            Deck deck,
            Function<Player, AddCardCommand> supplyCommand,
            Consumer<List<Player>> printParticipantCardStatus
    ) {
        for (Player participant : getParticipants()) {
            participant.drawOrFinishParticipantBy(deck, supplyCommand, printParticipantCardStatus);
        }
    }
    
    public void giveCardToDealerBy(Deck deck, Consumer<List<Player>> printGiveDealerCardMessage) {
        getDealer().drawOrFinishDealerBy(deck, printGiveDealerCardMessage);
    }
    
    public double findBetAmountByPlayer(Player player) {
        return betAmounts.findBetAmountByPlayer(player);
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
