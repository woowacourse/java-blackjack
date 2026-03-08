package controller;

import domain.BlackjackGame;
import domain.Dealer;
import domain.GameResultCalculator;
import domain.Player;
import domain.Players;
import domain.TotalFinalResult;
import dto.DealerFinalResultDto;
import dto.DealerResultDto;
import dto.PlayerDto;
import dto.PlayersDto;
import dto.TotalFinalResultsDto;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameController {

    public void start() {
        List<String> names = InputView.readParticipants();
        BlackjackGame blackjackGame = BlackjackGame.start(names);


        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();

        PlayersDto playersDto = PlayersDto.from(players);
        OutputView.printHandOutMessage(playersDto);
        OutputView.printCardStatus(playersDto, DealerResultDto.from(dealer));

        // 플레이어 카드 추가 수령
        addPlayersCard(blackjackGame, players);

        // 딜러 게임 진행
        addDealerCards(blackjackGame);

        // 카드 결과, 점수 출력
        printCardResults(DealerResultDto.from(dealer), PlayersDto.from(players));

        // 최종 승패 출력
        printFinalResults(players, dealer);
    }

    private void addPlayersCard(BlackjackGame blackjackGame, Players players) {
        for (Player player : players.getPlayers()) {
            addPlayerCards(blackjackGame, player);
        }
    }

    private void addPlayerCards(BlackjackGame blackjackGame, Player player) {
        while (!player.isBust() && InputView.checkAddCard(player.getName().getName())) {
            blackjackGame.addPlayerCard(player);
            OutputView.printPlayerCardStatus(PlayerDto.from(player));
        }
    }

    private void addDealerCards(BlackjackGame blackjackGame) {
        if (blackjackGame.shouldDealerDraw()) {
            OutputView.printAddDealerCardMessage();
            blackjackGame.playDealerTurn();
        }
    }

    private void printCardResults(DealerResultDto dealerResultDto, PlayersDto playersDto) {
        OutputView.printCardResult(dealerResultDto, playersDto);
    }

    private void printFinalResults(Players players, Dealer dealer) {
        TotalFinalResult totalFinalResult = GameResultCalculator.checkGameResult(players, dealer);
        DealerFinalResultDto dealerFinalResultDto = DealerFinalResultDto.from(totalFinalResult);
        TotalFinalResultsDto totalFinalResultsDto = TotalFinalResultsDto.from(totalFinalResult);

        OutputView.printTotalResult(dealerFinalResultDto, totalFinalResultsDto);
    }
}