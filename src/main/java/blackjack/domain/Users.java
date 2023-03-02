package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {

    private static final String NOT_CONTAIN_DEALER = "Users에 Dealer 객체가 없습니다.";
    private static final int DEALER_DRAW_LIMIT = 16;
    private final List<User> users;

    public Users(List<String> playerNames, Deck deck) {
        List<User> users = playerNames.stream()
                .map(name -> new Player(name, initCardGroup(deck)))
                .collect(Collectors.toList());
        users.add(new Dealer(initCardGroup(deck)));
        this.users = List.copyOf(users);
    }

    private CardGroup initCardGroup(Deck deck) {
        return new CardGroup(deck.draw(), deck.draw());
    }

    public Map<String, List<Card>> getStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getStatus));
    }

    public Map<String, List<Card>> getInitialStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getInitialStatus));
    }

    public boolean isDealerOverDrawLimit() {
        return BlackJackRule.getScore(getDealer()) > DEALER_DRAW_LIMIT;
    }

    private User getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(NOT_CONTAIN_DEALER));
    }

    public void drawDealer(final Deck deck) {
        getDealer().drawCard(deck);
    }
}
