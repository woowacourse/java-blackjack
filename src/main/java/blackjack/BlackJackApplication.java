package blackjack;

import blackjack.domain.GameResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.inputNames());
        CardDeck cardDeck = new CardDeck();

        distributeFirstCards(dealer, players, cardDeck);
        drawMoreCards(dealer, players, cardDeck);
        printCalculatedResult(dealer, players);
    }

    private static void printCalculatedResult(Dealer dealer, Players players) {
        GameResult gameResult = new GameResult(dealer, players);
        OutputView.printUsersCardsAndScore(dealer, players);
        OutputView.printFinalResult(dealer, gameResult);
    }

    private static void drawMoreCards(Dealer dealer, Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            drawMorePlayerCard(cardDeck, player);
        }
        while (dealer.canDrawCard()) {
            OutputView.printDealerOneMoreCard();
            dealer.drawCard(cardDeck);
        }
    }

    private static void distributeFirstCards(Dealer dealer, Players players, CardDeck cardDeck) {
        dealer.drawCard(cardDeck, 2);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, 2);
        }
        OutputView.printCardDistribution(players);
        OutputView.printUsersCards(dealer, players);
    }

    private static void drawMorePlayerCard(CardDeck cardDeck, Player player) {
        while (player.canDrawCard() && InputView.inputMoreCard(player)) {
            player.drawCard(cardDeck);
            OutputView.printPlayerCards(player);
        }
    }
}
