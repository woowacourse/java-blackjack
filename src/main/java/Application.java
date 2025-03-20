import betting.BettingMoney;
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

        Deck deck = new Deck(new ShuffledDeckCreator());
        Dealer dealer = new Dealer();

        List<Nickname> nicknames = InputView.readNicknames().stream()
                .map(Nickname::new)
                .toList();
        Players players = new Players();

        for (Nickname nickname : nicknames) {
            int bettingMoney = InputView.readPlayerBettingMoney(nickname.getNickname());
            players.enterPlayer(new Player(nickname, new BettingMoney(bettingMoney)));
        }

        blackjackGame.preparePlayerCards(players, deck);
        blackjackGame.prepareDealerCards(dealer, deck);
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
