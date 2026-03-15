package domain;

import domain.member.Dealer;
import domain.member.Member;
import domain.member.Name;
import domain.member.Player;
import domain.member.Players;
import domain.card.Deck;
import domain.member.Settler;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;
    private final Settler settler;

    public BlackjackGame(Players members) {
        this.deck = new Deck();
        this.dealer = new Dealer();
        this.players = members;
        this.settler = new Settler();
        this.deck.init();
    }

    public void initGame() {
        dealer.initDraw(deck);
        players.initDraw(deck);
    }

    public void drawPlayer(Player player) {
        players.draw(player, deck.draw());
    }

    public void drawDealer() {
        dealer.draw(deck.draw());
    }

    public boolean canDealerDraw() {
        return dealer.canDealerDraw();
    }

    public void applyBlackjackBonus() {
        getPlayers().stream()
                .filter(Player::hasBlackjack)
                .forEach(Player::applyBlackjackBonus);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players.getPlayers());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Map<Player, Integer> getPlayerProfits() {
        return settler.getPlayerProfits(dealer, getPlayers());
    }

    public int getDealerProfit() {
        return settler.getDealerProfit(dealer, getPlayers());
    }
}
