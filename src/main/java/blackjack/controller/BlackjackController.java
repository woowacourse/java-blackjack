package blackjack.controller;

import blackjack.domain.BlackjackManager;
import blackjack.domain.DtoAssembler;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.ResultDto;
import java.util.List;

public class BlackjackController {

    public void play() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.getPlayerNames());
        BlackjackManager blackjackManager = new BlackjackManager(dealer, players);

        initDrawCardsDealerAndAllPlayers(blackjackManager);
        hitOrStayAllPlayers(blackjackManager);
        hitDealerUntilOverLimitScore(blackjackManager);
        printCardsWithScoreOfDealerAndAllPlayers(blackjackManager);
        printBlackjackResult(blackjackManager);
    }

    private void initDrawCardsDealerAndAllPlayers(BlackjackManager blackjackManager) {
        blackjackManager.initDrawCards();
        ParticipantDto dealerInitStatus = DtoAssembler.createDealerInitStatusDto(blackjackManager.getDealer());
        List<ParticipantDto> playerStatuses = DtoAssembler.createPlayerStatusDtos(blackjackManager.getPlayers());
        OutputView.printInitStatuses(dealerInitStatus, playerStatuses);
    }

    private void hitOrStayAllPlayers(BlackjackManager blackjackManager) {
        while (!blackjackManager.isFinishedAllPlayers()) {
            hitOrStayCurrentPlayer(blackjackManager);
            blackjackManager.passTurnToNextPlayer();
        }
        blackjackManager.passTurnToNextPlayer();
    }

    private void hitOrStayCurrentPlayer(BlackjackManager blackjackManager) {
        if (!blackjackManager.isFinishedCurrentPlayer()) {
            blackjackManager.hitOrStayCurrentPlayer(InputView.getHitOrStay(blackjackManager.getCurrentPlayerName()));
            OutputView.printPlayerStatus(DtoAssembler.createPlayerStatusDto(blackjackManager.getCurrentPlayer()));
        }
    }

    private void hitDealerUntilOverLimitScore(BlackjackManager blackjackManager) {
        OutputView.printNewLine();
        while(!blackjackManager.isFinishedDealer()){
            hitOrStayDealer(blackjackManager);
        }
        OutputView.printNewLine();
    }

    private void hitOrStayDealer(BlackjackManager blackjackManager) {
        if(blackjackManager.isDealerScoreOverThenLimit()){
            blackjackManager.stayDealer();
            return;
        }
        blackjackManager.hitDealer();
        OutputView.printDealerHit();
    }

    private void printCardsWithScoreOfDealerAndAllPlayers(BlackjackManager blackjackManager) {
        OutputView.printStatusWithScore(DtoAssembler.createDealerStatusDto(blackjackManager.getDealer()));
        DtoAssembler.createPlayerStatusDtos(blackjackManager.getPlayers()).forEach(OutputView::printStatusWithScore);
    }

    private void printBlackjackResult(BlackjackManager blackjackManager) {
        Dealer dealer = blackjackManager.getDealer();
        Players players = blackjackManager.getPlayers();
        ResultDto dealerResult = DtoAssembler.createDealerResultDto(dealer, players);
        List<ResultDto> playerResultDtos = DtoAssembler.createPlayerResultDtos(dealer, players);
        OutputView.printBlackjackResult(dealerResult, playerResultDtos);
    }
}
