package model.user;

import model.card.Deck;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    private Participants(final List<Player> playersName, final Dealer dealer) {
        this.players = playersName;
        this.dealer = dealer;
    }

    public static Participants of(final List<String> playersName, final Dealer dealer) {
        return new Participants(createPlayers(playersName), dealer);
    }

    private static List<Player> createPlayers(List<String> playersName) {
        return playersName.stream()
                .map(Player::new)
                .collect(toList());
    }

    public Map<Result, Long> findDealerFinalResult() {
        final int dealerTotalValue = dealer.calculateTotalValue();
        return players.stream()
                .collect(groupingBy(player -> Result.judge(player.calculateTotalValue(), dealerTotalValue), counting()));
    }

    public Map<Player, Result> findPlayerFinalResult() {
        final int dealerTotalValue = dealer.calculateTotalValue();
        return players.stream()
                .collect(toMap(Function.identity(), player -> player.judgeResult(dealerTotalValue)));
    }

    public void receiveInitialCards(final Deck deck) {
        players.forEach(player -> player.receiveInitialCards(deck));
        dealer.receiveInitialCards(deck);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
