package controller;

import domain.betting.BettingAmount;
import domain.betting.Revenue;
import domain.game.GameManager;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantCardsDto;
import dto.ParticipantRevenueDto;
import java.math.BigDecimal;
import view.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static view.OutputView.printCards;
import static view.OutputView.printDealerMessage;
import static view.OutputView.printFinalCards;
import static view.OutputView.printGameInitialMessage;
import static view.OutputView.printParticipantRevenues;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        Players players = initPlayer();
        GameManager gameManager = new GameManager(players);
        List<String> playerNames = players.getPlayerNames();
        printGameInitialMessage(playerNames);
        gameManager.distributeInitialCards();
        printParticipantCards(gameManager.getDealer(), players);
        playGame(players, gameManager);
        Map<Name, Revenue> profits = gameManager.getParticipantsProfit();
        List<ParticipantRevenueDto> revenueDtos = ParticipantRevenueDto.from(profits);
        endGame(gameManager, players, revenueDtos);
    }

    private void playGame(Players players, GameManager gameManager) {
        for (Player player : players.getPlayers()) {
            playGameWithPlayer(player, gameManager);
        }
        playGameWithDealer(gameManager);
    }

    private void printParticipantCards(Dealer dealer, Players players) {
        printCards(ParticipantCardsDto.from(dealer));
        for (Player player : players.getPlayers()) {
            printCards(ParticipantCardsDto.from(player));
        }
    }

    private void printFinalScores(Players players) {
        for (Player player : players.getPlayers()) {
            printFinalCards(ParticipantCardsDto.from(player));
        }
    }

    public void playGameWithDealer(GameManager gameManager) {
        while (gameManager.getDealer().isContinueGame()) {
            if (!gameManager.getDealer().isContinueGame()) {
                break;
            }
            gameManager.drawCardTo(gameManager.getDealer());
            printDealerMessage();
        }
    }

    public void playGameWithPlayer(Player player, GameManager gameManager) {
        while (player.isContinueGame()) {
            if (isStopGame(player)) {
                break;
            }
            gameManager.drawCardTo(player);
            printCards(ParticipantCardsDto.from(player));
        }
    }

    private boolean isStopGame(Player player) {
        String response = InputView.askContinue(player.getParticipantName());
        if (response.equals("n")) {
            printCards(ParticipantCardsDto.from(player));
            return true;
        }
        return false;
    }

    private void endGame(GameManager gameManager, Players players, List<ParticipantRevenueDto> participantRevenueDtos) {
        printFinalCards(ParticipantCardsDto.from(gameManager.getDealer()));
        printFinalScores(players);
        printParticipantRevenues(participantRevenueDtos);
    }

    private Players initPlayer() {
        List<Player> players = new ArrayList<>();
        List<String> playerNames = getPlayerNames();

        for (String name : playerNames) {
            Name playerName = new Name(name);
            int amount = InputView.askBettingAmount(playerName.getName());
            players.add(new Player(playerName, new BettingAmount(BigDecimal.valueOf(amount))));
        }
        return new Players(players);
    }

    private List<String> getPlayerNames() {
        return InputView.askPlayerNames();
    }
}
