package controller;

import java.util.List;
import model.Agreement;
import model.BetPrice;
import model.Player;
import model.PlayerName;
import model.dto.ParticipantWinning;
import model.dto.PlayerResult;
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
        participantTurn();
        displayResult();
    }

    private void registerParticipant() {
        List<String> playerNamesInput = InputView.getPlayerNames();
        playerNamesInput.forEach(name -> {
            PlayerName playerName = new PlayerName(name);
            Player player = new Player(playerName);
            blackJackGame.registerPlayer(player);
        });
    }

    private void addBet() {
        for(String player : blackJackGame.getPlayerNames()) {
            BetPrice betPrice = new BetPrice(InputView.getBet(player));
            blackJackGame.setBet(player, betPrice);
        }
    }

    private void initGame() {
        blackJackGame.initGame();
        OutputView.printInitDeck(blackJackGame.getPlayerResults(), blackJackGame.getDealerFirstCard());
    }

    private void participantTurn() {
        drawPlayersTurn();
        drawDealerTurn();
    }

    private void drawPlayersTurn() {
        for(String playerName : blackJackGame.getPlayerNames()) {
            drawPlayerTurns(playerName);
        }
        OutputView.printNewLine();
    }

    private void drawPlayerTurns(String playerName) {
        while(drawPlayerTurn(playerName));
    }

    private boolean drawPlayerTurn(String playerName) {
        if(!getCondition(playerName)) {
            return false;
        }

        blackJackGame.drawPlayer(playerName);
        OutputView.printPlayerCurrentDeck(blackJackGame.getPlayerResult(playerName));

        return !blackJackGame.isBust(playerName);
    }

    private boolean getCondition(String name) {
        return new Agreement(InputView.getDrawCondition(name)).get();
    }

    private void drawDealerTurn() {
        blackJackGame.drawDealer(OutputView::printDealerCardDrawMessage);
    }

    private void displayResult() {
        List<PlayerResult> playerResults = blackJackGame.getPlayerResults();
        PlayerResult dealerResult = blackJackGame.getDealerResult();
        ParticipantWinning gameResult = blackJackGame.getGameResult();

        OutputView.printPlayersScore(dealerResult, playerResults);
        OutputView.printResult(gameResult);
    }
}
