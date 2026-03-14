package controller;

import domain.betting.BettingAmount;
import domain.betting.BettingAmounts;
import domain.betting.CalculateProfit;
import domain.betting.Revenue;
import domain.game.GameManager;
import domain.game.GameResultManager;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantCardsDto;
import dto.ParticipantRevenueDto;
import java.util.HashMap;
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
        BettingAmounts bettingAmounts = initBettingManager(players);
        OutputView.printGameInitialMessage(playersNames);
        gameManager.distributeInitialCards();
        printParticipantCards(gameManager.getDealer(), players);
        playGame(players, gameManager);
        CalculateProfit calculateProfit = new CalculateProfit(bettingAmounts);
        GameResultManager gameResultManager =
                new GameResultManager(calculateProfit, players, gameManager.getDealer());
        //TODO: 여기 아래 가 변경되어야 함. 지금 gameResuㅣㅅ
        //Map<String, GameResult> gameResult = gameResultManager.getGameResult();
        Map<Name, Revenue> profits = gameResultManager.getParticipantsProfit();
        List<ParticipantRevenueDto> revenueDtos = toParticipantRevenueDtos(profits);

        endGame(gameManager, players, revenueDtos);
    }

    private BettingAmounts initBettingManager(Players players) {
        Map<Name, BettingAmount> bettingAmounts = new HashMap<>();
        players.forEach(player -> {
            int amount = InputView.askBettingAmount(player.getName().getName());
            bettingAmounts.put(player.getName(), new BettingAmount(amount));
        });
        return new BettingAmounts(bettingAmounts);
    }

    private List<ParticipantRevenueDto> toParticipantRevenueDtos(Map<Name, Revenue> profits) {
        List<ParticipantRevenueDto> revenueDtos = new ArrayList<>();
        for (Map.Entry<Name, Revenue> entry : profits.entrySet()) {
            revenueDtos.add(new ParticipantRevenueDto(
                    entry.getKey().getName(),
                    entry.getValue().getMoney()
            ));
        }
        return revenueDtos;
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

    private void endGame(GameManager gameManager, Players players, List<ParticipantRevenueDto> participantRevenueDtos) {
        OutputView.printFinalCards(toParticipantCardsDto(gameManager.getDealer()));
        printFinalScores(players);
        // OutputView.printGameResult(gameResult);
        OutputView.printParticipantRevenues(participantRevenueDtos);
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
