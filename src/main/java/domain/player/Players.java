package domain.player;

import domain.card.Card;
import domain.gameresult.ScoreComparator;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(Dealer dealer, List<Gambler> gamblers) {
        List<Player> players = new ArrayList<>();
        players.add(0, dealer);
        players.addAll(gamblers);
        return new Players(players);
    }

    public void giveCardByName(String name, Card card) {
        Player player = findByName(name);
        player.draw(card);
    }

    public boolean shouldDealerGetCard() {
        return getDealer().getScore() <= Dealer.DEALER_MIN_SCORE;
    }

    public Map<Player, Bet> compareAll() {
        Map<Player, Bet> result = new LinkedHashMap<>();
        getParticipants().forEach(participant -> result.put(participant, ScoreComparator.compare(getDealer(), participant)));
        return result;
    }

    public Player findByName(String name) {
        return getAllPlayers().stream()
                .filter(player -> player.isNameEqualTo(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 참가자 이름입니다."));
    }

    private List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    public Player getDealer() {
        return players.get(0);
    }

    protected List<Player> getParticipants() {
        List<Player> players = new LinkedList<>(this.players);
        players.remove(getDealer());
        return players;
    }

    public List<String> getAllPlayerNames() {
        return getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
