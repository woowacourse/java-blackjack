package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final String DEALER_NAME = "딜러";

    public void run() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(DEALER_NAME);
        List<Player> players = createPlayers();

        turnStart(deck, dealer, players);
    }

    private List<Player> createPlayers() {
        List<String> names = InputView.getPlayerNames();
        return names.stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    private void turnStart(Deck deck, Dealer dealer, List<Player> players) {
        handOutCards(deck, dealer, players);
        printHandOutCardsResult(dealer, players);

        startPlayerDrawPhase(deck, players);
        startDealerDrawPhase(deck, dealer);
        printDealerPlayersScore(dealer, players);

        calculateBlackJackGameResult(dealer, players);
        printGameResult(dealer, players);
    }

    private void handOutCards(Deck deck, Dealer dealer, List<Player> players) {
        OutputView.printHandOutCardsMessage(dealer, players);
        dealer.initialDraw(deck);
        players.forEach(player -> player.initialDraw(deck));
    }

    private void startPlayerDrawPhase(Deck deck, List<Player> players) {
        players.forEach(player -> {
            while (player.canDraw() && InputView.getWhetherDrawCard(player.getName())) {
                player.draw(deck);
                OutputView.printPlayerCards(player);
            }
        });
    }

    private void startDealerDrawPhase(Deck deck, Dealer dealer) {
        if (dealer.canDraw()) {
            OutputView.printDealerCardDrawMessage();
            dealer.draw(deck);
        }
    }

    private void calculateBlackJackGameResult(Dealer dealer, List<Player> players) {
        players.forEach(player -> player.calculateGameResult(dealer));
    }

    private void printDealerPlayersScore(Dealer dealer, List<Player> players) {
        OutputView.printParticipantCardsWithScore(dealer);
        players.forEach(OutputView::printParticipantCardsWithScore);
    }

    private void printHandOutCardsResult(Dealer dealer, List<Player> players) {
        OutputView.printDealerCards(dealer.getName(), dealer.getCards());
        players.forEach(OutputView::printPlayerCards);
    }

    private void printGameResult(Dealer dealer, List<Player> players) {
        OutputView.printGameResult(players, dealer);
    }

}




