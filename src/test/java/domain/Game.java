package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private final Set<Player> players = new HashSet<>();
    private final Dealer dealer = new Dealer();
    private final Deck deck = new Deck();

    public Game(List<String> names) {
        validatePlayers(names);

        players.addAll(names.stream()
                .map(Player::new)
                .collect(Collectors.toSet()));
    }

    private void validatePlayers(List<String> names) {
        validateDuplicatedName(names);
        validatePlayerNumber(names);
    }

    private void validatePlayerNumber(List<String> names) {
        if (names.isEmpty() || names.size() > 7) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 1명 이상 7명 이하여야 합니다.");
        }
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> distinctNames = Set.copyOf(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public Set<Player> getPlayers() {
        return Set.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void startGame() {
        players.forEach(this::initializeCard);
        initializeCard();
    }

    private void initializeCard(Player player) {
        for (int i = 0; i < 2; i++) {
            distributeCard(player);
        }
    }

    private void initializeCard() {
        for (int i = 0; i < 2; i++) {
            distributeCard();
        }
    }

    private void distributeCard(Player player) {
        Card card = deck.drawCard();
        player.addCard(card);
    }

    private void distributeCard() {
        Card firstCard = deck.drawCard();
        dealer.addCard(firstCard);
    }
}
