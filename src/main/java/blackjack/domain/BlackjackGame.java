package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.scoreboard.result.GameResult;
import blackjack.domain.scoreboard.result.UserGameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class BlackjackGame {
    private static final String NO_MORE_PLAYING_USER_ERROR_MSG = "플레이 가능한 유저가 없습니다.";
    private final Deck deck = Deck.createDeck();
    private final Users users;
    private final Dealer dealer = new Dealer();

    private BlackjackGame(Users users) {
        this.users = users;
    }

    public static BlackjackGame create(Users users) {
        return new BlackjackGame(users);
    }

    public void firstDraw() {
        dealer.firstDraw(deck.draw(), deck.draw());
        users.forEach(user -> user.firstDraw(deck.draw(), deck.draw()));
    }

    public boolean existCanContinueUser(){
        return users.stream().anyMatch(User::canContinueGame);
    }

    public User findFirstCanPlayUser(){
        return users.stream()
                .filter(User::canContinueGame)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(NO_MORE_PLAYING_USER_ERROR_MSG));
    }

    public Card draw() {
        return deck.draw();
    }

    public void drawToDealer() {
        dealer.drawCard(deck.draw());
    }

    public ScoreBoard createScoreBoard(){
        return new ScoreBoard(
                users.stream()
                .collect(
                        toMap(Participant::getName, this::createGameResult, (exist, newer) -> newer, LinkedHashMap::new)
                )
                , createDealerGameResult());
    }

    private GameResult createDealerGameResult() {
        return new GameResult(dealer.getCards(), dealer.getName());
}

    private UserGameResult createGameResult(User user) {
        return new UserGameResult(user.getCards(), user.getName(), WinOrLose.decideWinOrLose(user, dealer));
    }

    public int getDealerHandSize() {
        return dealer.handSize();
    }

    public Stream<User> getUsersStream() {
        return users.stream();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
