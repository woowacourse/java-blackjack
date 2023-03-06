package controller;

import common.ExecuteContext;
import domain.model.BlackJackResultMaker;
import domain.model.CardDistributor;
import domain.model.Dealer;
import domain.model.Player;
import domain.model.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final CardDistributor cardDistributor;
    private final BlackJackResultMaker blackJackResultMaker;

    public BlackJackController(final CardDistributor cardDistributor, final BlackJackResultMaker blackJackResultMaker) {
        this.cardDistributor = cardDistributor;
        this.blackJackResultMaker = blackJackResultMaker;
    }

    public void play() {
        final List<Player> players = getParticipants();
        final Dealer dealer = new Dealer();
        dealInitialCards(dealer, players);
        getPlayerAdditionalCard(players);
        getDealerAdditionalCard(dealer);
        printTotalCardState(dealer, players);
        printResult(dealer, players);
    }

    private List<Player> getParticipants() {
        return ExecuteContext.workWithExecuteStrategy(() -> {
            List<String> names = InputView.inputNames();
            return names.stream()
                .map(Player::from)
                .collect(Collectors.toList());
        });
    }

    private void dealInitialCards(final Dealer dealer, final List<Player> players) {
        cardDistributor.dealInitialCards(dealer, players);
        OutputView.printInitialCards(dealer, players);
    }

    private void getPlayerAdditionalCard(final List<Player> players) {
        players.forEach(this::getPlayerAdditionalCard);
    }

    private void getPlayerAdditionalCard(final Player player) {
        boolean hit = false;
        while (!player.isBust() && (hit = getPlayerHitOrStand(player))) {
            cardDistributor.giveCard(player);
            OutputView.printCards(player);
        }
        if (!hit) {
            OutputView.printCards(player);
        }
    }

    private boolean getPlayerHitOrStand(final Player player) {
        return ExecuteContext.workWithExecuteStrategy(() -> InputView.inputPlayerHitOrStand(player));
    }

    private void getDealerAdditionalCard(final Dealer dealer) {
        while (dealer.canReceiveCard()) {
            OutputView.printDealerReceptionNotice();
            cardDistributor.giveCard(dealer);
        }
    }

    private void printTotalCardState(final Dealer dealer, final List<Player> players) {
        OutputView.printTotalCardState(dealer, players);
    }

    private void printResult(final Dealer dealer, final List<Player> players) {
        Result dealerResult = blackJackResultMaker.makeDealerResult(dealer, players);
        Map<Player, Result> playerResult = blackJackResultMaker.makeParticipantsResult(dealer, players);
        OutputView.printDealerResult(dealerResult, dealer);
        OutputView.printResult(playerResult);
    }
}
