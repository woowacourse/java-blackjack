package controller;

import domain.BlackjackGame;
import domain.Dealer;
import domain.GameResultCalculator;
import domain.Player;
import domain.Players;
import domain.TotalFinalResult;
import dto.DealerFinalResultDto;
import dto.PlayerDto;
import dto.PlayersDto;
import dto.ResultDto;
import dto.TotalFinalResultsDto;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        BlackjackGame blackjackGame = BlackjackGame.start(inputView.readParticipants());
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();

        PlayersDto playersDto = PlayersDto.from(players);
        outputView.printHandOutMessage(playersDto);
        outputView.printCardStatus(playersDto, ResultDto.from(dealer));

        addPlayersCard(blackjackGame, players);
        addDealerCards(blackjackGame);
        printCardResults(ResultDto.from(dealer), PlayersDto.from(players));
        printFinalResults(players, dealer);
    }

    private void addPlayersCard(BlackjackGame blackjackGame, Players players) {
        for (Player player : players.getPlayers()) {
            addPlayerCards(blackjackGame, player);
        }
    }

    private void addPlayerCards(BlackjackGame blackjackGame, Player player) {
        while (!player.isBust() && inputView.checkAddCard(player.getName().getName())) {
            blackjackGame.addPlayerCard(player);
            outputView.printPlayerCardStatus(PlayerDto.from(player));
        }
    }

    private void addDealerCards(BlackjackGame blackjackGame) {
        if (blackjackGame.shouldDealerDraw()) {
            outputView.printAddDealerCardMessage();
            blackjackGame.playDealerTurn();
        }
    }

    private void printCardResults(ResultDto resultDto, PlayersDto playersDto) {
        outputView.printCardResult(resultDto, playersDto);
    }

    private void printFinalResults(Players players, Dealer dealer) {
        TotalFinalResult totalFinalResult = GameResultCalculator.checkGameResult(players, dealer);
        DealerFinalResultDto dealerFinalResultDto = DealerFinalResultDto.from(totalFinalResult);
        TotalFinalResultsDto totalFinalResultsDto = TotalFinalResultsDto.from(totalFinalResult);

        outputView.printTotalResult(dealerFinalResultDto, totalFinalResultsDto);
    }
}