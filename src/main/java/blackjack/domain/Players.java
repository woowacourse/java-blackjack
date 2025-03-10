package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {

    private final Dealer dealer;
    private final List<Gambler> gamblers;
    private static final int PLAYERS_INIT_CARD_COUNT = 2;

    protected Players() {
        dealer = new Dealer();
        gamblers = new ArrayList<>();
    }

    public void addGamblers(List<Gambler> gamblers) {
        validateHasDuplication(gamblers);
        this.gamblers.addAll(gamblers);
    }

    private void validateHasDuplication(List<Gambler> gamblers) {
        int size = new HashSet<>(gamblers).size();
        if (gamblers.size() != size) {
            throw new IllegalArgumentException("이름은 중복 될 수 없습니다.");
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public void initPlayers(CardPack cardPack) {
        dealer.pushDealCard(cardPack, PLAYERS_INIT_CARD_COUNT);
        gamblers.forEach(gambler ->
                gambler.pushDealCard(cardPack, PLAYERS_INIT_CARD_COUNT));
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }
}
