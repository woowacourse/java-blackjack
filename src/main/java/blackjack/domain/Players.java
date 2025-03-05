package blackjack.domain;

import java.util.List;

public class Players {

    private final Player dealer;
    private final List<Player> gamblers;

    public Players(List<Player> gamblers) {
        dealer = new Dealer();
        this.gamblers = gamblers;
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
}
