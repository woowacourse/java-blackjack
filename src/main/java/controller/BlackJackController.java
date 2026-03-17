package controller;

import dto.ParticipantCurrentHandResponse;
import dto.ParticipantProfitResponse;
import model.card.Card;
import model.result.ParticipantCurrentHand;
import model.result.ParticipantProfit;
import model.result.ProfitResult;
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
        playerNamesInput.forEach(blackJackGame::registerPlayer);
    }

    private void addBet() {
        List<String> playerNames = blackJackGame.getPlayerNames();
        playerNames.forEach(playerName -> blackJackGame.placeBet(playerName, new BetPrice(InputView.getBet(playerName))));
    }

    private void initGame() {
        blackJackGame.initGame();
        List<ParticipantCurrentHandResponse> hands = blackJackGame.getPlayerCurrentHands().stream()
                .map(this::getCurrentHandDto)
                .toList();
        OutputView.printInitDeck(hands, blackJackGame.getDealerFirstCard().getString());
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
        while (drawPlayerTurn(playerName));
    }

    private boolean drawPlayerTurn(String playerName) {
        if (!getCondition(playerName)) {
            return false;
        }

        ParticipantCurrentHandResponse currentHand = getCurrentHandDto(blackJackGame.drawPlayer(playerName));
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
        printParticipantHandResult();
        printParticipantsProfitResult();
    }

    private ParticipantCurrentHandResponse getCurrentHandDto(ParticipantCurrentHand participantCurrentHand) {
        List<String> deck = participantCurrentHand.deck().stream()
                .map(Card::getString)
                .toList();
        return new ParticipantCurrentHandResponse(participantCurrentHand.name(), deck, participantCurrentHand.score());
    }

    private void printParticipantHandResult() {
        List<ParticipantCurrentHandResponse> playerHands = blackJackGame.getPlayerCurrentHands().stream()
                .map(this::getCurrentHandDto)
                .toList();
        ParticipantCurrentHandResponse dealerHand = getCurrentHandDto(blackJackGame.getDealerCurrentHand());

        OutputView.printParticipantHandWithScore(dealerHand, playerHands);
    }

    private void printParticipantsProfitResult() {
        ProfitResult profitResult = blackJackGame.getProfitResult();

        ParticipantProfitResponse dealerProfitResult = getProfitResultDto(profitResult.dealerProfit());
        List<ParticipantProfitResponse> playersProfitResult = profitResult.playerProfit().stream()
                .map(this::getProfitResultDto)
                .toList();
        OutputView.printProfitResult(dealerProfitResult, playersProfitResult);
    }

    private ParticipantProfitResponse getProfitResultDto(ParticipantProfit profitResult) {
        return new ParticipantProfitResponse(profitResult.name(), profitResult.profit());
    }
}
