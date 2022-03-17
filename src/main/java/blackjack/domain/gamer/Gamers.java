package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.*;

import static blackjack.domain.gamer.Gamer.INIT_DISTRIBUTION_COUNT;

public class Gamers {
    private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";

    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();

    public Gamers(List<String> names, Deck deck) {
        dealer = new Dealer(List.of(deck.draw(), deck.draw()));
        createPlayers(names, deck);
    }

    private void createPlayers(List<String> names, Deck deck) {
        validateDuplicationNames(names);
        for (String name : names) {
            List<Card> cards = List.of(deck.draw(), deck.draw());
            players.add(new Player(name, cards));
        }
    }

    private void validateDuplicationNames(List<String> names) {
        Set<String> duplicationCheck = new HashSet<>(names);
        if (duplicationCheck.size() != names.size()) {
            throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
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

    public boolean canDrawToPlayer(Player player) {
        return !player.canDraw();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int getDealerCardSize() {
        return dealer.getCardsSize();
    }
}
