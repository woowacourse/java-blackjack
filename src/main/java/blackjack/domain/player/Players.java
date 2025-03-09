package blackjack.domain.player;

import blackjack.domain.GameResults;
import blackjack.domain.card.CardPack;

import java.util.HashSet;
import java.util.List;

public class Players {

    private static final int HIT_THRESHOLD = 16;

    private final Player dealer;
    private final List<Player> gamblers;

    public Players(final List<Player> gamblers, final CardPack cardPack) {
        dealer = new Dealer();

        validateHasDuplication(gamblers);
        this.gamblers = gamblers;
        initPlayers(cardPack);
    }

    public void dealAddCardForDealer(final CardPack cardPack) {
        dealer.pushDealCard(cardPack, 1);
    }

    public boolean isDealerHit() {
        return dealer.calculateCardNumbers() <= HIT_THRESHOLD;
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }

    private void validateHasDuplication(final List<Player> players) {
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

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamblers() {
        return gamblers;
    }
}
