package controller;

import cardGame.BlackJackGame;
import cardGame.SingleMatch;
import controller.dto.SingleWinOrNotResult;
import java.util.ArrayList;
import java.util.List;
import player.Player;
import player.dto.SinglePlayerStatusDto;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void playGame() {
        BlackJackGame blackJackGame = getBlackJackGame();
        int dealerExtraCardCount = blackJackGame.countDealerExtraCard();

        List<SingleWinOrNotResult> winOrNotResults = runGame(blackJackGame, new ArrayList<>());
        List<Boolean> playersWinningInfo = getPlayersWinningInfo(winOrNotResults);

        outputView.printInfo(dealerExtraCardCount);
        outputView.printPariticipantsScore(blackJackGame.getFinalBlackJackGameResult());
        outputView.printDealerResult(countDealerWinning(playersWinningInfo), playersWinningInfo.size());
        outputView.printPlayerResult(winOrNotResults);
    }

    private BlackJackGame getBlackJackGame() {
        List<String> nameString = inputView.inputPlayerNames();
        BlackJackGame blackJackGame = new BlackJackGame(nameString);
        outputView.printInitCardStatus(blackJackGame.getBackJackGameStatus());
        return blackJackGame;
    }

    private List<SingleWinOrNotResult> runGame(BlackJackGame blackJackGame,
                                               List<SingleWinOrNotResult> winOrNotResults) {
        for (SingleMatch singleMatch : blackJackGame.startGame()) {
            Player player = singleMatch.getPlayer();
            retry(singleMatch, player);

            boolean isPlayerWins = singleMatch.isPlayerWins();
            winOrNotResults.add(new SingleWinOrNotResult(player.getName(), isPlayerWins));
        }
        return winOrNotResults;
    }

    private List<Boolean> getPlayersWinningInfo(List<SingleWinOrNotResult> winOrNotResults) {
        List<Boolean> playersWinningInfo = new ArrayList<>();
        for (SingleWinOrNotResult winOrNotResult : winOrNotResults) {
            playersWinningInfo.add(winOrNotResult.isWinner());
        }
        return playersWinningInfo;
    }

    private void retry(SingleMatch singleMatch, Player player) {
        while (!singleMatch.isCanPlayGamePlayer() && inputView.inputPlayerCommand(player.getName())) {
            singleMatch.playRound();
            outputView.printCardsStatus(SinglePlayerStatusDto.from(player));
        }
    }

    private int countDealerWinning(List<Boolean> playersWinningInfo) {
        return (int) playersWinningInfo.stream()
                .filter(winInfo -> !winInfo)
                .count();
    }
}