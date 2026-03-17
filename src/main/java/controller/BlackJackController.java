package controller;

import dto.result.ParticipantCurrentHand;
import dto.result.ProfitResult;
import model.participant.PlayerName;
import java.util.List;
import model.participant.Agreement;
import model.participant.BetPrice;
import model.BlackJackGame;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final BlackJackGame blackJackGame;

    public BlackJackController(BlackJackGame blackJackGame) {
        this.blackJackGame = blackJackGame;
    }

    public void playBlackJackGame() {
        registerParticipant();
        addBet();

        initGame();
        participantsTurn();
        displayResult();
    }

    private void registerParticipant() {
        List<String> playerNamesInput = InputView.getPlayerNames();
        playerNamesInput.forEach(name -> {
           PlayerName playerName = new PlayerName(name);
            blackJackGame.registerPlayer(playerName);
        });
    }

    private void addBet() {
        List<String> playerNames = blackJackGame.getPlayerNames();
        playerNames.forEach(playerName -> blackJackGame.placeBet(playerName, new BetPrice(InputView.getBet(playerName))));
    }

    private void initGame() {
        blackJackGame.initGame();
        OutputView.printInitDeck(blackJackGame.getPlayerCurrentHands(), blackJackGame.getDealerFirstCard());
    }

    private void participantsTurn() {
        drawPlayersTurn();
        drawDealerTurn();
    }

    private void drawPlayersTurn() {
        List<String> playerNames = blackJackGame.getPlayerNames();
        playerNames.forEach(this::drawPlayerTurns);
        OutputView.printNewLine();
    }

    private void drawPlayerTurns(String playerName) {
        while(drawPlayerTurn(playerName));
    }

    private boolean drawPlayerTurn(String playerName) {
        if(!getCondition(playerName)) {
            return false;
        }

        ParticipantCurrentHand currentHand = blackJackGame.drawPlayer(playerName);
        OutputView.printPlayerCurrentDeck(currentHand);

        return !blackJackGame.isBust(playerName);
    }

    private boolean getCondition(String name) {
        return new Agreement(InputView.getDrawCondition(name)).value();
    }

    private void drawDealerTurn() {
        blackJackGame.drawDealer(OutputView::printDealerCardDrawMessage);
    }

    private void displayResult() {
        List<ParticipantCurrentHand> playerHands = blackJackGame.getPlayerCurrentHands();
        ParticipantCurrentHand dealerHand = blackJackGame.getDealerCurrentHand();

        ProfitResult profitResult = blackJackGame.getProfitResult();

        OutputView.printParticipantHandWithScore(dealerHand, playerHands);
        OutputView.printProfitResult(profitResult);
    }
}
