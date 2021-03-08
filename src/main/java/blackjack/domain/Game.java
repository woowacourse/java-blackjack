package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static final int BLACKJACK_NUMBER = 21;
    private static final int SET_UP_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    private Game(Players players) {
        this.dealer = new Dealer();
        this.players = players;
        setUpTwoCards();
    }

    public static Game of(Players players) {
        return new Game(players);
    }

    public void setUpTwoCards() {
        for (int i = 0; i < SET_UP_CARD_COUNT; i++) {
            players.addCardToPlayer();
            dealer.addCard();
        }
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