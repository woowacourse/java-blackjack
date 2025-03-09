package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {

    public static final int HIT_THRESHOLD = 16;

    private final Player dealer;
    private final List<Player> gamblers;

    public Players() {
        dealer = new Dealer();
        this.gamblers = new ArrayList<>();
    }

    public void addGamblers(List<Player> gamblers) {
        validateHasDuplication(gamblers);
        this.gamblers.addAll(gamblers);
    }

    public void initPlayers(CardPack cardPack) {
        dealer.pushDealCard(cardPack, 2);
        gamblers.forEach(gambler ->
                gambler.pushDealCard(cardPack, 2));
    }

    public void dealAddCardForDealer(CardPack cardPack) {
        dealer.pushDealCard(cardPack, 1);
    }

    public boolean isDealerHit() {
        return dealer.calculateCardNumber() <= HIT_THRESHOLD;
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamblers() {
        return gamblers;
    }

    private void validateHasDuplication(List<Player> gamblers) {
        int size = new HashSet<>(gamblers).size();
        if (gamblers.size() != size) {
            throw new IllegalArgumentException("이름은 중복 될 수 없습니다.");
        }
    }
}
