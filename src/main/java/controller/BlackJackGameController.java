package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        List<String> playerNames = getPlayerNames();
        List<Player> players = initPlayer(playerNames);
        Dealer dealer = initDealer();
        Deck deck = new Deck();

        BlackJackGame blackJackGame = new BlackJackGame(deck, players, dealer);
        blackJackGame.distributeInitialCards();

        OutputView.printGameInitialMessage(playerNames);

        OutputView.printInitialDealerCards(dealer.getParticipantCardsDto());
        for (Player player : players) {
            OutputView.printInitialPlayerCards(player.getParticipantCardsDto());
        }

        playGame(blackJackGame, players, deck, dealer);

        Map<String, Boolean> gameResult = getGameResult(dealer, players);

        endGame(dealer, players, gameResult);
    }

    private List<String> getPlayerNames() {
        return InputView.askPlayerNames();
    }

    private List<Player> initPlayer(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::from)
                .toList();
        return players;
    }

    private Dealer initDealer() {
        Dealer dealer = Dealer.create();
        return dealer;
    }

    private static Map<String, Boolean> getGameResult(Dealer dealer, List<Player> players) {
        Map<Participant, Integer> participantScores = getParticipantScores(dealer, players);
        Map<String, Boolean> gameResult = Result.calculateResult(participantScores);
        return gameResult;
    }

    private static void endGame(Dealer dealer, List<Player> players, Map<String, Boolean> gameResult) {
        OutputView.printFinalCards(dealer.getParticipantCardsDto());
        printFinalScores(players);
        OutputView.printGameResult(gameResult);
    }

    private void playGame(BlackJackGame blackJackGame, List<Player> players, Deck deck, Dealer dealer) {
        for (Player player : players) {
            while (player.canReceiveCard()) {
                if (isContinueGame(player)) {
                    break;
                }
                blackJackGame.playGameWithPlayer(player);
                OutputView.printCards(player.getParticipantCardsDto());
            }
        }
        if (dealer.canReceiveCard()) {
            blackJackGame.playGameWithDealer();
            OutputView.printDealerMessage();
        }
    }

    private static void printFinalScores(List<Player> players) {
        for (Player player : players) {
            OutputView.printFinalCards(player.getParticipantCardsDto());
        }
    }

    private static Map<Participant, Integer> getParticipantScores(Dealer dealer, List<Player> players) {
        Map<Participant, Integer> participantScores = new HashMap<>();
        participantScores.put(dealer, dealer.getScore());

        for (Player player : players) {
            participantScores.put(player, player.getScore());
        }
        return participantScores;
    }

    private boolean isContinueGame(Player player) {
        if (!isContinue(InputView.askContinue(player.getName()))) {
            OutputView.printCards(player.getParticipantCardsDto());
            return true;
        }
        return false;
    }

    private boolean isContinue(String response) {
        if (response.equals("y")) {
            return true;
        }
        return false;
    }
}
