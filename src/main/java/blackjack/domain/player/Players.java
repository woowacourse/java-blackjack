package blackjack.domain.player;

import blackjack.domain.card.CardPack;
import blackjack.domain.game.GameResults;

import java.util.HashSet;
import java.util.List;

public class Players {

    private final Dealer dealer;
    private final List<Gambler> gamblers;

    private static final int PLAYERS_INIT_CARD_COUNT = 2;

    public Players(List<Gambler> gamblers) {
        this.dealer = new Dealer();
        validateHasDuplication(gamblers);
        this.gamblers = gamblers;
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

    public void dealInitCardsToPlayers(CardPack cardPack) {
        dealer.pushDealCards(cardPack.getDealCards(PLAYERS_INIT_CARD_COUNT));
        gamblers.forEach(gambler ->
                gambler.pushDealCards(cardPack.getDealCards(PLAYERS_INIT_CARD_COUNT)));
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }
}
