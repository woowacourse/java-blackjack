package blackjack.controller;

import blackjack.domain.participants.BettingResult;
import blackjack.domain.participants.GameBoard;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.BlackJackGameResult;
import blackjack.domain.participants.Profit;
import blackjack.domain.participants.State;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Players players = createPlayers();
        BettingResult bettingResult = bet(players);
        Dealer dealer = new Dealer();
        GameBoard gameBoard = new GameBoard(dealer, players);
        play(gameBoard, bettingResult);
    }

    private Players createPlayers() {
        List<String> names = inputView.readNames();
        List<Player> playerList = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        return new Players(playerList);
    }

    private BettingResult bet(Players players) {
        BettingResult bettingResult = new BettingResult();
        for (Player player : players.getPlayers()) {
            bettingResult.bet(player, new Profit(inputView.readBettingPrice(player.getName().getName())));
            System.out.println();
        }
        return bettingResult;
    }

    private void play(GameBoard gameBoard, BettingResult bettingResult) {
        startSetting(gameBoard);
        proceedPlayerTurn(gameBoard);
        proceedDealerTurn(gameBoard);
        handleScoreResult(gameBoard);
        handleGameResult(gameBoard, bettingResult);
    }

    private void startSetting(GameBoard gameBoard) {
        gameBoard.distributeInitialHands();
        GameParticipant dealer = gameBoard.getDealer();
        ParticipantDto dealerDto = new ParticipantDto(dealer);
        List<ParticipantDto> playersDto = gameBoard.getPlayers()
                .getPlayers()
                .stream()
                .map(ParticipantDto::new)
                .toList();
        outputView.printSetting(dealerDto, playersDto);
        outputView.printNewLine();
    }

    private void proceedPlayerTurn(GameBoard gameBoard) {
        List<Player> players = gameBoard.getPlayers().getPlayers();
        for (Player player : players) {
            proceedOnePlayerTurn(gameBoard, player);
        }
    }

    private void proceedDealerTurn(GameBoard gameBoard) {
        outputView.printNewLine();
        while (gameBoard.isDealerNotOver()) {
            gameBoard.addCardToDealer();
            outputView.printDealerOneMoreCard();
        }
        outputView.printNewLine();
    }

    private void proceedOnePlayerTurn(GameBoard gameBoard, Player player) {
        while (player.canHit() &&
                inputView.readCommand(player.getName().getName())) {
            gameBoard.addCardToPlayer(player);
            outputView.printCurrentCard(new ParticipantDto(player));
            outputView.printNewLine();
        }
    }

    private void handleScoreResult(GameBoard gameBoard) {
        GameParticipant dealer = gameBoard.getDealer();
        ParticipantDto dealerDto = new ParticipantDto(dealer);
        List<ParticipantDto> playersDto = gameBoard.getPlayers()
                .getPlayers()
                .stream()
                .map(ParticipantDto::new)
                .toList();
        outputView.printScoreResult(dealerDto, playersDto);
    }

    private void handleGameResult(GameBoard gameBoard, BettingResult bettingResult) {
        BlackJackGameResult blackJackGameResult = gameBoard.calculateGameResult();
        blackJackGameResult.getGameResult()
                .keySet()
                .forEach((player) -> bettingResult.calculateProfit(player, blackJackGameResult.getState(player)));

        outputView.printGameResult(bettingResult);
    }
}
