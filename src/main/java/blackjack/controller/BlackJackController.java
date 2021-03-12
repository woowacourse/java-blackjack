package blackjack.controller;

import blackjack.domain.Money;
import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Results;
import blackjack.domain.user.*;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import static java.util.stream.Collectors.*;

public class BlackJackController {

    public void run() {
        Players players = createPlayers();
        Dealer dealer = new Dealer();
        startGame(dealer, players);
        printOverallResults(dealer, players);
    }

    private Players createPlayers() {
        Names names = Repeater.supplierGetsArgumentOfFunction(Names::of, InputView::scanPlayerNames);
        return names.stream()
                .map(name -> Repeater.supplier(() -> {
                    Money money = Money.of(InputView.scanBettingMoney(name.getName()));
                    return new Player(name, money);
                }))
                .collect(collectingAndThen(toList(), Players::of));
    }

    private void startGame(Dealer dealer, Players players) {
        Users users = Users.of(dealer, players);
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
        // TODO 생성하지 않고 처리할 수 있도록 리팩토링
        OutputView.printCardsOfUsersWithScore(Users.of(dealer, players));

        Results results = players.generateResultsMapAgainstDealer(dealer);
        OutputView.printResult(results, results.generateDealerResult());
    }
}
