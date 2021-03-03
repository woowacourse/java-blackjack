package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Players;
import blackjack.domain.User;
import blackjack.domain.card.CardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;

    public void run() {
        Players players = new Players(InputView.scanPlayerNames());
        Dealer dealer = new Dealer();
        CardDeck cardDeck = CardDeck.createDeck();

        List<User> users = new ArrayList<>(players.getPlayers());
        users.add(0, dealer);

        initialHit(users, cardDeck);

        OutputView.printInitialComment(users);
    }

    private void initialHit(List<User> users, CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_DRAW_CARD_NUMBER; i++) {
            users.stream()
                    .forEach(user -> user.hit(cardDeck.drawCard()));
        }
    }
}
