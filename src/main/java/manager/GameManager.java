package manager;

import bet.BetStatus;
import bet.Money;
import game.BlackJackGame;
import card.CardDeck;
import card.ShuffledDeckGenerator;
import result.GameResult;
import result.MatchResultType;
import participant.Dealer;
import participant.Player;
import participant.Players;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    public void play() {
        Players players = new Players(InputView.readPlayerNames());
        CardDeck deck = new CardDeck(new ShuffledDeckGenerator().generateDeck());
        Dealer dealer = new Dealer();
        BetStatus betStatus = settingBetMoney(players);

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);
        blackJackGame.dealInitialCards();

        OutputView.printDivisionStart(dealer, players.getPlayers());

        for (Player player : players.getPlayers()) {
            receiveAdditionalCard(player, blackJackGame);
        }
        receiveAdditionalCard(dealer, blackJackGame);
        OutputView.printAllParticipantScore(dealer, players);

        GameResult gameResult = new GameResult();
        Map<Player, MatchResultType> playerMatchResult = gameResult.calculatePlayersMatchResult(players, dealer);
        Map<Player, Long> playersProfitResult = betStatus.calculateBetResult(playerMatchResult);
        OutputView.printProfitResult(betStatus.calculateDealerBetResult(playersProfitResult), playersProfitResult);
    }

    private BetStatus settingBetMoney(Players players) {
        Map<Player, Money> initialBetStatus = new HashMap<>();
        for (Player player : players.getPlayers()) {
            long inputBetMoney = InputView.readBetMoney(player.getNickname());
            initialBetStatus.put(player, new Money(inputBetMoney));
        }
        return new BetStatus(initialBetStatus);
    }

    private void receiveAdditionalCard(Player player, BlackJackGame blackJackGame) {
        while (player.canHit() && agreeIntent(player)) {
            blackJackGame.receiveAdditionalCard(player);
            OutputView.printCurrentHands(player);
        }
    }

    private void receiveAdditionalCard(Dealer dealer, BlackJackGame blackJackGame) {
        while (dealer.canHit()) {
            blackJackGame.receiveAdditionalCard(dealer);
            OutputView.printHittingDealer(dealer);
        }
    }

    private boolean agreeIntent(Player player) {
        return Intent.from(InputView.readIntent(player.getNickname())).equals(Intent.Y);
    }

}
