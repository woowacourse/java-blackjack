package blackJack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackJack.domain.BlackJackGame;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(getParticipants());

        defaultRound(blackJackGame);
        additionalRound(blackJackGame);

        OutputView.printGameResult(blackJackGame.getParticipants());
        OutputView.printWinOrLoseResult(blackJackGame.getDealer(),
            BlackJackGameResult.ofGameResult(blackJackGame.getDealer(), blackJackGame.getPlayers()));
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
            List<String> playerNames = InputView.inputPlayerNames();
            List<Player> players = createPlayers(playerNames);
            return new Participants(new Dealer(), players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getParticipants();
        }
    }

    private List<Player> createPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
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
