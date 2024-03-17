package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameResult;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.Map;

public class BlackjackGame {

    private final GameAccount gameAccount;
    private final Deck deck;

    public BlackjackGame() {
        this.gameAccount = new GameAccount();
        this.deck = new Deck();
    }

    public void distributeInitialHand(Players players, Dealer dealer) {
        deck.shuffle();
        setUpInitialHands(players, dealer);
    }


    private void setUpInitialHands(Players players, Dealer dealer) {
        players.initAllPlayersCard(deck);
        dealer.initCard(deck);
    }

    public void betPlayerMoney(Map<Player, Money> playersBetMoney) {
        for (Player player : playersBetMoney.keySet()) {
            gameAccount.betMoney(player, playersBetMoney.get(player));
        }
    }

    public void addCardToPlayer(Player player) {
        player.addCard(deck.draw());
    }

    public void distributeCardToDealer(Dealer dealer) {
        while (dealer.canReceiveCard()) {
            dealer.addCard(deck.draw());
        }
    }

    public Money calculateDealerIncome(Players players, Dealer dealer) {
        applyResultToBetMoney(players, dealer);
        return gameAccount.calculateDealerIncome();
    }

    private void applyResultToBetMoney(Players players, Dealer dealer) {
        Map<Player, GameResult> playerGameResults = players.collectPlayerGameResults(dealer);
        gameAccount.applyGameResults(playerGameResults);
    }

    public Map<Player, Money> getStore() {
        return gameAccount.getStore();
    }
}
