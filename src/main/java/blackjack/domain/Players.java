package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {

    private final Dealer dealer;
    private final List<Gambler> gamblers;
    private static final int PLAYERS_INIT_CARD_COUNT = 2;
    private static final int DEALER_HIT_MAX_VALUE = 16;

    public Players() {
        dealer = new Dealer();
        this.gamblers = new ArrayList<>();
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
                .orElseThrow(() ->
                        new IllegalArgumentException("서버에 오류가 발생했습니다."));
    }

    public boolean isDealerHit() {
        return dealer.calculateCardNumber() <= DEALER_HIT_MAX_VALUE;
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }
}
