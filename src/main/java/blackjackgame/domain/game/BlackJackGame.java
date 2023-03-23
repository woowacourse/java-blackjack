package blackjackgame.domain.game;

import blackjackgame.domain.UserAction;
import blackjackgame.domain.user.DealerStatus;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.Profit;
import blackjackgame.domain.user.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Cards cards;

    public BlackJackGame(Players players, Dealer dealer, Cards cards) {
        this.players = players;
        this.dealer = dealer;
        this.cards = cards;
    }

    public void drawDefaultCard() {
        dealer.receiveCards(cards.drawCards(INITIAL_CARD_COUNT));
        players.receiveCards(cards, INITIAL_CARD_COUNT);
    }

    public Map<User, List<Card>> getSetUpResult() {
        Map<User, List<Card>> setUpResult = new LinkedHashMap<>();
        setUpResult.put(dealer, List.of(dealer.getFirstCard()));
        setUpResult.putAll(players.getHandsByPlayer());
        return setUpResult;
    }

    public void takePlayerAction(Player player, UserAction action) {
        if (action == UserAction.HIT) {
            hit(player);
        }
        if (action == UserAction.STAY) {
            player.finishDraw();
        }
    }

    public void hit(User user) {
        user.receiveCard(cards.drawCard());
    }

    public void hitUntilSatisfyingDealerMinimumScore() {
        while (DealerStatus.UNDER_MIN_SCORE.equals(dealer.getStatus())) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public int calculateDealerProfit() {
        judgeWinner();

        int playerProfitSum = players.calculateProfitSum();
        return -playerProfitSum;
    }

    private void judgeWinner() {
        Referee referee = new Referee(players, dealer);
        referee.judgeWinner();
    }

    public Map<Player, Profit> getBetResultOfPlayer() {
        return players.getPlayerProfits();
    }

    public boolean isDealerDrawExtraCount() {
        return dealer.getCards().size() > INITIAL_CARD_COUNT;
    }

    public int getDealerExtraDrawCount() {
        return dealer.getExtraDrawCount(INITIAL_CARD_COUNT);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
