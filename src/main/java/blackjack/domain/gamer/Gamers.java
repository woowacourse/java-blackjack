package blackjack.domain.gamer;

import blackjack.domain.card.Deck;

import java.util.*;

public class Gamers {
    public static final int MAX_CARD_VALUE = 21;
    private static final int INIT_DISTRIBUTION_COUNT = 2;
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;
    private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";
    private static final String NOT_EXIST_PLAYER_ERROR = "플레이어가 존재하지 않습니다.";

    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();

    public Gamers(List<String> names) {
        dealer = new Dealer();
        createPlayers(names);
    }

    private void createPlayers(List<String> names) {
        validateDuplicationNames(names);
        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public void distributeFirstCards(Deck deck) {
        for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
            distributeCard(dealer, deck);
            players.forEach(player -> distributeCard(player, deck));
        }
    }

    private void distributeCard(Gamer gamer, Deck deck) {
        gamer.addCard(deck.draw());
    }

    public int distributeAdditionalToDealer(Deck deck) {
        int count = 0;
        while (!dealer.isOverThan(ADDITIONAL_DISTRIBUTE_STANDARD)) {
            distributeCard(dealer, deck);
            count++;
        }
        return count;
    }


    public Dealer findDealer() {
        return dealer;
    }

    public int getDealerCardSize() {
        return dealer.getCardsSize();
    }

    public void distributeCardToPlayer(String name, Deck deck) {
        findPlayerByName(name).addCard(deck.draw());
    }

    public boolean isBurst(String name) {
        Player player = findPlayerByName(name);
        return player.getCardsNumberSum() > MAX_CARD_VALUE;
    }

    public Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER_ERROR));
    }

    public List<Player> findPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validateDuplicationNames(List<String> names) {
        Set<String> duplicationCheck = new HashSet<>(names);
        if (duplicationCheck.size() != names.size()) {
            throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
        }
    }
}
