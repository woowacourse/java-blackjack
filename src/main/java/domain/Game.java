package domain;

import domain.card.Card;
import exception.ErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    public GameResult determineGameResult(String name) {
        Participant participant = findParticipantBy(name);
        if (isDealer(name)) {
            return determineDealerGameResult((Dealer) participant);
        }
        return determinePlayerGameResult((Player) participant);
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

    private boolean isDealer(String name) {
        return Objects.equals(name, "딜러");
    }

    private void distributeInitialCard(List<Card> cards) {
        dealer.addCard(cards.removeFirst());
        for (Player player : players) {
            player.addCard(cards.removeFirst());
        }
    }

    private Participant findParticipantBy(String name) {
        if (isDealer(name)) {
            return dealer;
        }
        return players.stream()
                .filter(p -> p.isParticipant(name))
                .findFirst()
                .orElseThrow(() -> new ErrorException("존재하지 않는 참여자입니다."));
    }

    private GameResult determineDealerGameResult(Dealer dealer) {
        GameResult dealerGameResult = new GameResult();
        for (Player player : players) {
            GameStatus dealerGameStatus = dealer.determineGameStatus(player);
            dealerGameResult.addStatusCount(dealerGameStatus);
        }
        return dealerGameResult;
    }

    private GameResult determinePlayerGameResult(Player player) {
        GameResult playerGameResult = new GameResult();
        GameStatus playerGameStatus = player.determineGameStatus(dealer);
        playerGameResult.addStatusCount(playerGameStatus);
        return playerGameResult;
    }

    private void validateDistinct(List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new ErrorException("참여자 이름은 중복될 수 없습니다.");
        }
    }
}
