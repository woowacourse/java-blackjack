package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.scoreboard.DealerGameResult;
import blackjack.domain.scoreboard.GameResult;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Status;
import blackjack.domain.user.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class BlackjackGame {
    private final Deck deck = Deck.createDeck();
    //todo : 일급컬렉션 생성
    private final List<User> users;
    private final Dealer dealer = new Dealer();

    private BlackjackGame(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public static BlackjackGame createAndFirstDraw(List<User> users) {
        BlackjackGame blackjackGame = new BlackjackGame(users);
        blackjackGame.init();
        return blackjackGame;
    }

    public Dealer getDealer() {
        return dealer;
    }

    private void init() {
        dealer.firstDraw(deck.draw(), deck.draw());
        users.forEach(user -> user.firstDraw(deck.draw(), deck.draw()));
    }

    public int getDealerHandSize() {
        return dealer.handSize();
    }

    public Optional<User> findFirstUserByStatus(Status status){
        return users.stream()
                .filter(user -> user.isSameStatus(status))
                .findFirst();
    }

    public Card draw(){
        return deck.draw();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
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

    private DealerGameResult createDealerGameResult() {
        return new DealerGameResult(dealer.getCards());
    }

    public boolean isExistPlayingUser(){
        return findFirstUserByStatus(Status.PLAYING).isPresent();
    }

    private GameResult createGameResult(User user) {
        return new GameResult(user.getCards(), WinOrLose.decideWinOrLose(user, dealer));
    }


}
