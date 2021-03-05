package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.scoreboard.result.GameResult;
import blackjack.domain.scoreboard.result.UserGameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class BlackjackGame {
    private static final String NO_MORE_PLAYING_USER_ERROR_MSG = "플레이 가능한 유저가 없습니다.";
    private final Deck deck = Deck.createDeck();
    private final Users users;
    private final Dealer dealer = new Dealer();

    private BlackjackGame(Users users) {
        this.users = users;
    }

    public static BlackjackGame createAndFirstDraw(Users users) {
        BlackjackGame blackjackGame = new BlackjackGame(users);
        blackjackGame.firstDraw();
        return blackjackGame;
    }

    public Dealer getDealer() {
        return dealer;
    }

    private void firstDraw() {
        dealer.firstDraw(deck.draw(), deck.draw());
        users.forEach(user -> user.firstDraw(deck.draw(), deck.draw()));
    }

    public int getDealerHandSize() {
        return dealer.handSize();
    }

    public User findFirstCanPlayUser(){
        return users.stream()
                .filter(User::canContinueGame)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(NO_MORE_PLAYING_USER_ERROR_MSG));
    }

    public Card draw(){
        return deck.draw();
    }

    public Users getUsers() {
        return users;
    }

    public List<String> getUserNames(){
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public ScoreBoard createScoreBoard(){
        return new ScoreBoard(
                users.stream()
                .collect(
                        toMap(Function.identity(), this::createGameResult, (exist, newer) -> newer, LinkedHashMap::new)
                )
                , createDealerGameResult());
    }

    private GameResult createDealerGameResult() {
        return new GameResult(dealer.getCards(), dealer.getName());
    }

    public boolean existCanContinueUser(){
        return users.stream().anyMatch(User::canContinueGame);
    }

    private UserGameResult createGameResult(User user) {
        return new UserGameResult(user.getCards(), user.getName(), WinOrLose.decideWinOrLose(user, dealer));
    }
}
