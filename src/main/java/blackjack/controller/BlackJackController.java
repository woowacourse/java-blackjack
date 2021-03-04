package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Users;
import blackjack.domain.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;
    private static final int DEALER_REDRAW_STANDARD = 17;

    private final Dealer dealer = new Dealer();

    public void run() {
        Users users = new Users(dealer, InputView.scanPlayerNames());

        CardDeck cardDeck = CardDeck.createDeck();

        initialHit(users, cardDeck);

        OutputView.printInitialComment(users);

        if (dealer.isBlackJack()) {
            OutputView.printResult(users.checkWinOrLose(dealer.getScore()));
            return;
        }
        users.getPlayers()
                .forEach(player -> playGameForEachPlayer(player, cardDeck));
        if (dealer.getScore() < DEALER_REDRAW_STANDARD) {
            dealer.hit(cardDeck.drawCard());
        }
        OutputView.printCardsOfPlayersWithScore(users);
        OutputView.printResult(users.checkWinOrLose(dealer.getScore()));
    }

    private void initialHit(Users users, CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_DRAW_CARD_NUMBER; i++) {
            users.getPlayers()
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
