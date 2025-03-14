package domain.participant;

import domain.CardDeck;
import domain.GameResult;
import domain.GameStatus;
import domain.card.Card;
import exception.ErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameParticipant {

    private final Dealer dealer;
    private final List<Player> players;

    public GameParticipant() {
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

    public GameResult determineDealerGameResult() {
        GameResult dealerGameResult = new GameResult(dealer.getName());
        for (Player player : players) {
            GameStatus dealerGameStatus = dealer.determineGameStatus(player);
            dealerGameResult.addStatusCount(dealerGameStatus);
        }
        return dealerGameResult;
    }

    public List<GameResult> determinePlayerGameResults() {
        List<GameResult> playerGameResults = new ArrayList<>();
        for (Player player : players) {
            GameResult playerGameResult = new GameResult(player.getName());
            GameStatus playerGameStatus = player.determineGameStatus(dealer);
            playerGameResult.addStatusCount(playerGameStatus);
            playerGameResults.add(playerGameResult);
        }
        return playerGameResults;
    }

    public void addExtraCard(Participant participant, CardDeck cardDeck) {
        Card card = cardDeck.pickCard();
        participant.addCard(card);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void distributeInitialCard(List<Card> cards) {
        dealer.addCard(cards.removeFirst());
        for (Player player : players) {
            player.addCard(cards.removeFirst());
        }
    }

    private void validateDistinct(List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new ErrorException("참여자 이름은 중복될 수 없습니다.");
        }
    }
}
