package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.Users;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        Players players = Repeater.supplierGetsArgumentOfFunction(Players::of, InputView::scanPlayerNames);
        Dealer dealer = new Dealer();
        startGame(dealer, players);
        printOverallResults(dealer, players);
    }

    private void startGame(Dealer dealer, Players players) {
        Users users = new Users(dealer, players);
        CardDeck cardDeck = CardDeck.createDeck();
        users.dealTwoCards(cardDeck);

        OutputView.printInitialComment(dealer, players);
        OutputView.printCardsOfDealerWithOneCardOpened(dealer);
        OutputView.printCardsOfPlayersWithoutScore(players);

        if (!dealer.isBlackJack()) {
            players.players().forEach(player -> playGameForEachPlayer(player, cardDeck));
            drawCardsOfDealerUntilOver16Score(dealer, cardDeck);
        }
    }

    private void playGameForEachPlayer(Player player, CardDeck cardDeck) {
        while (player.canContinue() &&
                Repeater.supplierGetsArgumentOfFunction(InputView::isHit, player::getName)) {
            player.draw(cardDeck.drawCard());
            OutputView.printCardsOfUser(player);
        }
    }

    private void drawCardsOfDealerUntilOver16Score(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canContinue()) {
            dealer.draw(cardDeck.drawCard());
            OutputView.printDealerGetNewCardsMessage();
        }
    }

    private void printOverallResults(Dealer dealer, Players players) {
        OutputView.printCardsOfUsersWithScore(new Users(dealer, players));
        OutputView.printResult(players.generateResultsMapAgainstDealer(dealer));
    }
}
