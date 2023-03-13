package blackjack.domain.game;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJack {

    private final Users users;
    private final Dealer dealer;

    public BlackJack(List<Name> usersNames, Deck deck) {
        this.users = new Users(usersNames, deck);
        this.dealer = new Dealer(getInitialCards(deck));
    }

    private List<Card> getInitialCards(final Deck randomDeck) {
        List<Card> cards = new ArrayList<>();
        cards.add(randomDeck.drawCard());
        cards.add(randomDeck.drawCard());
        return cards;
    }

    public void giveCard(Name user, Deck deck) {
        final User targetUser = users.finUserByName(user);
        targetUser.draw(deck.drawCard());
    }

    public int giveCardToDealerUntilDontNeed(Deck deck) {
        int additionalCardCount = 0;
        while (dealer.canReceive()) {
            dealer.draw(deck.drawCard());
            additionalCardCount += 1;
        }
        return additionalCardCount;
    }

    public boolean isBust(final Name name) {
        return users.checkBustBy(name);
    }

    public List<User> getUserOf(Result result) {
        final List<User> resultUsers = new ArrayList<>();
        for (User user : users.getUsers()) {
            if (Result.of(user.getCards(), dealer.getCards()) == result) {
                resultUsers.add(user);
            }
        }
        return resultUsers;
    }

    public Map<Result, List<User>> getAllUsersResult() {
        final LinkedHashMap<Result, List<User>> resultOfUsers = new LinkedHashMap<>();
        for (Result result : Result.values()) {
            resultOfUsers.put(result, getUserOf(result));
        }
        return resultOfUsers;
    }

    public User getUser(Name user) {
        return users.finUserByName(user);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Users getUsers() {
        return users;
    }
}
