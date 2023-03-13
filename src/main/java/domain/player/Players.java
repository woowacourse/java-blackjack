package domain.player;

import domain.card.Card;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private final Dealer dealer;
    private final List<Gambler> gamblers;

    public Players(Dealer dealer, List<Gambler> gamblers) {
        this.dealer = dealer;
        this.gamblers = gamblers;
    }

    public void giveCardByName(String name, Card card) {
        Player player = findByName(name);
        player.draw(card);
    }

    public boolean shouldDealerGetCard() {
        return dealer.getScore() <= Dealer.DEALER_MIN_SCORE;
    }

    public Map<Player, Bet> compareAll() {
        Map<Player, Bet> result = new LinkedHashMap<>();
        gamblers.forEach(participant -> result.put(participant, dealer.compareWith(participant)));
        return result;
    }

    public Player findByName(String name) {
        return getAllPlayers().stream()
                .filter(player -> player.isNameEqualTo(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 참가자 이름입니다."));
    }

    private List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(gamblers);
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    protected List<Gambler> getParticipants() {
        return gamblers;
    }

    public List<String> getAllPlayerNames() {
        return getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
