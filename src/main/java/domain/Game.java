package domain;

import domain.card.Card;
import exception.ErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {

    private final Dealer dealer;
    private final List<Player> players;

    public Game() {
        this.dealer = new Dealer();
        this.players = new ArrayList<>();
    }

    public void registerPlayers(List<String> names) {
        validateDistinct(names);
        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public int countParticipants() {
        return players.size() + 1;
    }

    public void distributeInitialCards(List<List<Card>> cardsStack) {
        for (List<Card> cards : cardsStack) {
            distributeInitialCard(cards);
        }
    }

    private void distributeInitialCard(List<Card> cards) {
        dealer.addCard(cards.removeFirst());
        for (Player player : players) {
            player.addCard(cards.removeFirst());
        }
    }

    public Map<Participant, GameResult> determineDealerGameResult() {
        Map<Participant, GameResult> gameResult = new HashMap<>();
        GameResult dealerGameResult = new GameResult();
        for (Player player : players) {
            determineGameResult(dealer, player, dealerGameResult);
        }
        gameResult.put(dealer, dealerGameResult);
        return gameResult;
    }

    public Map<Participant, GameResult> determinePlayersGameResult() {
        Map<Participant, GameResult> gameResult = new HashMap<>();
        for (Player player : players) {
            GameResult playerGameResult = new GameResult();
            gameResult.put(player, determineGameResult(player, dealer, playerGameResult));
        }
        return gameResult;
    }

    private GameResult determineGameResult(Participant participant, Participant other, GameResult gameResult) {
        GameStatus gameStatus = participant.determineGameStatus(other);
        gameResult.addStatusCount(gameStatus);
        return gameResult;
    }

    private void validateDistinct(List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new ErrorException("참여자 이름은 중복될 수 없습니다.");
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
