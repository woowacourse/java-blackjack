package domain.gamer;

import domain.card.providable.CardProvidable;
import domain.result.BlackJackResult;
import domain.result.DealerResult;
import domain.result.UserResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AllGamers {
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final Dealer dealer;
    private final List<Player> players;

    public AllGamers(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawFirstPhase(CardProvidable cardProvidable) {
        List<Gamer> gamers = joinGamers();

        for (Gamer gamer : gamers) {
            drawInitialCardToEach(gamer, cardProvidable);
        }
    }

    private void drawInitialCardToEach(Gamer gamer, CardProvidable cardProvidable) {
        for (int i = 0; i < INITIAL_CARD_AMOUNT; i++) {
            gamer.drawCard(cardProvidable);
        }
    }

    private List<Gamer> joinGamers() {
        List<Gamer> gamers = new ArrayList<>();
        gamers.addAll(players);
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
        return players.stream()
                .map(player -> new UserResult(player.getName(), player.determineWinLose(dealer)))
                .collect(toList());
    }

    public List<Gamer> getGamers() {
        return Collections.unmodifiableList(joinGamers());
    }
}
