package controller;

import domain.BlackJackGame;
import domain.TurnAction;
import domain.box.GameResult;
import domain.user.Dealer;
import domain.user.Player;
import dto.ParticipantDTO;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class GameController {

    private final BlackJackGame blackJackGame;

    public GameController(BlackJackGame game) {
        this.blackJackGame = game;
    }

    public void ready() {
        ParticipantDTO participantDTO = new ParticipantDTO();
        blackJackGame.initializeHand();
        blackJackGame.makeParticipants(participantDTO);
        List<Player> players = participantDTO.getPlayers();
        OutputView.printReady(players.stream().map(Player::getName).collect(Collectors.toList()));
        Dealer dealer = participantDTO.getDealer();
        OutputView.printNameAndHand(dealer.getName(), dealer.faceUpInitialHand());
        players.forEach((player) -> OutputView.printNameAndHand(player.getName(), player.faceUpInitialHand()));
        OutputView.printLineSeparator();
    }

    public void play() {
        Player current = blackJackGame.getCurrentPlayer();
        while (current.isPlayer()) {
            playersTurn(current);
            current = blackJackGame.getCurrentPlayer();
        }
        dealerTurn();
    }

    private void playersTurn(Player player) {
        String input = InputView.inputNeedMoreCard(player.getName());
        TurnAction action = TurnAction.getActionByInput(input);
        blackJackGame.playTurn(player, action);
        OutputView.printNameAndHand(player.getName(), player.showHand());
    }

    private void dealerTurn() {
        ParticipantDTO participantDTO = new ParticipantDTO();
        blackJackGame.makeParticipants(participantDTO);
        Dealer dealer = participantDTO.getDealer();
        while (blackJackGame.isDealerUnderThresholds(dealer)) {
            OutputView.printDealerReceivedCard();
            blackJackGame.playTurn(dealer, TurnAction.HIT);
        }
    }

    public void printFinalGameResult() {
        ParticipantDTO participantDTO = new ParticipantDTO();
        blackJackGame.makeParticipants(participantDTO);
        List<Player> players = participantDTO.getPlayers();
        printAllStatus(participantDTO.getDealer(), players);
        List<GameResult> playerBoxResults = blackJackGame.getPlayerBoxResults(players);
        OutputView.printDealerGameResult(blackJackGame.getDealerBoxResult(playerBoxResults), players.size());
        for (int index = 0; index < players.size(); index++) {
            OutputView.printPlayerBoxResult(players.get(index).getName(), playerBoxResults.get(index));
        }
    }

    private void printAllStatus(Dealer dealer, List<Player> players) {
        OutputView.printLineSeparator();
        OutputView.printNameAndHandAndPoint(dealer.getName(), dealer.showHand(), dealer.calculatePoint());
        players.forEach(
            (participant) -> OutputView.printNameAndHandAndPoint(participant.getName(), participant.showHand(),
                participant.calculatePoint()));
    }
}
