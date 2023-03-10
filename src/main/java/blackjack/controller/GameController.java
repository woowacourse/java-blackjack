package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Bank;
import blackjack.domain.BlackjackGame;
import blackjack.domain.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

// TODO: 2023/03/10 남은 기능 모두 구현하고, Amend해서 이전 커밋과 하나로 통합해서 commit, push 하기
public final class GameController {

    private static final String HIT = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = initGame();

        betMoney(blackjackGame);
        playGame(blackjackGame);
        finishGame(blackjackGame);
    }

    private BlackjackGame initGame() {
        List<Player> players = createPlayers();
        return new BlackjackGame(new Dealer(), new Players(players));
    }

    private List<Player> createPlayers() {
        List<String> playerNames = inputView.readPlayerNames();

        return playerNames.stream()
                .map(Player::new)
                .collect(toList());
    }

    private void betMoney(final BlackjackGame blackjackGame) {
        Bank bank = blackjackGame.getDealer().getBank();

        for (Player player : blackjackGame.getPlayers().getPlayers()) {
            int bettingMoney = inputView.readBettingMoney(player);
            bank.betMoney(player, new Money(bettingMoney));
        }
    }

    private void playGame(final BlackjackGame blackjackGame) {
        blackjackGame.settingGame();
        outputView.printSettingGameMessage(blackjackGame.getDealer(), blackjackGame.getPlayers());

        for (Player player : blackjackGame.getPlayers().getPlayers()) {
            play(blackjackGame.getDealer(), player);
        }

        blackjackGame.getDealer().drawIfLowerOrEquals16();
        outputView.printDealerDrawMessage(blackjackGame.getDealer());
    }

    private void play(final Dealer dealer, final Player player) {
        while (ask(dealer, player) && !player.isBust()) {
            // do nothing
        }
    }

    private boolean ask(final Dealer dealer, final Player player) {
        outputView.printPlayerHand(player);
        String action = inputView.readHitOrStand(player);

        if (action.equals(HIT)) {
            dealer.giveCard(player);
            return true;
        }
        return false;
    }

    private void finishGame(final BlackjackGame blackjackGame) {

    }
}
