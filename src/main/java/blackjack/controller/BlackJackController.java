package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    
    public void run() {
        List<Player> players = enterPlayers();
        Dealer dealer = Dealer.create();
    
        int receiveCount = 2;
        for (int i = 0; i < receiveCount; i++) {
            dealer.deal(dealer);
    
            for (Player player : players) {
                dealer.deal(player);
            }
        }
        
        OutputView.printDealtBaseCards(dealer, players);
        
        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);
        
        printResult(dealer, players);
    }
    
    private List<Player> enterPlayers() {
        String[] playerNames = InputView.getPlayerName().split(",");
        
        return Arrays.stream(playerNames)
                     .map(Player::from)
                     .collect(Collectors.toList());
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
        while (dealer.shouldReceive()) {
            dealer.deal(dealer);
            OutputView.printDealerDrewMessage();
        }
    }
    
    private void printResult(Dealer dealer, List<Player> players) {
        OutputView.printSummaryStatistics(dealer, players);
        OutputView.printGameResult(dealer, players);
    }
}
