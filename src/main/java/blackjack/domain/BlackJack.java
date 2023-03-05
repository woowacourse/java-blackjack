package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    private final Users users;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJack(List<Name> usersName, Deck deck) {
        this.users = makeUsersBy(usersName, deck);
        this.dealer = new Dealer(getInitialCards(deck));
        this.deck = deck;
    }

    private Users makeUsersBy(final List<Name> usersName, final Deck deck) {
        List<User> users = new ArrayList<>();
        for (Name name : usersName) {
            List<Card> cards = getInitialCards(deck);
            final User user = new User(name, new Cards(cards));
            users.add(user);
        }
        return new Users(users);
    }

    private List<Card> getInitialCards(final Deck deck) {
        List<Card> cards = new ArrayList<>();
        cards.add(deck.drawCard());
        cards.add(deck.drawCard());
        return cards;
    }

    public void drawCard(Name user) {
        users.findUserAndGive(user, deck.drawCard());
    }

    public List<Card> getDealerCard() {
        return dealer.openCards();
    }

    public List<Card> getUserCard(Name user) {
        return users.getCardsOf(user);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public int finalizeDealer() {
        int additionalCardCount = 0;
        while (dealer.needCard()) {
            dealer.draw(deck.drawCard());
            additionalCardCount += 1;
        }
        return additionalCardCount;
    }

    public List<User> getUsersStatus() {
        return users.getUsers();
    }

    public Dealer getDealerStatus() {
        if (dealer.needCard()) {
            throw new IllegalStateException("딜러가 아직 카드의 결론이 나지 않았습니다.");
        }
        return dealer;
    }

    public GameResult getGameResult() {
        return new GameResult(dealer, users);
    }

    public Users getUsers() {
        return users;
    }

    public boolean isBusted(final Name name) {
        return users.isBusted(name);
    }
}
