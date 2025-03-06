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

    public void dealAddCard(CardPack cardPack, Player player) {
        Player gambler = gamblers.stream()
                .filter(current -> current.equals(player))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        gambler.pushDealCard(cardPack, 1);
    }
}
