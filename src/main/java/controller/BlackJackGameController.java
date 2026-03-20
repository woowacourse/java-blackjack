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
import java.math.BigDecimal;
import java.util.HashMap;
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
        BettingAmounts bettingAmounts = initBettingManager(players);
        printGameInitialMessage(playerNames);
        gameManager.distributeInitialCards();
        printParticipantCards(gameManager.getDealer(), players);
        playGame(players, gameManager);
        CalculateProfit calculateProfit = new CalculateProfit(bettingAmounts);
        GameResultManager gameResultManager =
                new GameResultManager(calculateProfit, players, gameManager.getDealer());
        Map<Name, Revenue> profits = gameResultManager.getParticipantsProfit();
        List<ParticipantRevenueDto> revenueDtos = ParticipantRevenueDto.from(profits);
        endGame(gameManager, players, revenueDtos);
    }

    private BettingAmounts initBettingManager(Players players) {
        Map<Name, BettingAmount> bettingAmounts = new HashMap<>();
        // TODO: for문으로 players를 순회하는 것이랑 지금 forEach랑 어떤 차이가 있는가?
        // TODO: player가 bettingAmount를 가지고 있지 않고, Controller 메서드 안의 지역변수로 관리되고 있는 게 좋은 방향인가?
        players.forEach(player -> {
            int amount = InputView.askBettingAmount(player.getName().getName());
            bettingAmounts.put(player.getName(), new BettingAmount(BigDecimal.valueOf(amount)));
        });
        return new BettingAmounts(bettingAmounts);
    }

    // TODO: player와 bettingAmount관계에서 도메인 설계를 다시 고려해보라. forEach로 바꾼게 어떤 장점이 있는가? 이 메서드로 view로직을 전달하면 잘 분리가 된걸까?
    private void playGame(Players players, GameManager gameManager) {
        players.forEach(player -> playGameWithPlayer(player, gameManager));
        playGameWithDealer(gameManager);
    }

    private void printParticipantCards(Dealer dealer, Players players) {
        printCards(ParticipantCardsDto.from(dealer));
        players.forEach(player -> printCards(ParticipantCardsDto.from(player)));
    }

    private void printFinalScores(Players players) {
        players.forEach(player -> printFinalCards(ParticipantCardsDto.from(player)));
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
        String response = InputView.askContinue(player.getName().getName());
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
            players.add(new Player(playerName));
        }
        return new Players(players);
    }

    private List<String> getPlayerNames() {
        return InputView.askPlayerNames();
    }
}
