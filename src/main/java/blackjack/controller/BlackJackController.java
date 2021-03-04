package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DealerResult;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import javafx.util.Pair;

public class BlackJackController {

    public void play() {
        CardDeck cardDeck = new CardDeck();
        Pair<Dealer, Players> Users = playInit(cardDeck);
        Dealer dealer = Users.getKey();
        Players players = Users.getValue();
        process(cardDeck, dealer, players);
        end(dealer, players);
    }

    private Pair<Dealer, Players> playInit(CardDeck cardDeck) {
        Dealer dealer = new Dealer(cardDeck.generateUserDeck());
        Players players = new Players(cardDeck, InputView.requestPlayers());
        OutputView.showInitiate(dealer, players);
        return new Pair<>(dealer, players);
    }

    private void process(CardDeck cardDeck, Dealer dealer, Players players) {
        processPlayers(cardDeck, players);
        processDealer(cardDeck, dealer);
    }

    private void processPlayers(CardDeck cardDeck, Players players) {
        for (Player player : players.getPlayers()) {
            playerDraw(cardDeck, player);
        }
    }

    private void playerDraw(CardDeck cardDeck, Player player) {
        while (player.isAvailableDraw() && isYes(player)) {
            player.draw(cardDeck.draw());
            OutputView.showPlayerCard(player);
        }
    }

    private boolean isYes(Player player) {
        return InputView.requestMoreDraw(player.getName());
    }

    private void processDealer(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isAvailableDraw()) {
            dealer.draw(cardDeck.draw());
            OutputView.showDealerDraw();
        }
    }

    private void end(Dealer dealer, Players players) {
        OutputView.showScoreResult(dealer, players);
        DealerResult dealerResult = new DealerResult(dealer, players.getPlayers());
        OutputView.showDealerTable(dealerResult);
        OutputView.showPlayerTable(dealer, players);
    }
}
