package domain;

import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.YesOrNo;

public class BlackjackGame {
    private static final int INIT_CARD_NUMBER = 2;

    private final Participants participants;
    private final CardDeck cardDeck;

    private BlackjackGame(Participants participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public static BlackjackGame of(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        users.addAll(players);
        Participants participants = new Participants(users);
        return new BlackjackGame(participants, cardDeck);
    }

    public void firstHandOutCard() {
        for (int count = 0; count < INIT_CARD_NUMBER; count++) {
            participants.drawFirstCard(cardDeck);
        }
    }

    public User getDealer() {
        return participants.getDealer();
    }

    private GameResult compareScore(User player) {
        int dealerScore = participants.getDealer().calculateScore();
        int playerScore = player.calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore) {
            return GameResult.WIN;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return compareSameScore(player);
    }

    private GameResult compareSameScore(User player) {
        User dealer = participants.getDealer();
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public Map<User, GameResult> calculatePlayerScore() {
        Map<User, GameResult> gameResult = new LinkedHashMap<>();
        if (participants.getDealer().isBust()) {
            participants.getPlayers().forEach((user) -> putGameResultBust(user, gameResult));
            return gameResult;
        }
        participants.getPlayers().forEach((user) -> gameResult.put(user, compareScore(user)));
        return gameResult;
    }

    private void putGameResultBust(User user, Map<User, GameResult> gameResult) {
        if (user.isBust()) {
            gameResult.put(user, GameResult.LOSE);
            return;
        }
        gameResult.put(user, GameResult.WIN);
    }

    public Map<GameResult, Integer> calculateDealerScore() {
        Map<GameResult, Integer> gameResult = new HashMap<>();
        Map<User, GameResult> userGameResultMap = calculatePlayerScore();

        gameResult.put(GameResult.LOSE, getResultStateCount(userGameResultMap, GameResult.WIN));
        gameResult.put(GameResult.WIN, getResultStateCount(userGameResultMap, GameResult.LOSE));
        gameResult.put(GameResult.DRAW, getResultStateCount(userGameResultMap, GameResult.DRAW));

        return gameResult;
    }

    private int getResultStateCount(Map<User, GameResult> gameResult, GameResult status) {
        return (int) gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == status)
                .count();
    }

    public Map<String, List<Card>> openFirstPlayersCard() {
        return participants.getPlayersAllCard();
    }

    public List<Card> openFirstDealerCard() {
        User dealer = getDealer();
        return dealer.openInitialCard();
    }

    public void controlTurn(User user, YesOrNo yesOrNo) {
        if (yesOrNo == YesOrNo.YES && user.isDrawable()) {
            user.drawCard(cardDeck.drawCard());
        }
    }

    public boolean isDrawable(User user) {
        return participants.isDrawable(user);
    }

    public int calculateScore(User user) {
        return participants.calculateScore(user);
    }
}
