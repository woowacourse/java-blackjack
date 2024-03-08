package model.participant;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import model.card.Card;
import model.dto.FaceUpResult;

public class Entrant {
    private final Dealer dealer;
    private final Queue<Player> players;

    public Entrant(Names names) {
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

    public FaceUpResult getDealerFaceUpResult() {
        return dealer.generateFaceUpResult();
    }

    public List<FaceUpResult> getPlayerFaceUpResults() {
        return players.stream()
                .map(Player::generateFaceUpResult)
                .toList();
    }

    public int getPlayerSize() {
        return players.size();
    }

    public FaceUpResult getNextAvailablePlayerName() {
        Player currentPlayer = players.peek();
        if (currentPlayer.canHit()) {
            return currentPlayer.generateFaceUpResult();
        }
        moveToNextPlayer();
        return getNextAvailablePlayerName();
    }

    public boolean hasAvailablePlayer() {
        return players.stream()
                .anyMatch(Player::canHit);
    }

    public boolean canHitDealer() {
        return dealer.canHit();
    }
}
