package domain.member;

import domain.MatchResult;
import domain.card.Card;
import domain.state.Hit;
import domain.state.Stay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Members {

    private final Dealer dealer;
    private final List<Player> players;

    public Members(Map<String, Money> playerBets) {
        this.dealer = new Dealer(new Hit(new Hand()));
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

    public boolean isMeetTheDrawConditionForDealer() {
        return dealer.isMeetTheDrawCondition();
    }

    public List<String> getAllPlayerName() {
        return players.stream()
                .map(Member::name)
                .toList();
    }

//    public List<MatchResult> determineDealerGameResult() {
//        List<MatchResult> gameResult = new ArrayList<>();
//        players.forEach(player -> gameResult.add(dealer.compareScoreWith(player)));
//        return List.copyOf(gameResult);
//    }
//
//    public MatchResult determinePlayerGameResult(String name) {
//        Member player = findByPlayerName(name);
//        return player.compareScoreWith(dealer);
//    }

    public List<Integer> calculatePlayerProfits() {
        return players.stream()
                .map(Player::calculateProfit)
                .toList();
    }

    public boolean isPlayerFinished(String name) {
        return findByPlayerName(name).isFinished();
    }

    public String getDealerName() {
        return dealer.name();
    }

    private Member findByPlayerName(String playerName) {
        return players.stream()
                .filter(player -> player.name().equals(playerName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
