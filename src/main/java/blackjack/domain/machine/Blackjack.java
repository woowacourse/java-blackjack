package blackjack.domain.machine;

import blackjack.domain.participant.Participant;
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
    private final CardPickMachine cardPickMachine;

    public Blackjack(List<String> playerNames) {
        this.players = new Players(playerNames);
        this.dealer = new Dealer();
        this.cardPickMachine = new CardPickMachine();
    }

    public void dealInitialCards(NumberGenerator numberGenerator) {
        for (int i = 0; i < NUMBER_OF_INIT_CARD; ++i) {
            dealer.addCard(cardPickMachine.pickCard(numberGenerator));
            players.addCards(cardPickMachine, numberGenerator);
        }
    }

    public void dealAdditionalCardToPlayer(NumberGenerator numberGenerator, Player player) {
        players.addCard(cardPickMachine, player, numberGenerator);
    }

    public boolean dealAdditionalCardToDealer(NumberGenerator numberGenerator) {
        if (dealer.isHit()) {
            dealer.addCard(cardPickMachine.pickCard(numberGenerator));
            return true;
        }

        return false;
    }

    public boolean isDealOneMoreToPlayer() {
        return !players.isEnd();
    }

    public boolean isPlayerBust(Player player) {
        return players.isPlayerBust(player);
    }

    public boolean isBlackjack(Participant participant) {
        return participant.isBlackjack();
    }

    public Player getPlayer(Player player) {
        return players.findPlayer(player);
    }

    public Player getNextPlayer() {
        Player player = players.findNextPlayer();
        players.next();
        return player;
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
