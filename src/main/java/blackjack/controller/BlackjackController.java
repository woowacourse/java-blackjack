package blackjack.controller;

import blackjack.domain.blackjack.Blackjack;
import blackjack.domain.player.*;
import blackjack.domain.player.info.BettingMoney;
import blackjack.domain.player.info.PlayerInfo;
import blackjack.domain.player.info.Name;
import blackjack.domain.player.info.Names;
import blackjack.domain.result.Result;
import blackjack.view.input.BlackjackCommand;
import blackjack.view.input.InputView;
import blackjack.view.output.PlayerView;
import blackjack.view.output.ResultView;

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
        this.blackjack.acceptPlayers(playerInfos);
        PlayerView.printPlayers(this.blackjack.getDealer(), this.blackjack.getGamePlayers());
    }

    private void processGame() {
        processGamePlayers();
        processDealer();
        PlayerView.printPlayersWithScore(this.blackjack.getDealer(), this.blackjack.getGamePlayers());
    }

    private void processGamePlayers() {
        this.blackjack.getGamePlayers()
                      .forEach(this::processGamePlayer);
    }

    private void processGamePlayer(final GamePlayer gamePlayer) {
        while (gamePlayer.isReceivable()) {
            final BlackjackCommand command = inputCommand(gamePlayer);
            if (command.isHit()) {
                this.blackjack.participantHitCard(gamePlayer);
                PlayerView.printGamePlayer(gamePlayer);
            }
            if (command.isStand()) {
                gamePlayer.stand();
                return;
            }
        }
    }

    private void checkGameResult() {
        final Result result = this.blackjack.checkResult();
        ResultView.printResult(result);
    }

    private void processDealer() {
        final Dealer dealer = this.blackjack.getDealer();
        if (dealer.isReceivable()) {
            PlayerView.printDealerDrawMessage();
            this.blackjack.participantHitCard(dealer);
            return;
        }
        PlayerView.printDealerNotDrawMessage();
    }

    private BlackjackCommand inputCommand(final GamePlayer gamePlayer) {
        return InputView.inputBlackjackCommand(gamePlayer.getNameAsString());
    }
}
