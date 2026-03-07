import domain.GameCommand;
import domain.GameStatistics;
import domain.Players;
import domain.Referee;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    public void run() {
        Deck deck = new Deck();
        Players players = createPlayers(deck);
        Dealer dealer = new Dealer(deck.dealInitialCards());

        showCardNames(players, dealer);
        playTurn(players, deck, dealer);
        judge(dealer, players);
    }

    private Players createPlayers(Deck deck) {
        List<String> playerNames = InputView.readPlayerNames();
        return new Players(playerNames, deck);
    }

    private void showCardNames(Players players, Dealer dealer) {
        OutputView.showIntroMessage(players);
        OutputView.showDealerCardName(dealer);
        OutputView.showPlayerCardName(players);
    }

    private void playTurn(Players players, Deck deck, Dealer dealer) {
        playPlayerTurn(players, deck);
        playDealerTurn(dealer, deck);
        OutputView.showResult(dealer, players);
    }

    private void playPlayerTurn(Players players, Deck deck) {
        players.getPlayers()
                .forEach(player -> determineGameState(deck, player));
    }

    private void determineGameState(Deck deck, Player player) {
        while (player.isHit()) {
            String input = InputView.readHitOrStand(player.getName());
            GameCommand gameCommand = GameCommand.from(input);
            if (gameCommand.isNo()) {
                player.changeState();
                break;
            }
            player.playTurn(deck);
            OutputView.showCardName(player);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isHit()) {
            OutputView.showDealerMessage();
            dealer.playTurn(deck);
        }
    }

    private void judge(Dealer dealer, Players players) {
        Referee referee = new Referee();
        GameStatistics statistics = referee.judge(dealer, players);
        OutputView.showGameResult(statistics);
    }
}
