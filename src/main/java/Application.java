import deck.Deck;
import deck.ShuffledDeckCreator;
import java.util.List;
import participant.Dealer;
import participant.Nickname;
import participant.Player;
import participant.Players;
import betting.Profit;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();

        List<Nickname> nicknames = InputView.readNicknames().stream()
                .map(Nickname::new)
                .toList();
        Deck deck = new Deck(new ShuffledDeckCreator());
        Players players = blackjackGame.betPlayers(nicknames);
        Dealer dealer = new Dealer();

        for (Player player : players.getPlayers()) {
            blackjackGame.preparePlayerCards(player, deck);
        }
        blackjackGame.prepareDealer(dealer, deck);
        OutputView.printInitialCards(dealer, players);

        for (Player player : players.getPlayers()) {
            while (!player.isFinished()) {
                if (!InputView.readDrawOneMore(player.getNickname())) {
                    player.stand();
                    break;
                }
                player.hit(deck.draw());
                OutputView.printPlayerCards(player);
            }
        }

        while (dealer.canReceiveCard()) {
            OutputView.printDealerReceivedCard();
            dealer.hit(deck.draw());
        }

        OutputView.printAllCardAndScore(players, dealer);
        Profit profit = blackjackGame.calculateProfitResult(dealer, players);
        OutputView.printResult(profit);
    }
}
