package blackjack.domain.player;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;

import java.util.HashSet;
import java.util.List;

public class Players {

    private final Dealer dealer;
    private final List<Gambler> gamblers;

    public Players(final Dealer dealer, final List<Gambler> gamblers) {
        validateHasDuplication(gamblers);
        this.dealer = dealer;
        this.gamblers = gamblers;
    }

    public void dealAddCardForDealer(final List<Card> cards) {
        dealer.addCards(cards);
    }

    public void addCardForGambler(final Gambler gambler, final List<Card> cards) {
        gambler.addCards(cards);
    }

    public boolean isDealerHit() {
        return dealer.isHit();
    }

    public GameResult createGameResult() {
        GameResult gameResult = new GameResult();
        for (Gambler gambler : gamblers) {
            gameResult.processResult(dealer, gambler);
        }
        return gameResult;
    }

    private void validateHasDuplication(final List<Gambler> gamblers) {
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
}
