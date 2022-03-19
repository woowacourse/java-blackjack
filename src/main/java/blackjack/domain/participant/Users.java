package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Users {

    private final List<User> users;

    public Users(List<String> users) {
        this.users = users.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    public List<String> getUserNames() {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public void betting(String name, int money) {
        findUserByName(name).betting(money);
    }

    public void drawCard(String name, Deck deck) {
        findUserByName(name).drawCard(deck);
    }

    public boolean isBust(String name) {
        return findUserByName(name).isBust();
    }

    public List<Card> holdingCards(String name) {
        return findUserByName(name).getHoldingCards();
    }

    public int score(String name) {
        return findUserByName(name).score();
    }

    public double profit(String name, int dealerScore) {
        return findUserByName(name).calculateProfit(dealerScore);
    }

    public void changeToStand(String name) {
        findUserByName(name).changeToStand();
    }

    private User findUserByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
