package domain.gamer;

import domain.cards.CardPack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gamers {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;
    private static final int DEALER_INDEX = 0;
    private static final String DEALER_NAME = "딜러";

    private final List<Player> players;
    private final Dealer dealer;

    public Gamers(List<Player> players, Dealer dealer) {
        validate(players);
        this.players = players;
        this.dealer = dealer;
    }

    private void validate(List<Player> players) {
        validateSize(players.size());
        validateNotDealerName(players);
        validateDuplicatedNames(players);
    }

    private void validateSize(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            String errorMessage = "[ERROR] 플레이어는 최소 " + MIN_SIZE + "명에서 최대 " + MAX_SIZE + "명까지 참여할 수 있습니다.";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateNotDealerName(List<Player> players) {
        boolean hasDealerName = players.stream()
                .anyMatch(player -> player.getPlayerName().equals(DEALER_NAME));
        if (hasDealerName) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 \"" + DEALER_NAME + "\"가 될 수 없습니다.");
        }
    }

    private void validateDuplicatedNames(List<Player> players) {
        long nonDuplicatedCount = players.stream()
                .map(Player::getPlayerName)
                .distinct()
                .count();
        if (nonDuplicatedCount != players.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 간 중복된 이름을 가질 수 없습니다.");
        }
    }

    public void shareInitCards(CardPack cardPack, int count) {
        for (Player gamer : getGamers()) {
            gamer.receiveCards(cardPack, count);
        }
    }

    public List<Player> getGamers() {
        List<Player> gamers = new ArrayList<>(List.copyOf(players));
        gamers.add(DEALER_INDEX, dealer);
        return gamers;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
