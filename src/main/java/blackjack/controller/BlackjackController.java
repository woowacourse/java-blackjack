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
        List<Player> players = createPlayers();
        Dealer dealer = new Dealer(DEALER_NAME);

        deck.shuffle();
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
        printDealerPlayersScore(players, dealer);

        calculateBlackJackGameResult(players, dealer);
        OutputView.printGameResult(players, dealer);
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

    private void printDealerPlayersScore(List<Player> players, Dealer dealer) {
        OutputView.printParticipantCardsWithScore(dealer);
        players.forEach(OutputView::printParticipantCardsWithScore);
    }

    private void printHandOutCardsResult(Dealer dealer, List<Player> players) {
        OutputView.printDealerCards(dealer.getName(), dealer.getCards());
        players.forEach(OutputView::printPlayerCards);
    }

    private void calculateBlackJackGameResult(List<Player> players, Dealer dealer) {
        players.forEach(player -> player.calculateGameResult(dealer));
    }

}
