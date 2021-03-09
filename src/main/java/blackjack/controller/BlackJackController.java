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
    private final Players players;
    private final Dealer dealer;
    private final CardDeck cardDeck;
    private final Users users;

    public BlackJackController() {
        this.dealer = new Dealer();
        this.cardDeck = CardDeck.createDeck();
        this.players = Repeater.repeatOnError(() -> Players.of(InputView.scanPlayerNames()));
        this.users = new Users(this.dealer, this.players);
    }

    public void run() {
        dealingTwoCards(users);

        if (dealer.isBlackJack()) {
            OutputView.printResult(players.generateResultsMapAgainstDealer(dealer));
            return;
        }

        players.players().forEach(this::playGameForEachPlayer);
        drawCardsOfDealerUntilOver16Score();

        printOverallResults();
    }

    private void drawCardsOfDealerUntilOver16Score() {
        while (dealer.hasToDrawACard()) {
            dealer.addCard(cardDeck.drawCard());
            OutputView.printDealerGetNewCardsMessage();
        }
    }

    private void dealingTwoCards(Users users) {
        users.dealTwoCards(cardDeck);
        OutputView.printInitialComment(dealer, players);
        OutputView.printCardsOfDealerWithOneCardOpened(dealer);
        OutputView.printCardsOfPlayersWithoutScore(players);
    }

    private void playGameForEachPlayer(Player player) {
        while (!player.isBust() && !player.isBlackJack() && Repeater.repeatOnError(() -> InputView.isHit(player.getName()))) {
            player.addCard(cardDeck.drawCard());
            OutputView.printCardsOfUser(player);
        }
    }

    private void printOverallResults() {
        OutputView.printCardsOfUsersWithScore(users);
        OutputView.printResult(players.generateResultsMapAgainstDealer(dealer));
    }
}
