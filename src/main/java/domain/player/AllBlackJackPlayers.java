package domain.player;

import domain.CardProvider;
import domain.result.BlackJackResult;
import domain.result.DealerResult;
import domain.result.UserResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AllBlackJackPlayers {
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final Dealer dealer;
    private final List<User> users;

    public AllBlackJackPlayers(Dealer dealer, List<User> users) {
        this.dealer = dealer;
        this.users = users;
    }

    public void drawFirstPhase(CardProvider cardProvider) {
        List<BlackJackPlayer> gamers = joinGamers();

        for (BlackJackPlayer blackJackPlayer : gamers) {
            drawInitialCardToEach(blackJackPlayer, cardProvider);
        }
    }

    private void drawInitialCardToEach(BlackJackPlayer blackJackPlayer, CardProvider cardProvider) {
        for (int i = 0; i < INITIAL_CARD_AMOUNT; i++) {
            blackJackPlayer.drawCard(cardProvider);
        }
    }

    private List<BlackJackPlayer> joinGamers() {
        List<BlackJackPlayer> gamers = new ArrayList<>();
        gamers.addAll(users);
        gamers.add(dealer);

        return gamers;
    }

    public List<BlackJackResult> determineResults() {
        List<BlackJackResult> gameResult = new ArrayList<>();

        gameResult.addAll(determineUsersResults());
        gameResult.add(new DealerResult(determineUsersResults()));

        return gameResult;
    }

    private List<UserResult> determineUsersResults() {
        return users.stream()
                .map(user -> new UserResult(user.getName(), user.determineWinLose(dealer)))
                .collect(toList());
    }

    public List<BlackJackPlayer> getGamers() {
        return Collections.unmodifiableList(joinGamers());
    }
}
