package domain;

import controller.GameDelegate;
import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.HashMap;
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

    public static Game ready(GameDelegate delegate, CardCreationStrategy strategy) {
        Deck totalDeck = Deck.createDeck(strategy);

        Deck dealerDeck = Deck.createParticipantDeck(totalDeck);
        Dealer dealer = new Dealer(dealerDeck);

        List<String> playerNames = delegate.askPlayerNames();
        Players players = Players.of(playerNames, totalDeck);

        delegate.showInitialParticipantCards(
                ParticipantDto.initialFrom(dealer),
                ParticipantDto.listOf(players.getPlayers())
        );

        return new Game(totalDeck, dealer, players);
    }

    public void play(GameDelegate observer) {
        List<Player> individualPlayers = players.getPlayers();
        for (Player player : individualPlayers) {
            while (!player.isBust() && observer.askDrawCard(player.getName())) {
                player.addCard(totalDeck);
                observer.showPlayerCards(ParticipantDto.from(player));
            }
        }
        while (dealer.addCard(totalDeck).isPresent()) {
            observer.showDealerOneMoreCardMessage();
        }
    }

    public void end(GameDelegate delegate) {
        GameResultDto result = this.calculateResult();
        delegate.showGameResult(result);
    }

    private GameResultDto calculateResult() {
        int dealerScore = dealer.calculateDeckSum();
        boolean isDealerBust = dealer.isBust();

        Map<Player, Result> playerWinLossResults = consistPlayerWinLossResults(dealerScore, isDealerBust);
        Map<Result, Integer> dealerWinLossResults = consistDealerResult(playerWinLossResults);

        return GameResultDto.from(
                dealer,
                players,
                dealerWinLossResults,
                playerWinLossResults
        );
    }

    private Map<Player, Result> consistPlayerWinLossResults(int dealerScore, boolean isDealerBust) {
        Map<Player, Result> playerWinLossResults = new LinkedHashMap<>();
        List<Player> playingPlayers = players.getPlayers();

        for (Player specificPlayer : playingPlayers) {
            Result specificPlayerResult = determinePlayerResult(dealerScore, isDealerBust, specificPlayer);
            playerWinLossResults.put(specificPlayer, specificPlayerResult);
        }

        return playerWinLossResults;
    }

    private Result determinePlayerResult(int dealerScore, boolean isDealerBust, Player specificPlayer) {
        return Result.determinePlayerResult(
                dealerScore,
                specificPlayer.calculateDeckSum(),
                isDealerBust,
                specificPlayer.isBust()
        );
    }

    private Map<Result, Integer> consistDealerResult(Map<Player, Result> playerWinLossResults) {
        Map<Result, Integer> dealerWinLossResults = new HashMap<>();
        List<Player> playingPlayers = playerWinLossResults.keySet().stream().toList();
        for (Player player : playingPlayers) {
            Result dealerResult = playerWinLossResults.get(player).reverse();
            int currentValue = dealerWinLossResults.getOrDefault(dealerResult, 0);
            dealerWinLossResults.put(dealerResult, currentValue + 1);
        }
        return dealerWinLossResults;
    }
}
