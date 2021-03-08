package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static final int BLACKJACK_NUMBER = 21;

    private final Dealer dealer;
    private final List<Player> players;

    private Game(List<Player> players) {
        this.dealer = new Dealer();
        this.players = new ArrayList<>(players);
    }

    public static Game of(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("최소 1명 이상은 게임에 참여해야 합니다.");
        }
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

    public int playDealerTurn() {
        int cnt = 0;
        while (!dealer.isStay()) {
            giveCard(dealer);
            cnt++;
        }
        return cnt;
    }

    public void fightPlayers() {
        for (Player player : players) {
            player.fight(dealer);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}