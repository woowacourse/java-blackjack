package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {

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

    private void validateHasDuplication(List<Player> gamblers) {
        int size = new HashSet<>(gamblers).size();
        if (gamblers.size() != size) {
            throw new IllegalArgumentException("이름은 중복 될 수 없습니다.");
        }
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
        return dealer.calculateCardNumber() <= 16;
    }

    public GameResults getGameResult() {
        return new GameResults(dealer, gamblers);
    }
}
