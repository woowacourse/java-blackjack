package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    private static final int INITIAL_CARD_COUNT = 2;
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

    private void giveCardToPlayers() {
        giveCardToUsers();
        giveCardToDealer();
    }

    private void giveCardToUsers() {
        users.giveEachUser(deck, INITIAL_CARD_COUNT);
    }

    private void giveCardToDealer() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.draw(deck.drawCard());
        }
    }

    public void drawCard(Name user) {
        users.findUserAndGive(user, deck.drawCard());
    }

    public List<Card> getDealerCard() {
        return dealer.openCards();
    }

    public List<Card> getInitialDealerCard() {
        return dealer.getFirstCard();
    }

    public List<Card> getUserCard(Name user) {
        return users.getCardsOf(user);
    }

    public void finalizeDealer() {
        while (dealer.needCard()) {
            dealer.draw(deck.drawCard());
        }
    }
}
