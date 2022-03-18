package blackJack.controller;

import blackJack.domain.BlackJackGame;
import blackJack.domain.card.Deck;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

public class BlackJackController {

    public void run() {
        final BlackJackGame blackJackGame = new BlackJackGame(Deck.create(), getParticipants());

        additionalBattingMoney(blackJackGame);
        defaultRound(blackJackGame);
        additionalRound(blackJackGame);

        final BlackJackGameResult blackJackGameResult = blackJackGame.calculateResult();
        OutputView.printGameResult(blackJackGame.getParticipants());
        OutputView.printEarningResult(blackJackGame.getDealer(), blackJackGameResult);
    }

    private void additionalBattingMoney(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.getPlayers()) {
            player.betting(getBettingMoney(player));
        }
    }

    private void defaultRound(BlackJackGame blackJackGame) {
        blackJackGame.defaultDistributeCards();
        OutputView.printInitCardResult(blackJackGame.getParticipants());
    }

    private void additionalRound(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.getPlayers()) {
            additionalPlayerTurn(blackJackGame, player);
        }
        additionalDealerTurn(blackJackGame);
    }

    private void additionalPlayerTurn(BlackJackGame blackjackGame, Player player) {
        while (blackjackGame.isAvailableDistributeCard(player) && blackjackGame.isApproveDrawCard(getYesOrNo(player))) {
            blackjackGame.distributeCard(player);
            OutputView.printNowHoldCardInfo(player);
        }
    }

    private void additionalDealerTurn(BlackJackGame blackjackGame) {
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
            return InputView.inputBattingMoney(player.getName());
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
