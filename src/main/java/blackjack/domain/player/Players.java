package blackjack.domain.player;

import blackjack.domain.card.Deck;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Players {

    static final String DUPLICATE_NAMES_MESSAGE = "플레이어의 이름이 중복될 수 없습니다. 입력값:";
    private static final int NAME_COUNT_LOWER_BOUND = 1;
    private static final int NAME_COUNT_UPPER_BOUND = 6;
    static final String INVALID_NAME_COUNT =
            "플레이어는 최소 " + NAME_COUNT_LOWER_BOUND + "명 이상, 최대 " + NAME_COUNT_UPPER_BOUND + "명 이하여야 합니다. 입력값:";
    private static final String INVALID_DEALER_MESSAGE = "딜러가 존재하지 않습니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> inputNames) {
        final List<String> names = new ArrayList<>(inputNames);
        validateDuplicate(names);
        validateNameCount(names);
        return new Players(generate(names));
    }

    private static void validateDuplicate(final List<String> names) {
        if (isNameDuplicate(names)) {
            throw new IllegalArgumentException(DUPLICATE_NAMES_MESSAGE + names);
        }
    }

    private static boolean isNameDuplicate(final List<String> names) {
        return names.size() != new HashSet<>(names).size();
    }

    private static void validateNameCount(final List<String> names) {
        if (isValidNameCount(names)) {
            throw new IllegalArgumentException(INVALID_NAME_COUNT + names.size());
        }
    }

    private static boolean isValidNameCount(final List<String> names) {
        return names.size() < NAME_COUNT_LOWER_BOUND || NAME_COUNT_UPPER_BOUND < names.size();
    }

    private static List<Player> generate(final List<String> names) {
        final List<Player> players = new ArrayList<>();
        players.add(Dealer.create());
        for (String name : names) {
            players.add(new Gambler(name));
        }
        return players;
    }

    public void drawInitialCards(final Deck deck) {
        for (Player player : players) {
            player.drawInitialCard(deck);
        }
    }

    public void drawByDealer(final Deck deck) {
        final Dealer dealer = getDealer();
        while (dealer.isDrawable()) {
            dealer.draw(deck);
        }
    }

    public Map<Player, Result> compareHands() {
        final Dealer dealer = getDealer();
        return players.stream()
                .filter(player -> !player.isDealer())
                .collect(toMap(
                        Function.identity(),
                        player -> player.compareHandTo(dealer.getHand()),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Dealer getDealer() {
        return (Dealer) players.stream()
                .filter(Player::isDealer)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(INVALID_DEALER_MESSAGE));
    }

    public List<Player> getGamblers() {
        return players.stream()
                .filter(player -> !player.isDealer())
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
