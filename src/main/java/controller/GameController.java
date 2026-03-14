package controller;

import domain.Betting;
import domain.BettingResultCalculator;
import domain.BlackjackGame;
import domain.CardShuffler;
import domain.Dealer;
import domain.GameResultCalculator;
import domain.Name;
import domain.Names;
import domain.Player;
import domain.PlayerInfo;
import domain.Players;
import domain.TotalFinalResult;
import dto.PlayerDto;
import dto.PlayersDto;
import dto.ProfitDto;
import dto.ResultDto;
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

    public void start(CardShuffler cardShuffler) {
        BlackjackGame blackjackGame = initGame(cardShuffler);
        playGame(blackjackGame);
        printGameResult(blackjackGame);
    }

    private BlackjackGame initGame(CardShuffler cardShuffler) {
        Names names = inputPlayerNames();
        BlackjackGame blackjackGame = BlackjackGame.start(getPlayerInfos(names), cardShuffler);
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

    private Names inputPlayerNames() {
        try {
            List<String> inputNames = inputView.readParticipants();
            List<Name> names = inputNames.stream()
                    .map(Name::from)
                    .toList();
            return new Names(names);
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
            outputView.printErrorMessage(e.getMessage());
            return inputBettingAmount(name);
        }
    }

    private List<PlayerInfo> getPlayerInfos(Names names) {
        List<PlayerInfo> playerInfos = new ArrayList<>();
        for (Name name : names.getNames()) {
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
            outputView.printErrorMessage(e.getMessage());
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