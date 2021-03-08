package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.scoreboard.GameResult;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.UserGameResult;
import blackjack.domain.scoreboard.WinOrLose;
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
    private static final String NO_MORE_PLAYING_USER_ERROR_MSG = "플레이 가능한 유저가 없습니다.";
    private static final int DEALER_MINIMUM_SCORE = 16;

    private final Deck deck = Deck.createDeck();
    private final Users users;
    private final Dealer dealer = new Dealer();

    private BlackjackGame(Users users) {
        this.users = users;
    }

    public static BlackjackGame createAndFirstDraw(Users users) {
        BlackjackGame blackjackGame = new BlackjackGame(users);
        blackjackGame.init();
        return blackjackGame;
    }

    public boolean dealerScoreUnderSixTeen() {
        return dealer.calculateScore() < DEALER_MINIMUM_SCORE;
    }

    private void init() {
        dealer.firstDraw(deck.draw(), deck.draw());
        users.toList().forEach(user -> user.firstDraw(deck.draw(), deck.draw()));
    }

    public User findFirstCanPlayUser() {
        return users.toList().stream()
                .filter(User::canContinueGame)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(NO_MORE_PLAYING_USER_ERROR_MSG));
    }

    public List<String> getUserNames() {
        return users.toList().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public ScoreBoard createScoreBoard() {
        Map<User, UserGameResult> userResult = users.toList().stream()
                .collect(toMap(
                        Function.identity(),
                        this::createGameResult,
                        (exist, newer) -> newer, LinkedHashMap::new));

        return new ScoreBoard(userResult, createDealerGameResult());
    }

    public void drawCardToDealer() {
        dealer.drawCard(deck.draw());
    }

    public void drawCardToUser(User currentUser) {
        currentUser.drawCard(deck.draw());
    }

    public GameResult createDealerGameResult() {
        return new GameResult(dealer.getCards(), dealer.getName());
    }

    private UserGameResult createGameResult(User user) {
        return new UserGameResult(user.getCards(), user.getName(), WinOrLose.decideWinOrLose(user, dealer));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users.toList());
    }
}
