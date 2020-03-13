package blackjack.player.domain;

import blackjack.card.domain.CardDeck;
import blackjack.player.domain.report.GameReports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Players {
    public static final int STARTING_CARD_SIZE = 2;
    private final List<Player> players;

    public Players(List<Player> players) {
        if (players == null) {
            players = new ArrayList<>();
        }
        this.players = players;
    }

    public void drawStartingCard(CardDeck cardDeck) {
        checkState();
        for (int i = 0; i < STARTING_CARD_SIZE; i++) {
            players.forEach(player -> player.addCard(cardDeck.draw()));
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
        Player dealer = findDealer();
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
