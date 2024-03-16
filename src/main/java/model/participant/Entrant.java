package model.participant;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import model.card.Card;
import model.participant.dto.DealerFaceUpResult;
import model.participant.dto.PlayerFaceUpResult;
import model.participant.dto.PlayerMatchResult;
import util.ResultMapper;

public class Entrant {
    private final Dealer dealer;
    private final Queue<Player> players;

    public Entrant(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = new LinkedList<>(players);
    }

    public void hitDealer(Card card) {
        dealer.hitCard(card);
    }

    public boolean canHitDealer() {
        return dealer.canHit();
    }

    public void hitPlayer(Card card) {
        getCurrentPlayer().hitCard(card);
    }

    public void turnOverPlayer() {
        getCurrentPlayer().turnOver();
        moveToNextPlayer();
    }

    public Player getNextAvailablePlayer() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.canHit()) {
            return currentPlayer;
        }
        moveToNextPlayer();
        return getNextAvailablePlayer();
    }

    private void moveToNextPlayer() {
        players.add(players.poll());
    }

    public boolean hasAvailablePlayer() {
        return players.stream()
                .anyMatch(Player::canHit);
    }

    private Player getCurrentPlayer() {
        return Objects.requireNonNull(players.peek());
    }

    public DealerFaceUpResult getDealerFaceUpResult() {
        return ResultMapper.toDealerFaceUpResult(dealer);
    }

    public List<PlayerFaceUpResult> getPlayerFaceUpResults() {
        return players.stream()
                .map(ResultMapper::toPlayerFaceUpResult)
                .toList();
    }

    public List<PlayerMatchResult> determineFinalPlayerMatchResults() {
        return players.stream()
                .map(player -> ResultMapper.toPlayerMatchResult(player, dealer))
                .toList();
    }
}
