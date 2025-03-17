package Blackjack.domain.participant;

import Blackjack.domain.Bet;
import Blackjack.domain.card.Card;
import Blackjack.domain.card.Cards;
import Blackjack.exception.ErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Players {
    private final Map<String, Player> players;

    private Players(final Map<String, Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names) {
        validateDistinct(names);
        Map<String, Player> players = new HashMap<>(names.size());
        for (String name : names) {
            players.put(name, new Player(name, Bet.valueOf(0)));
        }
        return new Players(players);
    }

    public void initializeBet(Function<String, Integer> betFunction) {
        for (Player player : players.values()) {
            int bet = betFunction.apply(player.getName());
            player.increaseBet(bet);
        }
    }

    public int size() {
        return players.size();
    }

    public boolean canHit(final String name) {
        return players.get(name).ableToAddCard();
    }

    public void addCardTo(final String name, final Card card) {
        players.get(name).addCard(card);
    }

    public void addCardToAll(final List<Card> cards) {
        validateCardSize(cards);
        List<String> names = getNames();
        for (int i = 0; i < size(); i++) {
            players.get(names.get(i)).addCard(cards.get(i));
        }
    }

    public Map<String, Integer> calculateProfits(final Dealer dealer) {
        Map<String, Integer> profits = new HashMap<>();
        for (Player player : players.values()) {
            profits.put(player.name, player.calculateProfit(dealer));
        }
        return profits;
    }

    public List<String> getNames() {
        return new ArrayList<>(players.keySet()).stream().toList();
    }

    public Cards getCardsOf(final String name) {
        return new Cards(players.get(name).getCards());
    }

    private static void validateDistinct(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new ErrorException("참여자 이름은 중복될 수 없습니다.");
        }
    }

    private void validateCardSize(final List<Card> cards) {
        if (size() != cards.size()) {
            throw new ErrorException(String.format("총 %d장의 카드가 필요합니다.", size()));
        }
    }
}
