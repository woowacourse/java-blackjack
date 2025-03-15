package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.game.GameProfits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {

    private final Dealer dealer;
    private final List<Gambler> gamblers;

    private static final int PLAYERS_INIT_CARD_COUNT = 2;

    public Players(List<Gambler> gamblers) {
        this.dealer = new Dealer();
        validateHasDuplication(gamblers);
        this.gamblers = new ArrayList<>(gamblers);
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
        return Collections.unmodifiableList(gamblers);
    }

    public void dealInitCardsToPlayers(CardPack cardPack) {
        List<Card> dealCards = cardPack.getDealCards(PLAYERS_INIT_CARD_COUNT);
        dealer.pushDealCards(dealCards);
        gamblers.forEach(gambler ->
                gambler.pushDealCards(dealCards));
    }

    public GameProfits getGameResult() {
        return new GameProfits(dealer, gamblers);
    }
}
