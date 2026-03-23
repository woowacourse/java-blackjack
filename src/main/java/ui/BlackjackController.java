package ui;

import domain.BlackjackGame;
import domain.dto.PlayerCreateDto;
import domain.dto.TotalProfit;
import domain.participant.Player;
import java.util.List;
import ui.dto.GameResultDto;
import ui.dto.ParticipantCardsDto;
import ui.dto.PlayerDto;
import ui.view.InputView;
import ui.view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        List<PlayerCreateDto> playerInfos = readPlayerInfo();

        BlackjackGame blackjackGame = BlackjackGame.createNewGame(playerInfos);

        resultView.printParticipantsCards(
                ParticipantCardsDto.from(blackjackGame.getPlayers(), blackjackGame.getDealer()));

        playerHitStand(blackjackGame);
        dealerHitStand(blackjackGame);

        TotalProfit participantsProfits = blackjackGame.getParticipantsProfits();

        GameResultDto gameResultDto = GameResultDto.toDto(blackjackGame.getDealer(),
                participantsProfits.playerProfits(),
                participantsProfits.dealerProfit());

        resultView.printGameResult(gameResultDto);
    }

    private List<PlayerCreateDto> readPlayerInfo() {
        return inputView.readPlayersInfo().stream()
                .map(ui.dto.PlayerCreateDto::toDomain)
                .toList();
    }

    private void playerHitStand(BlackjackGame blackjackGame) {
        while (blackjackGame.isPlayerHitAvailable()) {
            hitPlayer(blackjackGame);
        }
    }

    private void hitPlayer(BlackjackGame blackjackGame) {
        if (!blackjackGame.isCurrentPlayerFinished()) {
            Player player = blackjackGame.currentHitPlayer();
            blackjackGame.hitPlayer(inputView.readHitStand(player.getName().getValue()));
            resultView.printCards(PlayerDto.toDto(player));
            return;
        }
        blackjackGame.changeToNextPlayer();
    }

    private void dealerHitStand(BlackjackGame blackjackGame) {
        List<Boolean> hitHistory = blackjackGame.hitStandDealer();
        resultView.printDealerHitStand(hitHistory);
    }
}
