package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Users;
import blackjack.domain.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    private final Users users;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackController() {
        this.dealer = new Dealer();
        this.cardDeck = CardDeck.createDeck();
        this.users = new Users(dealer, InputView.scanPlayerNames());
        playersBetting(users.getPlayers());
        users.initialHit(cardDeck);
        OutputView.printInitialComment(users);
    }

    public void run() {
        if (dealer.isFinished()) {
            users.stay();
            OutputView.printResult(users);
            return;
        }
        users.getPlayers().forEach(this::playGameForEachPlayer);

        while (!dealer.isFinished()) {
            dealer.hit(cardDeck.drawCard());
            OutputView.printDealerGetNewCardsMessage();
        }
        OutputView.printCardsOfPlayersWithScore(users);
        OutputView.printResult(users);
    }

    private void playersBetting(List<Player> players) {
        for (Player player : players) {
            player.betting(InputView.inputBettingMoney(player));
        }
        dealer.betting(0);
    }

    private void playGameForEachPlayer(Player player) {
        while (!player.isFinished() && InputView.isHit(player.getName())) {
            player.hit(cardDeck.drawCard());
            OutputView.printCardsOfPlayer(player);
        }
        player.stay();
    }
}
