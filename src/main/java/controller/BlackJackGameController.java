package controller;

import domain.*;
import dto.ParticipantCardsDto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static view.OutputView.printCards;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        List<Player> players = initPlayer();
        GameManager gameManager = new GameManager(players);

        List<String> playersNames = getPlayerNames(players);
        OutputView.printGameInitialMessage(playersNames);

        gameManager.distributeInitialCards();
        printParticipantCards(gameManager);

        playGame(players, gameManager);

        Map<String, Boolean> gameResult = gameManager.getGameResult();

        endGame(gameManager, players, gameResult);
    }

    private void playGame(List<Player> players, GameManager gameManager) {
        for (Player player : players) {
            playGameWithPlayer(player, gameManager);
        }
        playGameWithDealer(gameManager);
    }

    public void playGameWithDealer(GameManager gameManager) {
        while (gameManager.getDealer().isContinueGame()) {
            if (!gameManager.getDealer().isContinueGame()) {
                break;
            }
            gameManager.drawCardTo(gameManager.getDealer());
            OutputView.printDealerMessage();
        }
    }

    public void playGameWithPlayer(Player player, GameManager gameManager) {
        while (player.isContinueGame()) {
            if (isStopGame(player)) {
                break;
            }
            gameManager.drawCardTo(player);
            printCards(player.getParticipantCardsDto());
        }
    }

    private boolean isStopGame(Player player) {
        String response = InputView.askContinue(player.getName());
        if (response.equals("n")) {
            printCards(player.getParticipantCardsDto());
            return true;
        }
        return false;
    }

    private void printParticipantCards(GameManager gameManager) {
        ParticipantCardsDto dealerDto = gameManager.getDealerDto();
        OutputView.printCards(dealerDto);
        List<ParticipantCardsDto> playerDtos = gameManager.getPlayerDtos();
        for (ParticipantCardsDto playerDto : playerDtos) {
            OutputView.printCards(playerDto);
        }
    }

    private void endGame(GameManager gameManager, List<Player> players, Map<String, Boolean> gameResult) {
        OutputView.printFinalCards(gameManager.getDealerDto());
        printFinalScores(players);
        OutputView.printGameResult(gameResult);
    }

    private void printFinalScores(List<Player> players) {
        for (Player player : players) {
            OutputView.printFinalCards(player.getParticipantCardsDto());
        }
    }

    private List<String> getPlayerNames(List<Player> players) {
        return players.stream().map(Participant::getName).toList();
    }

    private List<Player> initPlayer() {
        List<Player> players = new ArrayList<>();
        List<String> playerNames = getPlayerNames();

        for (String name : playerNames) {
            Name playerName = new Name(name);
            players.add(new Player(playerName));
        }
        return players;
    }

    private List<String> getPlayerNames() {
        return InputView.askPlayerNames();
    }
}
