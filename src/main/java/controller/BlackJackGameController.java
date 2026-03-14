package controller;

import domain.*;
import dto.ParticipantCardsDto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static view.OutputView.printCards;
import static view.OutputView.printFinalCards;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        Players players = initPlayer();
        GameManager gameManager = new GameManager(players);
        List<String> playersNames = getPlayerNames(players);
        // TODO: 베팅금액 입력 로직 - return 값 아직 활용 안함
        players.forEach(player -> InputView.askBettingAmount(player.getName().getName()));
        OutputView.printGameInitialMessage(playersNames);
        gameManager.distributeInitialCards();
        printParticipantCards(gameManager.getDealer(), players);
        playGame(players, gameManager);
        Map<String, GameResult> gameResult = gameManager.getGameResult();
        endGame(gameManager, players, gameResult);
    }

    private void playGame(Players players, GameManager gameManager) {
        players.forEach(player -> playGameWithPlayer(player, gameManager));
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
            printCards(toParticipantCardsDto(player));
        }
    }

    private boolean isStopGame(Player player) {
        String response = InputView.askContinue(player.getName().getName());
        if (response.equals("n")) {
            printCards(toParticipantCardsDto(player));
            return true;
        }
        return false;
    }

    private void printParticipantCards(Dealer dealer, Players players) {
        OutputView.printCards(toParticipantCardsDto(dealer));
        players.forEach(player -> OutputView.printCards(toParticipantCardsDto(player)));
    }

    private void endGame(GameManager gameManager, Players players, Map<String, GameResult> gameResult) {
        OutputView.printFinalCards(toParticipantCardsDto(gameManager.getDealer()));
        printFinalScores(players);
        OutputView.printGameResult(gameResult);
    }

    private void printFinalScores(Players players) {
        players.forEach(player -> printFinalCards(toParticipantCardsDto(player)));
    }

    private List<String> getPlayerNames(Players players) {
        List<String> playerNames = new ArrayList<>();
        players.forEach(player -> playerNames.add(player.getName().getName()));
        return playerNames;

    }

    private Players initPlayer() {
        List<Player> players = new ArrayList<>();
        List<String> playerNames = getPlayerNames();

        for (String name : playerNames) {
            Name playerName = new Name(name);
            players.add(new Player(playerName));
        }
        return new Players(players);
    }

    private List<String> getPlayerNames() {
        return InputView.askPlayerNames();
    }

    private ParticipantCardsDto toParticipantCardsDto(Participant participant) {
        return new ParticipantCardsDto(
                participant.getName().getName(),
                participant.getCardsInfo(),
                participant.getScore()
        );
    }
}
