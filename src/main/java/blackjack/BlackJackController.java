package blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    
    private static final int BASE_CARD_COUNT = 2;
    
    public void run() {
        List<Player> players = enterPlayers();
        Dealer dealer = Dealer.create();
        dealBaseCards(dealer, players);
        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);
        printResult(dealer, players);
    }
    
    private List<Player> enterPlayers() {
        String[] playerNames = InputView.getPlayerName()
                                        .split(",");
        
        return Arrays.stream(playerNames)
                     .map(Player::from)
                     .collect(Collectors.toList());
    }
    
    private void dealBaseCards(Dealer dealer, List<Player> players) {
        for (int i = 0; i < BASE_CARD_COUNT; i++) {
            dealer.draw();
            players.forEach(dealer::deal);
        }
        OutputView.printDealtBaseCards(dealer, players);
    }
    
    private void progressPlayersTurn(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            while (player.canReceive() && InputView.wantsReceive(player)) {
                dealer.deal(player);
                OutputView.printNameAndHand(player);
            }
        }
    }
    
    private void progressDealerTurn(Dealer dealer) {
        while (dealer.canReceive()) {
            dealer.draw();
            OutputView.printDealerDrewMessage();
        }
    }
    
    private void printResult(Dealer dealer, List<Player> players) {
        OutputView.printSummaryStatistics(dealer, players);
        OutputView.printGameResult(dealer, players);
    }
}
