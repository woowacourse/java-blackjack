package controller;

import domain.BlackjackGame;
import domain.participant.BettingMoney;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.PlayerCreationInfo;
import domain.participant.Players;
import domain.ProfitCalculator;
import dto.PlayerDto;
import dto.PlayerProfitDto;
import dto.PlayersDto;
import dto.ResultDto;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public void run() {
        BlackjackGame blackjackGame = startGame();
        printInitialStatus(blackjackGame);
        playGame(blackjackGame);
        printGameResult(blackjackGame);
    }

    private BlackjackGame startGame() {
        List<String> names = InputView.readParticipants();
        List<PlayerCreationInfo> playerCreationInfos = createPlayerCreationInfos(names);
        return BlackjackGame.start(playerCreationInfos);
    }

    private void printInitialStatus(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        PlayersDto playersDto = PlayersDto.from(players);

        OutputView.printHandOutMessage(playersDto);
        OutputView.printCardStatus(playersDto, ResultDto.fromDealerInitial(dealer));
    }

    private void playGame(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();
        addPlayersCard(blackjackGame, players);
        addDealerCards(blackjackGame);
    }

    private void printGameResult(BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();
        PlayersDto afterPlayersDto = PlayersDto.from(players);

        printCardResults(ResultDto.from(dealer), afterPlayersDto);
        printProfitResults(players, dealer);
    }

    private List<PlayerCreationInfo> createPlayerCreationInfos(List<String> names) {
        List<PlayerCreationInfo> playerCreationInfos = new ArrayList<>();
        for (String name : names) {
            Integer money = InputView.readBettingMoney(name);
            System.out.println();
            playerCreationInfos.add(PlayerCreationInfo.of(Name.from(name), BettingMoney.of(money)));
        }
        return playerCreationInfos;
    }

    private void addPlayersCard(BlackjackGame blackjackGame, Players players) {
        for (Player player : players.getPlayers()) {
            addPlayerCards(blackjackGame, player);
        }
    }

    private void addPlayerCards(BlackjackGame blackjackGame, Player player) {
        while (!player.isBust() && InputView.checkAddCard(player.getNameValue())) {
            blackjackGame.addPlayerCard(player);
            OutputView.printPlayerCardStatus(PlayerDto.from(player));
        }
    }

    private void addDealerCards(BlackjackGame blackjackGame) {
        boolean dealerDrew = blackjackGame.playDealerTurn();
        if (dealerDrew) {
            OutputView.printAddDealerCardMessage();
        }
    }

    private void printCardResults(ResultDto resultDto, PlayersDto afterPlayersDto) {
        OutputView.printCardResult(resultDto, afterPlayersDto);
    }

    private void printProfitResults(Players players, Dealer dealer) {
        String dealerProfit = ProfitCalculator.formatProfit(
                ProfitCalculator.calculateDealerProfit(players, dealer)
        );

        List<PlayerProfitDto> playerProfitResults = players.getPlayers().stream()
                .map(player -> new PlayerProfitDto(
                        player.getNameValue(),
                        ProfitCalculator.formatProfit(ProfitCalculator.calculatePlayerProfit(player, dealer)
                        )))
                .toList();

        OutputView.printTotalProfit(dealerProfit, playerProfitResults);
    }
}
