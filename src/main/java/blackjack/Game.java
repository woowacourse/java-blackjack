package blackjack;

import blackjack.card.Deck;
import blackjack.participant.Dealer;
import blackjack.participant.Participant;
import blackjack.participant.Player;

import java.util.Arrays;
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

    public List<Integer> getDealerResult() {
        int winCount = 0;
        int drawCount = 0;
        int loseCount = 0;
        for (Player player : players) {
            winCount += player.getLoseCount();
            drawCount += player.getDrawCount();
            loseCount += player.getWinCount();
        }

        return Arrays.asList(winCount, drawCount, loseCount);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}