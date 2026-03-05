package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Game {

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private final Deck deck;

    public Game(List<String> names, Deck deck) {
        validatePlayers(names);

        players.addAll(names.stream()
                .map(Player::new)
                .toList());

        this.deck = deck;
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

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void startGame() {
        players.forEach(this::initializeCard);
        initializeCard(dealer);
    }

    private void initializeCard(Participant participant) {
        for (int i = 0; i < 2; i++) {
            distributeCard(participant);
        }
    }

    private void distributeCard(Participant participant) {
        Card firstCard = deck.drawCard();
        participant.addCard(firstCard);
    }

    public void playerHit(Participant participant, boolean isHit) {
        if (participant.checkScoreUnderCriterion() && isHit) {
            distributeCard(participant);
        }
    }
}
