import domain.GameCommand;
import domain.card.Deck;
import domain.card.RandomShuffleStrategy;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.GameProfit;
import domain.result.GameResult;
import domain.result.GameStatistics;
import domain.result.Referee;
import java.util.List;
import java.util.Map;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    public void run() {
        Deck deck = new Deck(new RandomShuffleStrategy());
        Players players = createPlayers(deck);
        Dealer dealer = new Dealer();
        dealer.initHand(deck);

        showCardNames(players, dealer);
        playTurn(players, deck, dealer);
        judge(dealer, players);
    }

    private List<String> getPlayerNames() {
        String input = InputView.readPlayerNames();
        return Parser.parseByDelimiter(input);
    }

    private Players createPlayers(Deck deck) {
        List<String> playerNames = getPlayerNames();

        List<Player> players = playerNames.stream()
                .map(name -> createPlayer(name, deck))
                .toList();

        return new Players(players);
    }

    private Player createPlayer(String name, Deck deck) {
        int amount = Parser.parseByBattingAmount(InputView.readBattingAmount(name));

        Player player = new Player(name, amount);
        player.initHand(deck);
        return player;
    }


    private void showCardNames(Players players, Dealer dealer) {
        OutputView.showIntroMessage(players.getPlayerNames());
        OutputView.showDealerCardName(dealer.firstCardNames());
        players.players().forEach(
                player -> OutputView.showCardName(player.getName(), player.createCardNames())
        );
    }

    private void playPlayerTurn(Players players, Deck deck) {
        players.players().forEach(player -> determineGameState(deck, player));
    }

    private void determineGameState(Deck deck, Player player) {
        String input = InputView.readHitOrStand(player.getName());
        GameCommand gameCommand = GameCommand.from(input);

        if (gameCommand.isNo()) {
            OutputView.showCardName(player.getName(), player.createCardNames());
            return;
        }

        player.playTurn(deck);
        OutputView.showCardName(player.getName(), player.createCardNames());

        if (!player.isBust()) {
            determineGameState(deck, player);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.stay()) {
            OutputView.showDealerMessage();
            dealer.playTurn(deck);
        }
    }

    private void playTurn(Players players, Deck deck, Dealer dealer) {
        playPlayerTurn(players, deck);
        playDealerTurn(dealer, deck);
        OutputView.showCardAndScore("딜러", dealer.createCardNames(), dealer.getScore());
        players.players().forEach(
                player -> OutputView.showCardAndScore(player.getName(), player.createCardNames(), player.getScore())
        );
    }

    private void judge(Dealer dealer, Players players) {
        Referee referee = new Referee();
        Map<Player, GameResult> playerResultMap = referee.judge(dealer, players);
        GameStatistics statistics = new GameStatistics(playerResultMap);
        OutputView.showGameResult(statistics.getPlayerResult(), statistics.getDealerResult());

        GameProfit gameProfit = new GameProfit(playerResultMap);
        OutputView.showDealerProfitResult(gameProfit.getDealerProfit());
        OutputView.showPlayerProfitResult(gameProfit.getPlayerProfit());
    }
}
