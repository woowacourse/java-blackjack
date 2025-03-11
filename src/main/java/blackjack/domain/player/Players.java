package blackjack.domain.player;

import blackjack.domain.GameResults;
import blackjack.domain.card.CardPack;

import java.util.HashSet;
import java.util.List;

public class Players {

    private final Dealer dealer;
    private final List<Gambler> gamblers;

    public Players(final List<Gambler> gamblers, final CardPack cardPack) {
        dealer = new Dealer();

        validateHasDuplication(gamblers);
        this.gamblers = gamblers;
        initPlayers(cardPack);
    }

    public void dealAddCardForDealer(final CardPack cardPack) {
        dealer.pushDealCard(cardPack, 1);
    }

    public boolean isDealerHit() {
        return dealer.isHit();
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }

    private void validateHasDuplication(final List<Gambler> players) {
        int size = new HashSet<>(players).size();
        if (players.size() != size) {
            throw new IllegalArgumentException("이름은 중복 될 수 없습니다.");
        }
    }

    private void initPlayers(final CardPack cardPack) {
        dealer.pushDealCard(cardPack, 2);
        gamblers.forEach(gambler ->
                gambler.pushDealCard(cardPack, 2));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }
}
