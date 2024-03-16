package blackjack.domain;

import blackjack.domain.bet.BetLeverage;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hands;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerNames;
import blackjack.domain.user.Players;
import blackjack.domain.user.UserName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    public static final int START_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(PlayerNames playerNames) {
        this.players = new Players(playerNames);
        this.dealer = new Dealer();
        this.deck = Deck.create();
    }

    public void giveStartCards() {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            giveCardToPlayers();
            dealer.draw(deck.pick());
        }
    }

    private void giveCardToPlayers() {
        for (Player player : players.getPlayer()) {
            player.draw(deck.pick());
        }
    }

    public void playerDraw(UserName name) {
        players.find(name).draw(deck.pick());
    }

    public int runDealerTurn() {
        if (players.isAllBust()) {
            return 0;
        }

        int count = 0;

        while (dealer.canHit()) {
            dealer.draw(deck.pick());
            count++;
        }
        return count;
    }

    public boolean isPlayerCanHit(UserName name) {
        return players.find(name).canHit();
    }

    public Hands playerHands(UserName name) {
        return players.find(name).getHands();
    }

    public Map<UserName, BetLeverage> getPlayersBetLeverage() {
        Map<UserName, BetLeverage> playersBetLeverage = new LinkedHashMap<>();
        Hands dealerHands = dealer.getHands();

        for (Player player : players.getPlayer()) {
            Hands playerHands = player.getHands();
            BetLeverage betLeverage = BetLeverage.of(playerHands, dealerHands);

            playersBetLeverage.put(player.getUserName(), betLeverage);
        }

        return playersBetLeverage;
    }

    public Map<UserName, Hands> getPlayersHands() {
        return players.getHands();
    }

    public Hands getDealerOpenedHands() {
        return dealer.getOpenedHands();
    }

    public Hands getDealerHands() {
        return dealer.getHands();
    }

    public List<UserName> getPlayersName() {
        return players.getPlayerNames();
    }
}
