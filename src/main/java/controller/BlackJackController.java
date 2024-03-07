package controller;

import cardGame.BlackJackGame;
import cardGame.SingleMatch;
import cardGame.dto.ParticipantsTotalGameResult;
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
        List<String> nameString = inputView.inputPlayerNames();

        BlackJackGame blackJackGame = new BlackJackGame(nameString);

        outputView.printInitCardStatus(blackJackGame.getBackJackGameStatus());

        List<SingleWinOrNotResult> winOrNotResults = new ArrayList<>();
        List<Boolean> playersWinningInfo = new ArrayList<>();

        int dealerExtraCardCount = blackJackGame.countDealerExtraCard();

        for (SingleMatch singleMatch : blackJackGame.startGame()) {
            Player player = singleMatch.getPlayer();

            while (!singleMatch.isCanPlayGamePlayer() && inputView.inputPlayerCommand(player.getName())) {
                singleMatch.playRound();
                outputView.printCardsStatus(SinglePlayerStatusDto.from(player));
            }
            boolean isPlayerWins = singleMatch.isPlayerWins();
            winOrNotResults.add(new SingleWinOrNotResult(player.getName(), isPlayerWins));
            playersWinningInfo.add(isPlayerWins);
        }

        outputView.printInfo(dealerExtraCardCount);

        ParticipantsTotalGameResult participantsTotalGameResult = blackJackGame.getFinalBlackJackGameResult();

        outputView.printPariticipantsScore(participantsTotalGameResult);
        outputView.printDealerResult(countDealerWinning(playersWinningInfo), playersWinningInfo.size());
        outputView.printPlayerResult(winOrNotResults);
    }

    private int countDealerWinning(List<Boolean> playersWinningInfo) {
        return (int) playersWinningInfo.stream()
                .filter(winInfo -> !winInfo)
                .count();
    }
}
