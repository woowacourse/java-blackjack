package blackjack.domain.gamer;

import blackjack.domain.card.Deck;

import java.util.*;

import static blackjack.domain.gamer.Gamer.INIT_DISTRIBUTION_COUNT;

public class Gamers {
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

    private void validateDuplicationNames(List<String> names) {
        Set<String> duplicationCheck = new HashSet<>(names);
        if (duplicationCheck.size() != names.size()) {
            throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
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

    public void distributeAdditionalToDealer(Deck deck) {
        while (dealer.canDraw()) {
            distributeCard(dealer, deck);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void distributeCardToPlayer(String name, Deck deck) {
        findPlayerByName(name).addCard(deck.draw());
    }

    public boolean isBurst(String name) {
        Player player = findPlayerByName(name);
        return !player.canDraw();
    }

    public Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER_ERROR));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int getDealerCardSize() {
        return dealer.getCardsSize();
    }
}
