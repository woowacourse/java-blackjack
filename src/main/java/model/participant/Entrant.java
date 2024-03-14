package model.participant;

import static java.util.Collections.frequency;
import static model.casino.MatchResult.DRAW;
import static model.casino.MatchResult.LOSE;
import static model.casino.MatchResult.WIN;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import model.card.Card;
import model.casino.MatchResult;
import service.dto.FaceUpResult;
import service.dto.PlayerMatchResult;

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

    public void hitDealer(Card card) {
        dealer.hitCard(card);
    }

    public boolean canHitDealer() {
        return dealer.canHit();
    }

    public void hitAndMoveToNextPlayer(Card card) {
        hitPlayer(card);
        moveToNextPlayer();
    }

    public void hitPlayer(Card card) {
        getCurrentPlayer().hitCard(card);
    }

    public void turnOverPlayer() {
        getCurrentPlayer().turnOver();
        moveToNextPlayer();
    }

    public FaceUpResult getNextAvailablePlayerName() {
        Player currentPlayer = getCurrentPlayer();
        if (currentPlayer.canHit()) {
            return currentPlayer.generateFaceUpResult();
        }
        moveToNextPlayer();
        return getNextAvailablePlayerName();
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

    public FaceUpResult getDealerFaceUpResult() {
        return dealer.generateFaceUpResult();
    }

    public List<FaceUpResult> getPlayerFaceUpResults() {
        return players.stream()
                .map(Player::generateFaceUpResult)
                .toList();
    }

    public EnumMap<MatchResult, Integer> calculateDealerMatchResult() {
        EnumMap<MatchResult, Integer> dealerScoreBoard = new EnumMap<>(MatchResult.class);
        List<MatchResult> playerScores = calculateMatchResults();
        dealerScoreBoard.put(WIN, frequency(playerScores, LOSE));
        dealerScoreBoard.put(DRAW, frequency(playerScores, DRAW));
        dealerScoreBoard.put(LOSE, frequency(playerScores, WIN));
        return dealerScoreBoard;
    }

    private List<MatchResult> calculateMatchResults() {
        int dealerHand = dealer.cardDeck.calculateHand();
        return players.stream()
                .map(player -> player.calculateMatchResult(dealerHand))
                .toList();
    }

    public List<PlayerMatchResult> calculatePlayerMatchResults() {
        int dealerHand = dealer.cardDeck.calculateHand();
        return players.stream()
                .map(player -> new PlayerMatchResult(player.getName(), player.calculateMatchResult(dealerHand)))
                .toList();
    }

    public int getPlayerSize() {
        return players.size();
    }

    Queue<Player> getPlayers() {
        return players;
    }
}
