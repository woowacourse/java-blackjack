package controller;

import domain.Betting;
import domain.BlackjackGame;
import domain.Dealer;
import domain.GameResultCalculator;
import domain.Name;
import domain.Player;
import domain.PlayerInfo;
import domain.Players;
import domain.TotalFinalResult;
import dto.DealerFinalResultDto;
import dto.PlayerDto;
import dto.PlayersDto;
import dto.ResultDto;
import dto.TotalFinalResultsDto;
import java.util.ArrayList;
import java.util.List;
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
        List<String> names = inputView.readParticipants();
        BlackjackGame blackjackGame = BlackjackGame.start(getPlayerInfos(names, getBettingAmounts(names)));

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

    private List<Integer> getBettingAmounts(List<String> names) {
        return names.stream().map(inputView::readPlayerBettingAmount).toList();
    }

    private List<PlayerInfo> getPlayerInfos(List<String> names, List<Integer> amounts) {
        List<PlayerInfo> playerInfos = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Name name = Name.from(names.get(i));
            Betting amount = new Betting(amounts.get(i));
            playerInfos.add(new PlayerInfo(name, amount));
        }
        return playerInfos;
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