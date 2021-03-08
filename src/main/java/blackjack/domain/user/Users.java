package blackjack.domain.user;

import blackjack.domain.MatchRule;
import blackjack.domain.ResultType;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users = new ArrayList<>();

    public Users(Dealer dealer, List<String> players) {
        this.users.add(dealer);
        this.users.addAll(players.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    public List<User> gerUsers() {
        return Collections.unmodifiableList(this.users);
    }

    public Dealer getDealer() {
        return (Dealer) this.users.stream()
                .filter(user -> user instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("딜러가 존재하지 않습니다."));
    }

    public Map<User, ResultType> generateResultsMapAgainstDealer() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .collect(Collectors.toMap(
                        player -> player,
                        player -> MatchRule.getMatchResult(player.getCards(), this.getDealer().getCards())
                ));
    }
}
