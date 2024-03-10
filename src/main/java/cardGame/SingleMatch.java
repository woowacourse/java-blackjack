package cardGame;

import card.Card;
import dealer.Dealer;
import player.Player;

public class SingleMatch {

    private final Player player;
    private final Dealer dealer;

    public SingleMatch(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public void getMoreCard() {
        giveCard();
    }

    public boolean isCanPlayGamePlayer() {
        return player.isOverMaxCardScore();
    }

    private void giveCard() {
        Card pickedCard = dealer.giveCard();
        player.receiveCard(pickedCard);
    }

    public boolean isPlayerWins() {
        int dealerScore = dealer.getMaxGameScore();
        int playerScore = player.getMaxGameScore();

        return dealerScore < playerScore;
    }

    public Player getPlayer() {
        return player;
    }
}
