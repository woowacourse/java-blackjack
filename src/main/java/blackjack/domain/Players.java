package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final Player dealer;
    private final List<Player> gamblers;

    public Players() {
        dealer = new Dealer();
        this.gamblers = new ArrayList<>();
    }

    public void addGamblers(List<Player> gamblers) {
        this.gamblers.addAll(gamblers);
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamblers() {
        return gamblers;
    }

    public void initPlayers(CardPack cardPack) {
        dealer.pushDealCard(cardPack, 2);
        gamblers.forEach(gambler ->
                gambler.pushDealCard(cardPack, 2));
    }

    public void dealAddCardForDealer(CardPack cardPack) {
        dealAddCard(cardPack, dealer);
    }

    public void dealAddCard(CardPack cardPack, Player addPlayer) {
        Player player = getMatchPlayer(addPlayer);
        player.pushDealCard(cardPack, 1);
    }

    public boolean isPlayerBust(final Player player) {
        return player.isPlayerBust();
    }

    private Player getMatchPlayer(Player player) {
        if (player.equals(dealer)) {
            return dealer;
        }
        return gamblers.stream()
                .filter(current -> current.equals(player))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isDealerHit() {
        return dealer.calculateCardNumber() <= 16;
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }
}
