package blackjackgame.model.blackjackgame;

import java.util.List;
import java.util.Objects;
import blackjackgame.model.card.Card;
import blackjackgame.model.card.Cards;
import blackjackgame.model.participants.dealer.Dealer;
import blackjackgame.model.participants.player.Player;
import blackjackgame.model.participants.player.Players;

public class BlackjackGame {

    private static final int CARDS_SETTING_COUNTS = 2;

    private Dealer dealer;
    private Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void distributeCardsForSetting(Cards cards) {
        List<Card> cardsElement = cards.getCards();
        dealer = dealer.withAdditionalCards(cardsElement.subList(0, CARDS_SETTING_COUNTS));
        players = players.addCards(cardsElement.subList(CARDS_SETTING_COUNTS, cardsElement.size()));
    }

    public boolean isHitByPlayer(Player player, Card card) {
        if (player.isNotBust()) {
            players = players.hit(player, card);
            Player hittedPlayer = updatedPlayer(player);
            return !isPlayerBust(hittedPlayer);
        }
        return false;
    }

    private boolean isPlayerBust(Player player) {
        return player.isBust();
    }

    public boolean isHitByDealer(Card card) {
        if (dealer.isPossibleAddCard()) {
            dealer = dealer.withAdditionalCard(card);
            return true;
        }
        return false;
    }

    public Player updatedPlayer(Player player) {
        return players.getPlayers()
                .stream()
                .filter(updatedPlayer -> Objects.equals(
                        updatedPlayer.getName(), player.getName()))
                .findFirst()
                .orElse(player);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
