package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.scoreboard.DealerGameResult;
import blackjack.domain.scoreboard.UserGameResult;
import blackjack.domain.scoreboard.Profit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class BlackjackGame {
    private static final int DEALER_MINIMUM_SCORE = 16;
    private static final int FIRST_DRAW_COUNT = 2;

    private final Deck deck = Deck.createDeck();
    private final Users users;
    private final Dealer dealer = new Dealer();

    private BlackjackGame(Users users) {
        this.users = users;
    }

    public static BlackjackGame createAndFirstDraw(Users users) {
        BlackjackGame blackjackGame = new BlackjackGame(users);
        blackjackGame.firstDrawToPlayers();
        return blackjackGame;
    }

    private void firstDrawToPlayers() {
        for (int i = 0; i < FIRST_DRAW_COUNT; i++) {
            drawCardToDealer();
            users.toList().forEach(this::drawCardToUser);
        }
    }

    public void userDrawOrStop(User user, boolean hitOrStay) {
        if (hitOrStay) {
            drawCardToUser(user);
            return;
        }
        user.stayUser();
    }

    public void processDealerRound() {
        while (dealer.calculateScore() < DEALER_MINIMUM_SCORE) {
            drawCardToDealer();
        }
    }

    public List<String> getUserNames() {
        return users.toList().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public UserGameResult createUserGameResult() {
        Map<User, Double> userResult = users.toList().stream()
                .collect(toMap(
                        Function.identity(),
                        this::makeUserProfit,
                        (existed, newer) -> newer,
                        LinkedHashMap::new));

        return new UserGameResult(userResult);
    }

    private double makeUserProfit(User user) {
        return user.getMoney() * Profit.decideProfit(user, dealer).getProfit();
    }

    private void drawCardToDealer() {
        dealer.drawCard(deck.draw());
    }

    private void drawCardToUser(User user) {
        user.drawCard(deck.draw());
    }

    public DealerGameResult createDealerGameResult() {
        return new DealerGameResult(dealer, dealer.getCards());
    }

    public Card getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users.toList());
    }
}
