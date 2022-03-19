package blackjack.domain.player;

import blackjack.domain.BettingBox;
import blackjack.domain.BettingMoney;
import blackjack.domain.card.Deck;
import blackjack.domain.strategy.BetInputStrategy;
import blackjack.domain.strategy.HitStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players {

    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "참가자 이름은 중복될 수 없습니다.";
    private static final String PLAYER_COUNT_OVER_ERROR_MESSAGE = "참가자는 딜러 포함 8명 까지만 가능합니다.";
    private static final int MAX_SIZE = 8;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players createPlayers(List<String> names, Deck deck, HitStrategy hitStrategy) {
        validate(names);
        List<Player> allPlayers = new ArrayList<>(toPlayers(names, deck, hitStrategy));
        allPlayers.add(new Dealer(deck));
        return new Players(allPlayers);
    }

    private static void validate(List<String> names) {
        Set<String> nameSet = new HashSet<>(names);
        if (names.size() != nameSet.size()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATE_ERROR_MESSAGE);
        }
        if (names.size() >= MAX_SIZE) {
            throw new IllegalArgumentException(PLAYER_COUNT_OVER_ERROR_MESSAGE);
        }
    }

    private static List<Player> toPlayers(List<String> names,Deck deck, HitStrategy hitStrategy) {
        return names.stream()
                .map(name -> new Guest(name, deck, hitStrategy))
                .collect(Collectors.toList());
    }

    public void deal(Deck deck, int dealDrawCount) {
        for (Player player : players) {
            dealCount(deck, dealDrawCount, player);
        }
    }

    private void dealCount(Deck deck, int initDrawCount, Player player) {
        for (int i = 0; i < initDrawCount; i++) {
            player.hit(deck.pick());
        }
    }

    public void playersHit(Deck deck, Consumer<Player> hitCallback) {
        for (Player player : players) {
            hitOrStand(player, deck, hitCallback);
        }
    }

    private void hitOrStand(Player player, Deck deck, Consumer<Player> afterHitCallback) {
        while (player.isHittable()) {
            player.hit(deck.pick());
            afterHitCallback.accept(player);
        }
    }

    public BettingBox bet(BetInputStrategy betInputStrategy) {
        Map<Player, BettingMoney> bettingBoxValuse = new LinkedHashMap<>();
        List<Player> guests = getGuests();
        for (Player guest : guests) {
            bettingBoxValuse.put(guest, betInputStrategy.inputBettingMoney(guest));
        }
        return new BettingBox(bettingBoxValuse);
    }

    public Player findDealer() {
        return players.stream()
                .filter(this::isDealer)
                .findAny()
                .orElseThrow(() -> new RuntimeException("딜러가 없음..."));
    }

    public List<Player> getGuests() {
        return players.stream()
                .filter(player -> !isDealer(player))
                .collect(Collectors.toList());
    }

    private boolean isDealer(Player player) {
        return player instanceof Dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void judge() {
        List<Player> guests = getGuests();
        Player dealer = findDealer();
        for (Player guest : guests) {
            guest.judge(dealer);
        }
    }

    public Map<Player, Integer> getPrizeResult(BettingBox bettingBox) {
        Map<Player, Integer> result = new LinkedHashMap<>();
        int totalPrizeMoney = 0;
        for (Player guest : getGuests()) {
            int prizeMoney = (int) (guest.getPrizeRate() * bettingBox.findBettingMoney(guest).getAmount());
            result.put(guest, prizeMoney);
            totalPrizeMoney += prizeMoney;
        }
        result.put(findDealer(), totalPrizeMoney * -1);
        return result;
    }
}
