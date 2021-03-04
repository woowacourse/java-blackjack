package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameResult;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static final int BLACKJACK_NUMBER = 21;

    private final Dealer dealer;
    private final List<Player> players;

    private Game(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public static Game of(List<String> playerNames) {
        return new Game(playerNames.stream()
                                   .map(Player::new)
                                   .collect(Collectors.toList()));
    }

    public void setUpTwoCards() {
        addTwoCard(dealer);
        for (Player player : players) {
            addTwoCard(player);
        }
    }

    private void addTwoCard(Participant participant) {
        participant.addCard(Deck.draw());
        participant.addCard(Deck.draw());
    }

    public void giveCard(Participant participant) {
        participant.addCard(Deck.draw());
    }

    public void playDealerTurn() {
        while (!dealer.isStay()) {
            giveCard(dealer);
        }
    }

    public GameResult getDealerResult() {
        GameResult totalPlayerResult = new GameResult();
        for (Player player : players) {
            totalPlayerResult.plus(player.getGameResult());
        }
        return totalPlayerResult.reverse();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}