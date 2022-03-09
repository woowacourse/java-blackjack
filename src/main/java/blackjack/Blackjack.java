package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Blackjack {
    private final  List<Player> players;
    private final Dealer dealer;

    public Blackjack(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        this.dealer = new Dealer();
    }

    public void distributeInitialCards(NumberGenerator numberGenerator) {
        for (int i = 0; i<2; ++i) {
            dealer.addCard(dealer.handOutCard(numberGenerator));
            players.forEach(player -> player
                    .addCard(dealer.handOutCard(numberGenerator)));
        }
    }

    public void distributeAdditionalCardDealer(NumberGenerator numberGenerator) {
        while(dealer.isHit()) {
            dealer.addCard(dealer.handOutCard(numberGenerator));
        }
    }

    public void distributeAdditionalCardPlayer(NumberGenerator numberGenerator, Player player, boolean flag) {
        if (flag) {
            player.addCard(dealer.handOutCard(numberGenerator));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Map<Player, Integer> result() {
        return ScoreBoard.calculateResult(dealer, players);
    }
}
