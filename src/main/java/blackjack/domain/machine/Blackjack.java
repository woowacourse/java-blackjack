package blackjack.domain.machine;

import java.util.List;

import blackjack.domain.dto.ResultDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.strategy.NumberGenerator;

public class Blackjack {
    private static final int NUMBER_OF_INIT_CARD = 2;

    private final Players players;
    private final Dealer dealer;

    public Blackjack(List<String> playerNames) {
        this.players = new Players(playerNames);
        this.dealer = new Dealer();
    }

    public void dealInitialCards(NumberGenerator numberGenerator) {
        for (int i = 0; i < NUMBER_OF_INIT_CARD; ++i) {
            dealer.addCard(dealer.handOutCard(numberGenerator));
            players.addCards(dealer, numberGenerator);
        }
    }

    public boolean dealAdditionalCardToDealer(NumberGenerator numberGenerator) {
        if (dealer.isHit()) {
            dealer.addCard(dealer.handOutCard(numberGenerator));
            return true;
        }

        return false;
    }

    public boolean isDealOneMore() {
        return !players.isEnd();
    }

    public boolean isPlayerBurst(Player player) {
        return players.isPlayerBurst(player);
    }

    public Player getNextPlayer() {
        Player player = players.findNextPlayer();
        players.next();
        return player;
    }

    public Player findPlayer(Player player) {
        return players.findPlayer(player);
    }

    public void dealAdditionalCardToPlayer(NumberGenerator numberGenerator, Player player) {
        players.addCard(dealer, player, numberGenerator);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public ResultDto result() {
        return Records.of(dealer, players.getPlayers());
    }
}
