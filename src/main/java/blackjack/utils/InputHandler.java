package blackjack.utils;

import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.User;
import blackjack.domain.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InputHandler {

    public static List<String> parseName(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("이름은 최소 1개 이상이어야 합니다");
        }
        List<String> names = Arrays.asList(input.trim()
                .replace(" ", "")
                .split(","));

        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다");
        }
        return names;
    }

    public static void hitMoreCard(Users users, Deck deck) {
        List<User> gameUsers = users.getUsers();
        gameUsers.stream()
                .filter(user -> user instanceof Player)
                .forEach(user -> askForHit(deck, user));
    }

    private static void askForHit(Deck deck, User user) {
        while (InputView.askForHit(user.getName())) {
            user.receiveCard(deck.drawCard());
            OutputView.printCardStatus(user.showCardInfo());
            if (user.isBusted()) {
                OutputView.printBusted(user.getName());
                break;
            }
        }
    }
}
