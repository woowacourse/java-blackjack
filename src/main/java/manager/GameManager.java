package manager;

import game.BlackJack;
import card.CardDeck;
import card.ShuffledDeckGenerator;
import participant.Dealer;
import participant.Player;
import participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class GameManager {

    public void play() {
        List<String> values = InputView.readPlayerNames();

        Players players = Players.from(values);
        CardDeck deck = new CardDeck(new ShuffledDeckGenerator().generateDeck());
        Dealer dealer = new Dealer();

        BlackJack blackJack = new BlackJack(players, dealer, deck);
        blackJack.dealInitialCards();

        OutputView.printDivisionStart(dealer, players.getPlayers());

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, blackJack);
        };

        receiveAdditionalCard(dealer, blackJack);
        OutputView.printAllParticipantScore(dealer, players);

        OutputView.printResult(dealer, blackJack.calculateMatchResult(), players);
    }

    private void receiveAdditionalCard(Player player, BlackJack blackJack) {
        while (player.canHit() && agreeIntent(player)) {
            blackJack.receiveAdditionalCard(player);
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer, BlackJack blackJack) {
        while (dealer.canHit()) {
            blackJack.receiveAdditionalCard(dealer);
            OutputView.printHittingDealer(dealer);
        }
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }

}
