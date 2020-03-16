package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 블랙잭 게임을 진행하는 클래스
 *
 * @author hotheadfactory, chws
 */

public class BlackjackGame {
    public static final int INITIAL_CARDS = 2;

    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, List<Player> players, Deck deck) {
        this.dealer = dealer;
        this.players = Collections.unmodifiableList(players);
        this.deck = deck;
    }

    public BlackjackGame(List<Player> players, Deck deck) {
        this(new Dealer(), players, deck);
    }

    public void distributeInitialCards() {
        dealer.receiveInitialCards(deck.draw(INITIAL_CARDS));
        players.forEach(t -> t.receiveInitialCards(deck.draw(INITIAL_CARDS)));
    }

    public boolean dealerHitsAdditionalCard() {
        if (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.draw());
            return true;
        }
        return false;
    }

    public Map<Player, Result> calculateAllResults() {
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        players.forEach(player -> totalResult.put(player, Result.of(dealer, player)));
        return totalResult;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void hitCard(Player player) {
        player.receiveCard(deck.draw());
    }
}
