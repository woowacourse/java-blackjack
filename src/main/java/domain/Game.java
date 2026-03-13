package domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Deck totalDeck;
    private final Dealer dealer;
    private final Players players;

    private Game(Deck totalDeck, Dealer dealer, Players players) {
        this.totalDeck = totalDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static Game registerParticipantsAndPrepareTotalDeck(List<String> playerNames,
                                                               CardShuffleStrategy strategy) {
        Deck totalDeck = Deck.createTotalDeckAndShuffle(strategy);
        Dealer dealer = new Dealer();
        Players players = Players.of(playerNames);
        return new Game(totalDeck, dealer, players);
    }

    public void readyParticipantDecks() {
        drawInitialCards(dealer, totalDeck);
        for (Player player : players) {
            drawInitialCards(player, totalDeck);
        }
    }

    private void drawInitialCards(Participant participant, Deck totalDeck) {
        for (int i = 0; i < 2; i++) {
            drawCard(participant, totalDeck);
        }
    }

    private void drawCard(Participant participant, Deck totalDeck) {
        Card card = totalDeck.drawCard();
        participant.addCard(card);
    }

    public boolean drawCardUnderCondition(Participant participant) {
        boolean isDrawable = participant.isDrawable() && totalDeck.isDrawable();
        if (isDrawable) {
            drawCard(participant, totalDeck);
        }
        return isDrawable;
    }

    public Map<Player, Result> getPlayerWinTieLossResults() {
        int dealerScore = dealer.getCardsSum();
        boolean isDealerBust = dealer.isBust();
        Map<Player, Result> playerWinTieLossResults = new LinkedHashMap<>();
        for (Player player : players) {
            Result playerResult = getPlayerWinTieLossResult(isDealerBust, dealerScore, player);
            playerWinTieLossResults.put(player, playerResult);
        }
        return playerWinTieLossResults;
    }

    private Result getPlayerWinTieLossResult(boolean isDealerBust, int dealerScore, Player player) {
        return Result.determinePlayerResult(
                isDealerBust,
                player.isBust(),
                dealerScore,
                player.getCardsSum()
        );
    }

    public Map<Result, Integer> getDealerWinTieLossResult() {
        Map<Player, Result> playerWinTieLossResults = getPlayerWinTieLossResults();
        Map<Result, Integer> dealerWinTieLossResults = new EnumMap<>(Result.class);
        for (Player player : players) {
            Result playerResult = playerWinTieLossResults.get(player);
            Result dealerResult = playerResult.reverse();
            int currentValue = dealerWinTieLossResults.getOrDefault(dealerResult, 0);
            dealerWinTieLossResults.put(dealerResult, currentValue + 1);
        }
        return dealerWinTieLossResults;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        for (Player player : players) {
            participants.add(player);
        }
        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
