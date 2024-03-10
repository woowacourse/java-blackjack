package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Participants participants = createParticipants();
            Deck deck = Deck.createShuffledDeck();

            initialDeal(participants, deck);
            playersTurn(participants.getPlayers(), deck);
            dealerTurn(participants.getDealer(), deck);
            printResult(participants);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private Participants createParticipants() {
        List<String> playerNames = inputView.readPlayerNames();

        return new Participants(playerNames);
    }

    private void initialDeal(Participants participants, Deck deck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(deck.draw());
            participant.hit(deck.draw());
        }

        outputView.printInitialDeal(participants.getDealer(), participants.getPlayers());
    }

    private void playersTurn(List<Player> players, Deck deck) {
        players.forEach(player -> playerTurn(player, deck));
    }

    private void playerTurn(Player player, Deck deck) {
        if (!player.isPlayable()) {
            return;
        }

        boolean wannaHit = inputView.readCommand(player.getName());
        if (wannaHit) {
            player.hit(deck.draw());
            outputView.printCards(player.getName(), player.getCards());
            playerTurn(player, deck);
            return;
        }

        outputView.printCards(player.getName(), player.getCards());
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isPlayable()) {
            dealer.hit(deck.draw());
            outputView.printDealerHitMessage();
        }
    }

    private void printResult(Participants participants) {
        Map<Player, GameResult> playerGameResults = createPlayerGameResults(participants);
        Map<GameResult, Integer> dealerGameResult = createDealerGameResult(playerGameResults);

        outputView.printAllCardsWithScore(participants.getParticipants());
        outputView.printGameResult(playerGameResults, dealerGameResult);
    }

    private Map<GameResult, Integer> createDealerGameResult(Map<Player, GameResult> playerGameResults) {
        Map<GameResult, Integer> dealerGameResults = new EnumMap<>(GameResult.class);

        for (GameResult gameResult : GameResult.values()) {
            dealerGameResults.put(gameResult, 0);
        }

        for (Entry<Player, GameResult> entry : playerGameResults.entrySet()) {
            GameResult gameResult = entry.getValue().getOpposite();
            int current = dealerGameResults.get(gameResult);
            dealerGameResults.put(gameResult, current + 1);
        }

        return dealerGameResults;
    }

    private Map<Player, GameResult> createPlayerGameResults(Participants participants) {
        Map<Player, GameResult> playerGameResults = new LinkedHashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            GameResult gameResult = dealer.judge(player);
            playerGameResults.put(player, gameResult);
        }

        return playerGameResults;
    }
}
