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
    
    private final Dealer dealer;
    private final List<Player> players;
    
    private BlackJackController(Dealer dealer, List<Player> players) {
        this.players = players;
        this.dealer = dealer;
    }
    
    public static BlackJackController create() {
        List<Player> players = enterPlayers();
        Dealer dealer = Dealer.create();
        return new BlackJackController(dealer, players);
    }
    
    private static List<Player> enterPlayers() {
        String[] playerNames = InputView.getPlayerName()
                                        .split(",");
        
        return Arrays.stream(playerNames)
                     .map(Player::from)
                     .collect(Collectors.toList());
    }
    
    public void run() {
        dealBaseCards();
        dealAdditionalCards();
        printResult();
    }
    
    private void dealBaseCards() {
        for (int i = 0; i < BASE_CARD_COUNT; i++) {
            dealer.draw();
            players.forEach(dealer::deal);
        }
        OutputView.printDealtBaseCards(dealer, players);
    }
    
    private void dealAdditionalCards() {
        dealToPlayers();
        dealToMyself();
    }
    
    private void dealToPlayers() {
        players.forEach(this::dealToPlayer);
    }
    
    private void dealToPlayer(Player player) {
        while (player.canReceive() && InputView.wantsReceive(player)) {
            dealer.deal(player);
            OutputView.printNameAndHand(player);
        }
    }
    
    private void dealToMyself() {
        while (dealer.canReceive()) {
            dealer.draw();
            OutputView.printDealerDrewMessage();
        }
    }
    
    private void printResult() {
        OutputView.printSummaryStatistics(dealer, players);
        OutputView.printGameResult(dealer, players);
    }
}
