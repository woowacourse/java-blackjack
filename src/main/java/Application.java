import deck.Deck;
import deck.ShuffledDeckCreator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import participant.BettingMoney;
import participant.Dealer;
import participant.GameResult;
import participant.Nickname;
import participant.Player;
import participant.Players;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame();

        Deck deck = new Deck(new ShuffledDeckCreator());

        List<Nickname> nicknames = InputView.readNicknames().stream()
                .map(Nickname::new)
                .toList();
        Players players = bettingPlayers(nicknames);
        Dealer dealer = new Dealer();

        for (Player player : players.getPlayers()) {
            blackjackGame.preparePlayerCards(player, deck.draw(), deck.draw());
        }
        blackjackGame.prepareDealer(dealer, deck.draw(), deck.draw());
        OutputView.printInitialCards(dealer, players);

        for (Player player : players.getPlayers()) {
            while (!player.isFinished() && InputView.readDrawOneMore(player.getNickname())) {
                player.hit(deck.draw());
                OutputView.printPlayerCards(player);
            }
        }

        while (dealer.canReceiveCard()) {
            OutputView.printDealerReceivedCard();
            dealer.hit(deck.draw());
        }

        OutputView.printAllCardAndScore(players, dealer);

        int dealerProfit = 0;
        Map<Player, Integer> playersResult = new HashMap<>();

        for (Player player : players.getPlayers()) {
            GameResult gameJudge = GameResult.judge(dealer, player);

            dealerProfit -= player.calculateProfit(gameJudge);
            playersResult.put(player, player.calculateProfit(gameJudge));
        }

        OutputView.printResult(dealerProfit, playersResult);
    }

    private static Players bettingPlayers(List<Nickname> nicknames) {
        List<Player> players = new ArrayList<>();
        for (Nickname nickname : nicknames) {
            int bettingMoney = InputView.readPlayerBettingMoney(nickname.getNickname());
            Player player = new Player(nickname, new BettingMoney(bettingMoney));

            players.add(player);
        }

        return new Players(players);
    }
}
