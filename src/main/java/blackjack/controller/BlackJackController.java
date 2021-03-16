package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Names;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BlackJackController {

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.generateUserDeck());

        Names names = new Names(InputView.requestPlayers());
        Players players = new Players(cardDeck, names, InputView.requestMoney(names));
        OutputView.showInitiate(dealer, players);

        processPlayers(cardDeck, players);
        processDealer(cardDeck, dealer);

        endBlackJack(dealer, players);
    }

    private void processPlayers(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            playerDraw(cardDeck, player);
        }
    }

    private void playerDraw(CardDeck cardDeck, Player player) {
        if (!player.isFinished()) {
            String input = InputView.requestMoreDraw(player.getName());
            if (player.wantToDraw(input)) {
                player.draw(cardDeck);
                OutputView.showPlayerCard(player);
                playerDraw(cardDeck, player);
            }
        }
    }

    private void processDealer(CardDeck cardDeck, Dealer dealer) {
        if (!dealer.isFinished()) {
            dealer.draw(cardDeck);
            OutputView.showDealerDraw();
            processDealer(cardDeck, dealer);
        }
    }

    private void endBlackJack(Dealer dealer, Players players) {
        OutputView.showScoreResult(dealer, players);
        Map<Player, Double> playerEarning = playerEarningResult(players);
        double dealerEarning = playerEarning.values()
            .stream()
            .mapToDouble(Double::doubleValue)
            .sum();
        OutputView.showEarning(playerEarning);
        OutputView.showEarning(dealerEarning * -1);
    }

    private Map<Player, Double> playerEarningResult(Players players) {
        Map<Player, Double> playerEarning = new HashMap<>();
        for (Player player : players.getPlayers()) {
            double earning = player.getMoney();
            playerEarning.put(player, earning);
        }
        return Collections.unmodifiableMap(playerEarning);
    }
}
