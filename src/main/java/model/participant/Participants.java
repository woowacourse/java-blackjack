package model.participant;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import model.card.Card;
import model.dto.GameCompletionResult;

public class Participants {
    private final Dealer dealer;
    private final Queue<Player> players;

    public Participants(Names names) {
        this.dealer = new Dealer();
        this.players = generatePlayers(names);
    }

    private Queue<Player> generatePlayers(Names names) {
        return names.getPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void hitAndMoveToNextPlayer(Card card) {
        hitPlayer(card);
        moveToNextPlayer();
    }

    public void turnOverPlayer() {
        Player currentTurnPlayer = Objects.requireNonNull(players.poll());
        currentTurnPlayer.turnOver();
        players.add(currentTurnPlayer);
    }

    private void moveToNextPlayer() {
        players.add(players.poll());
    }

    public void hitDealer(Card card) {
        dealer.hitCard(card);
    }

    public void hitPlayer(Card card) {
        Player player = Objects.requireNonNull(players.peek());
        player.hitCard(card);
    }

    Queue<Player> getPlayers() {
        return players;
    }

    public GameCompletionResult getDealerCompletionResult() {
        return dealer.generateGameCompletionResult();
    }

    public List<GameCompletionResult> getPlayerCompletionResults() {
        return players.stream()
                .map(Player::generateGameCompletionResult)
                .toList();
    }

    public int getPlayerSize() {
        return players.size();
    }

    public GameCompletionResult getNextAvailablePlayerName() {
        Player currentPlayer = players.peek();
        if (currentPlayer.canHit()) {
            return currentPlayer.generateGameCompletionResult();
        }
        moveToNextPlayer();
        return getNextAvailablePlayerName();
    }

    public Participant findParticipantByName(Name name) {
        if (dealer.isSameName(name)) {
            return dealer;
        }
        Queue<Player> newPlayers = new LinkedList<>(players);

        return newPlayers.stream()
                .filter(player -> player.isSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 참가자를 찾을 수 없습니다."));
    }

    public boolean hasAvailablePlayer() {
        return players.stream()
                .anyMatch(Player::canHit);
    }

    public boolean canHitDealer() {
        return dealer.canHit();
    }

}
