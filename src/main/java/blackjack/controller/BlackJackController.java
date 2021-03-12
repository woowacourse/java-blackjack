package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.dto.DealerResult;
import blackjack.domain.dto.Results;
import blackjack.domain.user.*;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void run() {
        Players players = Players.of(Repeater.supplierGetsArgumentOfFunction(Names::of, InputView::scanPlayerNames));
        Dealer dealer = new Dealer();
        startGame(dealer, players);
        printOverallResults(dealer, players);
    }

    private void startGame(Dealer dealer, Players players) {
        Users users = new Users(dealer, players);
        CardDeck cardDeck = CardDeck.createDeck();

        users.dealCards(cardDeck);

        OutputView.printInitialComment(dealer, players);
        OutputView.printCardsOfDealerWithOneCardOpened(dealer);
        OutputView.printCardsOfPlayersWithoutScore(players);

        if (!dealer.isBlackJack()) {
            playPlayersTurn(players, cardDeck);
            playDealersTurn(dealer, cardDeck);
        }
    }

    private void playPlayersTurn(Players players, CardDeck cardDeck) {
        players.players().forEach(player -> playGameForEachPlayer(player, cardDeck));
    }

    private void playGameForEachPlayer(Player player, CardDeck cardDeck) {
        while (player.canContinue() &&
                Repeater.supplierGetsArgumentOfFunction(InputView::isHit, player::getName)) {
            player.draw(cardDeck.drawCard());
            OutputView.printCardsOfUser(player);
        }
    }

    private void playDealersTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canContinue()) {
            dealer.draw(cardDeck.drawCard());
            OutputView.printDealerGetNewCardsMessage();
        }
    }

    private void printOverallResults(Dealer dealer, Players players) {
        OutputView.printCardsOfUsersWithScore(new Users(dealer, players));
        Results results = players.generateResultsMapAgainstDealer(dealer);
        OutputView.printDealerResult(DealerResult.of(results));
        OutputView.printResult(results);
    }
}
