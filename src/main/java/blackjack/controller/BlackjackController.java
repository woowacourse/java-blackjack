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
        deck.shuffle();
        List<Player> players = createPlayers();
        Dealer dealer = new Dealer(DEALER_NAME);
        turnStart(deck, dealer, players);
    }

    private void turnStart(Deck deck, Dealer dealer, List<Player> players) {
        handOutCards(deck, dealer, players);
        printHandOutCardsResult(dealer, players);
        drawPhaseStart(deck, players, dealer);
        printDealerPlayersScore(players, dealer);
        calculateBlackJackGameResult(players, dealer);
        OutputView.printGameResult(players, dealer);
    }

    private void printDealerPlayersScore(List<Player> players, Dealer dealer) {
        OutputView.printParticipantCardsWithScore(dealer.getName(), dealer.getCards());
        for (Player player : players) {
            OutputView.printParticipantCardsWithScore(player.getName(), player.getCards());
        }
    }

    private List<Player> createPlayers() {
        List<String> names = InputView.getPlayerNames();
        return names.stream()
            .map(Player::new)
            .collect(Collectors.toList());
    }

    private void handOutCards(Deck deck, Dealer dealer, List<Player> players) {
        OutputView.printHandOutCardsMessage(dealer, players);

        dealer.draw(deck.draw());
        dealer.draw(deck.draw());

        for (Player player : players) {
            player.draw(deck.draw());
            player.draw(deck.draw());
        }
    }

    private void printHandOutCardsResult(Dealer dealer, List<Player> players) {
        OutputView.printParticipantCards(dealer.getName(), dealer.getCards());
        for (Player player : players) {
            OutputView.printParticipantCards(player.getName(), player.getCards());
        }
    }

    private void playersDrawPhaseStart(Deck deck, Player player) {
        while (player.canDraw() && InputView.getWhetherDrawCard(player.getName())) {
            player.draw(deck.draw());
            OutputView.printParticipantCards(player.getName(), player.getCards());
        }

    }

    private void dealerDrawPhaseStart(Deck deck, Dealer dealer) {
        if (dealer.canDraw()) {
            OutputView.printDealerCardDrawMessage();
            dealer.draw(deck.draw());
        }
    }

    private void drawPhaseStart(Deck deck, List<Player> players, Dealer dealer) {
        for (Player player : players) {
            playersDrawPhaseStart(deck, player);
        }
        dealerDrawPhaseStart(deck, dealer);
    }

    private void calculateBlackJackGameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            dealer.matchCards(player.getCards());
            player.matchCards(dealer.getCards());
        }
    }

}
