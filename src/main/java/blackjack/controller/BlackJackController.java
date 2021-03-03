package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.User;
import blackjack.domain.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;
    private static final int DEALER_REDRAW_STANDARD = 17;

    public void run() {
        Players players = new Players(InputView.scanPlayerNames());
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.createDeck();

        List<User> users = new ArrayList<>(players.getPlayers());
        users.add(0, dealer);

        initialHit(users, cardDeck);

        OutputView.printInitialComment(users);

        if (dealer.isBlackJack()) {
            OutputView.printResult(players.checkWinOrLose(dealer.getScore()));
            return;
        }
        players.getPlayers().stream()
                .forEach(player -> playGameForEachPlayer(player, cardDeck));
        if (dealer.getScore() < DEALER_REDRAW_STANDARD) {
            dealer.hit(cardDeck.drawCard());
        }
        OutputView.printCardsOfPlayersWithScore(users);
        OutputView.printResult(players.checkWinOrLose(dealer.getScore()));
    }

    private void initialHit(List<User> users, CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_DRAW_CARD_NUMBER; i++) {
            users.stream()
                    .forEach(user -> user.hit(cardDeck.drawCard()));
        }
    }

    private void playGameForEachPlayer(Player player, CardDeck cardDeck) {
        while (!player.isStay()) {
            requestHitOrNot(player, cardDeck);
        }
    }

    private void requestHitOrNot(Player player, CardDeck cardDeck) {
        if (InputView.isHit(player.getName())) {
            player.hit(cardDeck.drawCard());
            OutputView.printCardsOfPlayer(player);
            return;
        }
        player.stay();
    }
}
