package blackJack.controller;

import blackJack.domain.BlackjackGame;
import blackJack.domain.card.Deck;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackjackGameResult;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

public class BlackjackController {

    public void run() {
        final BlackjackGame blackjackGame = new BlackjackGame(Deck.create(), getParticipants());

        additionalBettingMoney(blackjackGame);
        defaultRound(blackjackGame);
        additionalRound(blackjackGame);

        final BlackjackGameResult blackjackGameResult = blackjackGame.calculateResult();
        OutputView.printGameResult(blackjackGame.getParticipants());
        OutputView.printEarningResult(blackjackGame.getDealer(), blackjackGameResult);
    }

    private void additionalBettingMoney(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            player.betting(getBettingMoney(player));
        }
    }

    private void defaultRound(BlackjackGame blackjackGame) {
        blackjackGame.defaultDistributeCards();
        OutputView.printInitCardResult(blackjackGame.getParticipants());
    }

    private void additionalRound(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            additionalPlayerTurn(blackjackGame, player);
        }
        additionalDealerTurn(blackjackGame);
    }

    private void additionalPlayerTurn(BlackjackGame blackjackGame, Player player) {
        while (blackjackGame.isAvailableDistributeCard(player) && blackjackGame.isApproveDrawCard(getYesOrNo(player))) {
            blackjackGame.distributeCard(player);
            OutputView.printNowHoldCardInfo(player);
        }
    }

    private void additionalDealerTurn(BlackjackGame blackjackGame) {
        while (blackjackGame.isAvailableDistributeCard(blackjackGame.getDealer())) {
            blackjackGame.distributeCard(blackjackGame.getDealer());
        }
        OutputView.printDealerReceiveCardCount(blackjackGame.getDealer());
    }

    private Participants getParticipants() {
        try {
            return Participants.fromNames(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            return getParticipants();
        }
    }

    private int getBettingMoney(Player player) {
        try {
            return InputView.inputBettingMoney(player.getName());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getBettingMoney(player);
        }
    }

    private YesOrNo getYesOrNo(Player player) {
        try {
            return YesOrNo.find(InputView.inputOneMoreCard(player.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getYesOrNo(player);
        }
    }
}
