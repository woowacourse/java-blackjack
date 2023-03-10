package controller;

import common.ExecuteContext;
import domain.BlackJackGameRunner;
import domain.CardGenerator;
import domain.Player;

import java.util.ArrayList;
import java.util.List;

import view.MultiResultsDto;
import view.SingleResultDto;
import view.ViewRenderer;
import view.NameCardScoreDto;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final CardGenerator cardGenerator;
    private BlackJackGameRunner runner;

    public BlackJackController(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void play() {
        runner = initGameRunner();
        giveInitialCards();
        givePlayerAdditionalCard();
        giveDealerAdditionalCard();
        printTotalCardState();
        printResult();
    }

    private BlackJackGameRunner initGameRunner() {
        return ExecuteContext.workWithExecuteStrategy(() ->
            BlackJackGameRunner.of(cardGenerator, InputView.inputNames()));
    }

    private void giveInitialCards() {
        List<NameCardScoreDto> nameCardScores = ViewRenderer.toNameCardScore(runner.givePlayersInitialCards());
        OutputView.printInitializedPlayers(nameCardScores);
        OutputView.printFirstCard(ViewRenderer.toNameCardScore(runner.giveDealerInitialCards()));
        OutputView.printCards(nameCardScores);
    }

    private void givePlayerAdditionalCard() {
        while (runner.isGameOnPlayersHitStage()) {
            Player player = runner.getCurrentPlayerOnHitStage();
            boolean hit = ExecuteContext.workWithExecuteStrategy(() ->
                InputView.inputPlayerHitOrStand(ViewRenderer.toNameCardScore(player)));
            runner.handlePlayerHit(hit);
            OutputView.printCards(ViewRenderer.toNameCardScore(player));
        }
    }

    private void giveDealerAdditionalCard() {
        while (runner.giveDealerIfReceivable()) {
            OutputView.printDealerReceptionNotice();
        }
    }

    private void printTotalCardState() {
        List<NameCardScoreDto> nameCardScores = new ArrayList<>();
        nameCardScores.add(ViewRenderer.toNameCardScore(runner.getDealer()));
        List<Player> players = runner.getPlayers();
        players.forEach(player -> nameCardScores.add(ViewRenderer.toNameCardScore(player)));
        OutputView.printTotalCardState(nameCardScores);
    }

    private void printResult() {
        MultiResultsDto multiResults = ViewRenderer.toMultiResults(runner.getDealer(), runner.makeDealerResult());
        OutputView.printMultiResult(multiResults);
        List<SingleResultDto> singleResults = ViewRenderer.toSingleResults(runner.makePlayersResult());
        OutputView.printSingleResult(singleResults);
    }
}
