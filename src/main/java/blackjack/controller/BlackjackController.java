package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.player.*;
import blackjack.domain.result.Result;
import blackjack.view.BlackjackCommand;
import blackjack.view.InputView;
import blackjack.view.PlayerView;
import blackjack.view.ResultView;

import java.util.List;

public class BlackjackController {
    private final Blackjack blackjack;

    public BlackjackController(final Blackjack blackjack) {
        this.blackjack = blackjack;
    }

    public void playBlackJack() {

        final var playerInfos = askPlayerInfo();

        joinPlayer(playerInfos);

        processGame();

        checkGameResult();
    }

    private List<PlayerInfo> askPlayerInfo() {
        final Names names = askNames();
        return names.stream()
                    .map(this::askBettingMoney)
                    .toList();
    }

    private Names askNames() {
        return Names.from(InputView.inputPlayerNames());
    }

    private PlayerInfo askBettingMoney(final Name name) {
        final BettingMoney bettingMoney = new BettingMoney(InputView.inputBettingMoney(name.asString()));
        return new PlayerInfo(name, bettingMoney);

    }

    private void joinPlayer(final List<PlayerInfo> playerInfos) {
        blackjack.acceptPlayers(playerInfos);
        PlayerView.printPlayers(blackjack.getDealer(), blackjack.getGamePlayers());
    }

    private void processGame() {
        processGamePlayers();
        processDealer();
        PlayerView.printPlayersWithScore(blackjack.getDealer(), blackjack.getGamePlayers());
    }

    private void processGamePlayers() {
        blackjack.getGamePlayers()
                 .forEach(this::processGamePlayer);
    }

    private void processGamePlayer(final GamePlayer gamePlayer) {
        while (gamePlayer.isReceivable()) {
            final BlackjackCommand command = inputCommand(gamePlayer);
            if (command.isHit()) {
                blackjack.participantHitCard(gamePlayer);
                PlayerView.printGamePlayer(gamePlayer);
            }
            if (command.isStand()) {
                gamePlayer.stand();
                return;
            }
        }
    }

    private void checkGameResult() {
        final Result result = blackjack.checkResult();
        ResultView.printResult(result);
    }

    private void processDealer() {
        final Dealer dealer = blackjack.getDealer();
        if (dealer.isReceivable()) {
            PlayerView.printDealerDrawMessage();
            blackjack.participantHitCard(dealer);
            return;
        }
        PlayerView.printDealerNotDrawMessage();
    }

    private BlackjackCommand inputCommand(final GamePlayer gamePlayer) {
        return InputView.inputBlackjackCommand(gamePlayer.getNameAsString());
    }
}
