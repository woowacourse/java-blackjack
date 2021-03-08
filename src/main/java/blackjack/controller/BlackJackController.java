package blackjack.controller;

import blackjack.domain.GameTable;
import blackjack.domain.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    private static final String HIT_CONTINUE = "Y";

    public void run() {
        try {
            OutputView.printPlayersGuideMessage();
            Dealer dealer = new Dealer();
            Players players = makePlayers(InputView.inputPlayers());
            playGame(dealer,players);
            revealResult(dealer,players);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
        }
    }

    private Players makePlayers(List<String> inputPlayers) {
        return new Players(inputPlayers.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    private void playGame(Dealer dealer, Players players) {
        GameTable gameTable = new GameTable(dealer, players);
        gameTable.drawAtFirst();
        drawPlayers(gameTable, players);
        drawDealer(gameTable, dealer);
    }

    private void revealResult(Dealer dealer, Players players) {
        OutputView.printCardsAndScore(dealer, players);
        Result result = new Result(dealer, players);
        List<Integer> matchResult = dealer.calculateMatchResult(result.getResult());
        OutputView.printResult(matchResult, result.getResult());
    }

    private void drawPlayers(GameTable gameTable, Players players) {
        tryHit(gameTable, players);
    }

    public void tryHit(GameTable gameTable, Players players) {
        players.getPlayers()
            .forEach(player -> askHit(gameTable, player));
    }

    private void askHit(GameTable gameTable, Player player) {
        while (player.canHit() && wantCard(player)) {
            gameTable.draw(player);
            OutputView.printUserCards(player);
        }
    }

    private boolean wantCard(Player player) {
        OutputView.printHitGuideMessage(player);
        String hitValue = InputView.getHitValue();
        return HIT_CONTINUE.equals(hitValue);
    }
    private void drawDealer(GameTable gameTable, Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printDealerHitMessage();
            gameTable.draw(dealer);
        }
    }
}
