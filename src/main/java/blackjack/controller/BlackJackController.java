package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Users;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;

    private final Users users;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackController() {
        this.dealer = new Dealer();
        this.cardDeck = CardDeck.createDeck();
        this.users = new Users(dealer, InputView.scanPlayerNames());
        initialHit(users);
        OutputView.printInitialComment(users);
    }

    public void run() {
        if (dealer.isBlackJack()) {
            OutputView.printResult(users.generateResultsMapAgainstDealer());
            return;
        }
        users.getPlayers()
                .forEach(this::playGameForEachPlayer);
        while (dealer.hasToDrawACard()) {
            dealer.hit(cardDeck.drawCard());
            OutputView.printDealerGetNewCardsMessage();
        }
        OutputView.printCardsOfUsersWithScore(users);
        OutputView.printResult(users.generateResultsMapAgainstDealer());
    }


    private void initialHit(Users users) {
        for (int i = 0; i < INITIAL_DRAW_CARD_NUMBER; i++) {
            users.gerUsers()
                    .forEach(user -> user.hit(cardDeck.drawCard()));
        }
    }

    private void playGameForEachPlayer(Player player) {
        while (!player.isStay()) {
            requestHitOrNot(player);
        }
    }

    private void requestHitOrNot(Player player) {
        if (InputView.isHit(player.getName())) {
            player.hit(cardDeck.drawCard());
            OutputView.printCardsOfUser(player);
            return;
        }
        player.stay();
    }
}
