package controller;

import domain.*;
import dto.ParticipantCardsDto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static view.OutputView.printCards;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        List<Player> players = initPlayer();
        GameManager gameManager = new GameManager(players);

        // TODO: 이름 출력 로직
        List<String> playersNames = getPlayerNames(players);
        OutputView.printGameInitialMessage(playersNames);

        gameManager.distributeInitialCards();
        printParticipantCards(gameManager);

        // TODO: ------------------------------------------------------------
        playGame(players, deck, dealer);

        Map<String, Boolean> gameResult = getGameResult(dealer, players);

        endGame(dealer, players, gameResult);
    }

    private static void printParticipantCards(GameManager gameManager) {
        ParticipantCardsDto dealerDto = gameManager.getDealerDto();
        OutputView.printCards(dealerDto);
        List<ParticipantCardsDto> playerDtos = gameManager.getPlayerDtos();
        for (ParticipantCardsDto playerDto : playerDtos) {
            OutputView.printCards(playerDto);
        }
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

    private void playGame(List<Player> players, Deck deck, Dealer dealer) {
        for (Player player : players) {
            playGameWithPlayer(player, deck);
        }

        playGameWithDealer(dealer, deck);
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

    private void playGameWithDealer(Dealer dealer, Deck deck) {
        while (dealer.isContinueGame()) {
            if (!dealer.isContinueGame()) {
                break;
            }
            distributeCard(dealer, deck);
            OutputView.printDealerMessage();
        }
    }

    private void playGameWithPlayer(Player player, Deck deck) {
        while (player.isContinueGame()) {
            if (isContinueGame(player)) {
                break;
            }
            distributeCard(player, deck);
            printCards(player.getParticipantCardsDto());
        }
    }

    private boolean isContinueGame(Player player) {
        if (!isContinue(InputView.askContinue(player.getName()))) {
            printCards(player.getParticipantCardsDto());
            return true;
        }
        return false;
    }

    private List<String> getPlayerNames(List<Player> players) {
        return players.stream().map(Participant::getName).toList();
    }

    private boolean isContinue(String response) {
        if (response.equals("y")) {
            return true;
        }

        return false;
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
