package cardGame;

import card.Card;
import card.Cards;
import dealer.Dealer;
import player.Player;

public class CardGame {

    private final Player player;
    private final Dealer dealer;

    public CardGame(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public Cards playRound() {
        giveCard();
        return player.getCards();
    }

    private void giveCard() {
        Card pickedCard = dealer.giveCard();
        player.receiveCard(pickedCard);
    }

    public boolean isDealerWins() {
        int dealerScore = dealer.getMaxGameScore();
        int playerScore = player.getMaxGameScore();

        return dealerScore >= playerScore;
    }

    public Player getPlayer() {
        return player;
    }
}
