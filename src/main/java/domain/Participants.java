package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = new Players(players);
    }

    public void initGame() {
        dealer.drawInitCardsDealer();
        drawPlayerInitCards(dealer, players);
    }

    public void drawPlayerInitCards(Dealer dealer, Players players) {
        players.getPlayers().forEach(player -> {
            addPlayerCards(player, dealer.drawInitCards());
        });
    }

    public void addPlayerCards(Player player, List<Card> cards) {
        cards.forEach(player::addCard);
    }

    public void drawCardForPlayers(Function<Player, Boolean> playerInput, Consumer<Player> completeAction) {
        players.getPlayers().forEach(player -> drawCardForPlayer(player, playerInput, completeAction));
    }

    private void drawCardForPlayer(Player player, Function<Player, Boolean> playerInput,
                                   Consumer<Player> completeAction) {
        boolean whetherDrawCard = false;
        while (player.canAdd() && (whetherDrawCard = playerInput.apply(player))) {
            player.addCard(dealer.drawCard());
            completeAction.accept(player);
        }
        if (!whetherDrawCard) {
            completeAction.accept(player);
        }
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }

    public void addCardToDealerIfPossible() {
        this.dealer.drawCardDealer();
    }

    public void calculateAllResults() {
        this.dealer.calculateAllResults(players.getPlayers());
    }

    public PlayerRevenues getPlayerRevenues() {
        return this.dealer.getPlayerRevenues();
    }
}
