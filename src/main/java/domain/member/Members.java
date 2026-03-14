package domain.member;

import domain.MatchResult;
import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Members {

    private final Dealer dealer;
    private final List<Player> players;

    public Members(List<String> playerNames) {
        this.dealer = new Dealer();
        this.players = playerNames.stream()
                .map(Player::new)
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

    public List<MatchResult> determineDealerGameResult() {
        List<MatchResult> gameResult = new ArrayList<>();
        players.forEach(player -> gameResult.add(dealer.compareScoreWith(player)));
        return List.copyOf(gameResult);
    }

    public MatchResult determinePlayerGameResult(String name) {
        Member player = findByPlayerName(name);
        return player.compareScoreWith(dealer);
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
