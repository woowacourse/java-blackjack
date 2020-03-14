package domain.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Cards;
import domain.card.Deck;
import domain.result.ResultType;
import domain.rule.Rules;
import view.OutputView;

public class Players {

    private List<Player> players;

    private Players(String input) {
        List<String> names = Arrays.asList(input.split(",", -1));
        this.players = names.stream()
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());

    }

    public static Players of(String names) {
        return new Players(names);
    }

    public void draw(Deck deck) {
        players.forEach(
                player -> player.draw(deck.dealOut())
        );
    }

    public String getAllNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(Cards.COMMA));
    }

    public String getAllFirstDrawResults() {
        return players.stream()
                .map(Player::getDrawResult)
                .collect(Collectors.joining(OutputView.NEWLINE));
    }

    public String getAllTotalDrawResults() {
        return players.stream()
                .map(Player::getTotalDrawResult)
                .collect(Collectors.joining(OutputView.NEWLINE));
    }

    public Map<Player, ResultType> decideWinner(Dealer dealer, Rules rules) {
        Map<Player, ResultType> resultOfPlayers = new LinkedHashMap<>();

        players.forEach(player -> resultOfPlayers.put(player, rules.decideResultType(player, dealer)));

        return Collections.unmodifiableMap(resultOfPlayers);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
