package domain;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Name;
import domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public final class BlackJack {

    public static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final Users users;
    private final Dealer dealer;
    private final Deck deck;

    private BlackJack(List<Name> userNames, Deck deck) {
        final List<User> userList = userNames.stream()
                .map(User::of)
                .collect(Collectors.toList());
        this.users = new Users(userList);
        this.dealer = new Dealer();
        this.deck = deck;
        initGame();
    }

    public static BlackJack getInstance(List<Name> userNames, Deck deck) {
        return new BlackJack(userNames, deck);
    }

    private void initGame() {
        users.giveEachUser(deck, INITIAL_DRAW_CARD_COUNT);
        dealer.give(deck, INITIAL_DRAW_CARD_COUNT);
    }

    public void drawCard(User user) {
        users.findUserAndGive(user, deck.drawCard());
    }


    public List<Card> getCardsFrom(User user) {
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

    public List<User> getUserList() {
        return users.getUsers();
    }

    public boolean isBusted(final User user) {
        return users.isBusted(user);
    }
}
