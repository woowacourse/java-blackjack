package controller;

import domain.Betting;
import domain.BettingResultCalculator;
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
import dto.ProfitDto;
import dto.ResultDto;
import dto.TotalFinalResultsDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        BlackjackGame blackjackGame = initGame();
        playGame(blackjackGame);
        printGameResult(blackjackGame);
    }

    private BlackjackGame initGame() {
        List<Name> names = inputPlayerNames();
        BlackjackGame blackjackGame = BlackjackGame.start(getPlayerInfos(names));
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();

        PlayersDto playersDto = PlayersDto.from(players);
        outputView.printHandOutMessage(playersDto);
        outputView.printCardStatus(playersDto, ResultDto.from(dealer));

        return blackjackGame;
    }

    private void playGame(BlackjackGame blackjackGame) {
        addPlayersCard(blackjackGame, blackjackGame.getPlayers());
        addDealerCards(blackjackGame);
    }

    private void printGameResult(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        printCardResults(ResultDto.from(dealer), PlayersDto.from(players));
        printProfit(players, dealer);
    }

    private List<Name> inputPlayerNames() {
        try {
            List<String> inputNames = inputView.readParticipants();
            return inputNames.stream()
                    .map(Name::from)
                    .toList();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }

    private Betting inputBettingAmount(Name name) {
        try {
            int amount = inputView.readPlayerBettingAmount(name.getName());
            return new Betting(amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputBettingAmount(name);
        }
    }

    private List<PlayerInfo> getPlayerInfos(List<Name> names) {
        List<PlayerInfo> playerInfos = new ArrayList<>();
        for (Name name : names) {
            playerInfos.add(new PlayerInfo(name, inputBettingAmount(name)));
        }
        return playerInfos;
    }

    private void addPlayersCard(BlackjackGame blackjackGame, Players players) {
        for (Player player : players.getPlayers()) {
            addPlayerCards(blackjackGame, player);
        }
    }

    private void addPlayerCards(BlackjackGame blackjackGame, Player player) {
        while (!player.isBust() && inputAddCard(player)) {
            blackjackGame.addPlayerCard(player);
            outputView.printPlayerCardStatus(PlayerDto.from(player));
        }
    }

    private boolean inputAddCard(Player player) {
        try {
            return inputView.checkAddCard(player.getName().getName());
        } catch (IllegalArgumentException e) {
            return inputAddCard(player);
        }
    }

    private void addDealerCards(BlackjackGame blackjackGame) {
        while (blackjackGame.shouldDealerDraw()) {
            outputView.printAddDealerCardMessage();
            blackjackGame.playDealerTurn();
        }
    }

    private void printCardResults(ResultDto resultDto, PlayersDto playersDto) {
        outputView.printCardResult(resultDto, playersDto);
    }

    private void printProfit(Players players, Dealer dealer) {
        TotalFinalResult totalFinalResult = GameResultCalculator.checkGameResult(players, dealer);
        Map<Name, Integer> playerProfits = BettingResultCalculator.calculatePlayersProfit(totalFinalResult);

        ProfitDto dealerProfitDto = ProfitDto.fromDealer(BettingResultCalculator.calculateDealerProfit(playerProfits));
        outputView.printProfitMessage();
        outputView.printProfit(dealerProfitDto);
        for (Map.Entry<Name, Integer> entry : playerProfits.entrySet()) {
            outputView.printProfit(ProfitDto.fromPlayer(entry.getKey().getName(), entry.getValue()));
        }
    }
}