package blackjack.player.domain;

import blackjack.card.domain.CardDrawer;
import blackjack.player.domain.report.GameReports;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Players {
    public static final int STARTING_CARD_SIZE = 2;
    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("게임참여자가 존재하지 않습니다.");
        }
    }

    public void drawStartingCard(CardDrawer cardDrawer) {
        checkState();
        for (int i = 0; i < STARTING_CARD_SIZE; i++) {
            players.forEach(player -> player.drawCard(cardDrawer));
        }
    }

    private void checkState() {
        boolean hasCard = players.stream()
                .allMatch(Player::hasCard);
        if (hasCard) {
            throw new IllegalStateException("게임 시작시에만 두장을 뽑을 수 있습니다.");
        }
    }

    public GameReports getReports() {
        Dealer dealer = (Dealer) findDealer();
        List<Player> gamblers = findGamblers();

        return gamblers.stream()
                .map(dealer::createReport)
                .collect(Collectors.collectingAndThen(toList(), GameReports::new));
    }

    public List<Player> findGamblers() {
        return players.stream()
                .filter(player -> player.getClass().equals(Gambler.class))
                .collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public Player findDealer() {
        return players.stream()
                .filter(player -> player.getClass().equals(Dealer.class))
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
