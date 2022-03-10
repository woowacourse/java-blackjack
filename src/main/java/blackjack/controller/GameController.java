package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardDeckGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    public void run() {
        List<String> users = InputView.inputUsers();
        CardDeck cardDeck = CardDeckGenerator.createCardDeckByCardNumber();
        Player dealer = new Dealer(cardDeck.drawInitialCard());
        List<Player> players = users.stream()
                .map(user -> new User(user, cardDeck.drawInitialCard()))
                .collect(Collectors.toList());
        ResultView.printDealerCard(dealer);
        for (Player player : players) {
            ResultView.printUserCards(player);
        }
    }
}
