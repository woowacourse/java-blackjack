package blackJack.domain;

import java.util.List;
import java.util.stream.Collectors;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import blackJack.domain.result.YesOrNo;
import blackJack.domain.view.InputView;
import blackJack.domain.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = initBlackJackGame();
        blackJackGame.firstCardDispensing();
        OutputView.printInitCardResult(blackJackGame.getDealer(), blackJackGame.getPlayers());
        doPlayerGame(blackJackGame);
        doDealerGame(blackJackGame);
        OutputView.printDealerReceiveCardCount(blackJackGame.getDealer());
        OutputView.printGameResult(blackJackGame.getDealer(), blackJackGame.getPlayers());
    }

    private BlackJackGame initBlackJackGame() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
            return new BlackJackGame(new Dealer(), players);
        } catch (IllegalArgumentException e) {
            return initBlackJackGame();
        }
    }

    private void doPlayerGame(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(blackJackGame, player);
        }
    }

    private void doEachPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (blackJackGame.hasNextTurn(player) && getOneMoreCard(player)) {
            blackJackGame.distributeCard(player, 1);
            OutputView.printNowHoldCardInfo(player);
        }
    }

    private boolean getOneMoreCard(Player player) {
        try {
            String choice = InputView.inputOneMoreCard(player.getName());
            return YesOrNo.YES == YesOrNo.find(choice);
        } catch (IllegalArgumentException e) {
            return getOneMoreCard(player);
        }
    }

    private void doDealerGame(BlackJackGame blackJackGame){
        Dealer dealer = blackJackGame.getDealer();
        while(blackJackGame.hasNextTurn(dealer)){
            blackJackGame.distributeCard(dealer, 1);
        }
    }
}
