package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.BettingMoney;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Name;
import blackjack.domain.entry.Player;
import blackjack.domain.state.State;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final Dealer dealer;
    private final Map<Player, State> players;

    public Players(Dealer dealer, Map<Player, State> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public boolean isFinished(Name name) {
        return players.keySet().stream()
            .filter(player -> player.equalsName(name))
            .anyMatch(player -> players.get(player).isFinished());
    }

    public List<Card> stayBy(Name name) {
        return players.keySet().stream()
            .filter(player -> player.equalsName(name))
            .findFirst()
            .map(this::stay)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 플에이어가 존재하지 않습니다."));
    }

    public List<Card> hitBy(Name name, Card card) {
        return players.keySet().stream()
            .filter(player -> player.equalsName(name))
            .findFirst()
            .map(player -> hit(card, player))
            .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 플에이어가 존재하지 않습니다."));
    }

    public Map<Name, HoldCards> stay() {
        return players.keySet().stream()
            .collect(Collectors.toMap(Player::getName, player -> players.get(player).getHoldCards()));
    }

    public Map<String, Double> getPlayerEarningMoney() {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Player player : players.keySet()) {
            BettingMoney bettingMoney = player.getBettingMoney();
            State state = players.get(player);
            result.put(player.getName().getValue(), state.profit(bettingMoney));
        }
        return result;
    }

    public List<Name> getNames() {
        return players.keySet().stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    private List<Card> stay(Player player) {
        State state = players.get(player).stay();
        players.put(player, state);
        return state.getHoldCards().getCards();
    }

    private List<Card> hit(Card card, Player player) {
        State state = players.get(player).draw(card);
        players.put(player, state);
        return state.getHoldCards().getCards();
    }

    public boolean isFinishedDealer() {
        return !dealer.canHit();
    }

    public void hitDealer(Card card) {
        dealer.addCard(card);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
