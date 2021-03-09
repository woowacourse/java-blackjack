package blackjack.domain;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.betting.EarningRate;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.scoreboard.ScoreBoard;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.scoreboard.result.GameResult;
import blackjack.domain.scoreboard.result.UserGameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.ParticipantName;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class BlackjackGame {
    public static final int BLACKJACK_NUMBER = 21;

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
        dealer.drawCards(Arrays.asList(deck.draw(), deck.draw()));
        users.forEach(user -> user.drawCards(Arrays.asList(deck.draw(), deck.draw())));
    }

    public boolean existCanContinueUser() {
        return users.existCanContinueUser();
    }

    public User findFirstCanPlayUser() {
        return users.findFirstCanPlayUser();
    }

    public Card draw() {
        return deck.draw();
    }

    public void drawToDealer() {
        dealer.drawCard(deck.draw());
    }

    public ScoreBoard createScoreBoard() {
        LinkedHashMap<User, UserGameResult> userAndGameResults = users.stream()
                .collect(toMap(
                        Function.identity(),
                        this::createGameResult,
                        (exist, newer) -> newer, LinkedHashMap::new));
        return new ScoreBoard(userAndGameResults, createDealerGameResult());
    }

    private GameResult createDealerGameResult() {
        return new GameResult(dealer.getCards(), dealer.getName().toString());
    }

    private UserGameResult createGameResult(User user) {
        WinOrLose winOrLose = WinOrLose.decideWinOrLose(user, dealer);
        BettingMoney prizeMoney = EarningRate.calculate(user, winOrLose);
        return new UserGameResult(user.getCards(), user.getName().toString(), winOrLose, calculateIncome(user, prizeMoney));
    }

    private long calculateIncome(User user, BettingMoney prizeMoney) {
        return prizeMoney.subtract(user.getBettingMoney());
    }

    public boolean canDealerMoreDraw() {
        return dealer.canDealerMoreDraw();
    }

    public List<ParticipantName> getUserNames() {
        return users.getNameList();
    }

    public Card getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public Users getUsers() {
        return users;
    }
}
