package blackjack.domain.game;

import blackjack.domain.card.CardsGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.PlayerGroup;
import blackjack.domain.participants.Profit;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final GameReferee referee;
    private final PlayerGroup playerGroup;

    public BlackjackGame(Deck deck, Dealer dealer, GameReferee referee, PlayerGroup playerGroup) {
        this.deck = deck;
        this.dealer = dealer;
        this.referee = referee;
        this.playerGroup = playerGroup;
    }

    public static BlackjackGame create(
        CardsGenerator cardsGenerator, GameReferee referee, PlayerGroup playerGroup) {
        return new BlackjackGame(
            Deck.create(cardsGenerator),
            Dealer.createEmptyHand(),
            referee,
            playerGroup);
    }

    public List<Player> getPlayers() {
        return playerGroup.players();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void initialDeal() {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            dealer.hit(deck.draw());
            playerGroup.deal(deck);
        }
    }

    public void hit(Player player) {
        player.hit(deck.draw());
    }

    public int playDealerTurn() {
        int hitCount = 0;
        while (dealer.canHit()) {
            dealer.hit(deck.draw());
            hitCount++;
        }
        return hitCount;
    }

    public Profit calculateProfit(Player player) {
        GameResult judge = referee.judge(dealer, player);
        return player.calculateProfit(judge);
    }
}
