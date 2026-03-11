import domain.GameCommand;
import domain.card.Deck;
import domain.participant.*;
import java.util.List;
import domain.result.GameStatistics;
import domain.result.Referee;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    public void run() {
        Deck deck = new Deck();
        Players players = createPlayers(deck);
        Dealer dealer = new Dealer(deck);

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

        Player player = Player.from(name, amount);
        player.initHand(deck);
        return player;
    }


    private void showCardNames(Players players, Dealer dealer) {
        OutputView.showIntroMessage(players);
        OutputView.showDealerCardName(dealer);
        OutputView.showPlayerCardName(players);
    }

    private void playPlayerTurn(Players players, Deck deck) {
        players.getPlayers().forEach(player -> determineGameState(deck, player));
    }

    private void determineGameState(Deck deck, Player player) {
        String input = InputView.readHitOrStand(player.getName());
        GameCommand gameCommand = GameCommand.from(input);

        if (gameCommand.isNo()) {
            return;
        }

        player.playTurn(deck);
        OutputView.showCardName(player);

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
        OutputView.showResult(dealer, players);
    }

    private void judge(Dealer dealer, Players players) {
        Referee referee = new Referee();
        GameStatistics statistics = referee.judge(dealer, players);
        OutputView.showGameResult(statistics);
    }
}
