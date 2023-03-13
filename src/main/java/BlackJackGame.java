import card.Card;
import java.util.List;
import java.util.Optional;
import user.Dealer;
import user.Player;
import user.PlayerAction;

public class BlackJackGame {

    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void initializeHand() {
        for (Player player : players) {
            player.addCard(dealer.draw());
            player.addCard(dealer.draw());
        }
        dealer.addCard(dealer.draw());
        dealer.addCard(dealer.draw());
    }

    public boolean isNotFinish() {
        return players.stream().anyMatch(Player::isNotFinish);
    }

    public Player getCurrentPlayer() {
        Optional<Player> currentPlayer = players.stream().filter(Player::isNotFinish).findFirst();
        if (currentPlayer.isEmpty()) {
            throw new IllegalStateException("더 이상 게임을 진행할 플레이어가 없습니다");
        }
        return currentPlayer.get();
    }

    public void playerTurn(PlayerAction playerAction) {
        Card card = null;
        if (playerAction == PlayerAction.HIT) {
            card = dealer.draw();
        }
        Player currentPlayer = getCurrentPlayer();
        currentPlayer.addCard(card);
    }

    public void dealerTurn() {
        dealer.addCard(dealer.draw());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
