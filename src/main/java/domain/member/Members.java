package domain.member;

import domain.card.Card;
import domain.state.DealerHit;
import domain.state.Hit;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Members {

    private final Dealer dealer;
    private final List<Player> players;

    public Members(Map<String, Money> playerBets) {
        this.dealer = new Dealer(new DealerHit(new Hand()));
        this.players = playerBets.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue(), new Hit(new Hand())))
                .toList();
    }

    public void provideCardToPlayer(String playerName, Card card) {
        Member player = findByPlayerName(playerName);
        player.receiveCard(card);
    }

    public void provideCardToDealer(Card card) {
        dealer.receiveCard(card);
    }

    public List<Card> findCardByName(String playerName) {
        Member player = findByPlayerName(playerName);
        return player.currentCards();
    }

    public List<Card> findDealerCards() {
        return dealer.currentCards();
    }

    public int checkPlayerScore(String playerName) {
        Member player = findByPlayerName(playerName);
        return player.currentScore();
    }

    public int checkDealerScore() {
        return dealer.currentScore();
    }

    public boolean canTheDealerDraw() {
        return !dealer.isFinished();
    }

    public List<String> getAllPlayerName() {
        return players.stream()
                .map(Member::name)
                .toList();
    }

    public Map<String, Integer> calculateFinalProfits() {
        validateFinished();
        Map<String, Integer> totalResults = new LinkedHashMap<>();
        players.forEach(player ->
                totalResults.put(player.name(), player.calculateProfit(dealer)));
        int totalPlayerProfit = totalResults.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        totalResults.put(dealer.name(), -1 * totalPlayerProfit);
        return totalResults;
    }

    private void validateFinished() {
        if (!dealer.isFinished() || players.stream()
                .anyMatch(player -> !player.isFinished())) {
            throw new IllegalArgumentException("게임이 끝나지 않은 사람이 있습니다.");
        }
    }

    public boolean isPlayerBust(String name) {
        return findByPlayerName(name).isBust();
    }

    public void changePlayerStateToStay(String playerName) {
        findByPlayerName(playerName).changeToStay();
    }

    public String getDealerName() {
        return dealer.name();
    }

    private Player findByPlayerName(String playerName) {
        return players.stream()
                .filter(player -> player.name().equals(playerName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
