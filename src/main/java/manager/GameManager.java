package manager;

import game.BlackJack;
import card.CardDeck;
import card.ShuffledDeckGenerator;
import result.GameResult;
import result.MatchResultType;
import participant.Dealer;
import participant.Player;
import participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

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
        }
        receiveAdditionalCard(dealer, blackJack);
        OutputView.printAllParticipantScore(dealer, players);

        GameResult gameResult = new GameResult();
        Map<Player, MatchResultType> playerMatchResult = gameResult.calculatePlayersMatchResult(players, dealer);
        Map<MatchResultType, Long> dealerMatchResult = gameResult.calculateDealerMatchResult(playerMatchResult);

        OutputView.printResult(dealer,dealerMatchResult,playerMatchResult);
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
