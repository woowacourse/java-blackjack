package blackJack.controller;

import blackJack.domain.BlackJackGame;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BettingAmount;
import blackJack.domain.result.ResultOfProfit;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private static final int ADDITIONAL_CARD_COUNT = 1;

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(getParticipants());
        ResultOfProfit resultOfProfit = new ResultOfProfit(getBettingAmounts(blackJackGame.getPlayers()));
        blackJackGame.firstCardDispensing();
        OutputView.printInitCardResult(blackJackGame.getParticipants());

        doPlayerGame(blackJackGame);
        doDealerGame(blackJackGame);
        OutputView.printGameResult(blackJackGame.getParticipants());
        OutputView.printResultOfProfit(resultOfProfit, blackJackGame.getDealer());
    }

    private Participants getParticipants() {
        try {
            return new Participants(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getParticipants();
        }
    }

    private Map<Player, BettingAmount> getBettingAmounts(List<Player> players) {
        Map<Player, BettingAmount> bettingAmounts = new LinkedHashMap<>();
        for (Player player : players) {
            bettingAmounts.put(player, getBettingAmount(player));
        }
        return bettingAmounts;
    }

    private BettingAmount getBettingAmount(Player player) {
        try {
            return new BettingAmount(InputView.inputBettingAmount(player));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getBettingAmount(player);
        }
    }

    private void doPlayerGame(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(blackJackGame, player);
        }
    }

    private void doEachPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (player.canHit() && isHitCard(player)) {
            blackJackGame.distributeCard(player, ADDITIONAL_CARD_COUNT);
            OutputView.printNowHoldCardInfo(player);
        }
    }

    private boolean isHitCard(Player player) {
        try {
            String choice = InputView.inputOneMoreCard(player.getName());
            return YesOrNo.YES == YesOrNo.find(choice);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return isHitCard(player);
        }
    }

    private void doDealerGame(BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        while (dealer.canHit()) {
            blackJackGame.distributeCard(dealer, ADDITIONAL_CARD_COUNT);
        }
        OutputView.printDealerReceiveCardCount(blackJackGame.getDealer());
    }
}
