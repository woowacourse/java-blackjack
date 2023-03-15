package domain.user;

import domain.card.Card;
import domain.game.Winning;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Users {

    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 4;

    private final Map<String, Player> players;
    private final Dealer dealer;

    private Users(final Map<String, Player> players, final Dealer dealer) {
        this.players = new LinkedHashMap<>(players);
        this.dealer = dealer;
    }

    public static Users from(final List<String> playerNames) {
        validate(playerNames);
        Dealer dealer = new Dealer();
        Map<String, Player> players = new LinkedHashMap<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName);
            players.put(playerName, player);
        }
        return new Users(players, dealer);
    }

    private static void validate(final List<String> names) {
        validateSize(names);
        validateDuplication(names);
    }

    private static void validateSize(final List<String> names) {
        if (names.size() < PLAYER_MIN_SIZE || names.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException(
                String.format("플레이어 수는 %d명 이상, %d명 이하여야 합니다.", PLAYER_MIN_SIZE, PLAYER_MAX_SIZE));
        }
    }

    private static void validateDuplication(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void hitCardToDealer(final Card card) {
        dealer.hit(card);
    }

    public void stayDealer() {
        dealer.stay();
    }

    public void bettingByName(final String name, final int bettingAmount) {
        Player player = findPlayerByName(name);
        player.betting(bettingAmount);
    }

    private Player findPlayerByName(final String name) {
        Player player = players.get(name);
        checkPlayerNotExist(player);
        return player;
    }

    private void checkPlayerNotExist(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("해당 이름의 플레이어가 존재하지 않습니다.");
        }
    }

    public boolean isDealerHittable() {
        return dealer.isHittable();
    }

    public Map<Player, Winning> matchPlayers() {
        Map<Player, Winning> matchResults = new LinkedHashMap<>();
        for (Player player : players.values()) {
            matchResults.put(player, player.match(dealer));
        }
        return Collections.unmodifiableMap(matchResults);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(players.values());
        users.add(dealer);
        return users;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players.values());
    }

    public List<String> getPlayerNames() {
        return players.values().stream()
            .map(Player::getName)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public int getDealerScore() {
        return dealer.getScore();
    }

    public Card getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public Map<String, List<Card>> getPlayerToCard() {
        Map<String, List<Card>> playerToCard = new LinkedHashMap<>();
        for (Player player : players.values()) {
            playerToCard.put(player.getName(), player.getCards());
        }
        return Collections.unmodifiableMap(playerToCard);
    }

    public Map<String, Integer> getPlayerToScore() {
        Map<String, Integer> playerToScore = new LinkedHashMap<>();
        for (Player player : players.values()) {
            playerToScore.put(player.getName(), player.getScore());
        }
        return Collections.unmodifiableMap(playerToScore);
    }
}
