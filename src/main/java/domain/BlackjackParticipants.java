package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackParticipants {

    private final String INVALID_PLAYER = "존재하지 않는 플레이어입니다.";

    private final List<Player> players;
    private final Dealer dealer;

    public BlackjackParticipants(List<Player> players, Dealer dealer) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
    }

    private Player findPlayer(String name) {
        return players.stream()
                .filter(player -> player.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYER));
    }

    public List<BlackjackResult> calculatePlayerResults() {
        List<BlackjackResult> blackjackResults = new ArrayList<>();
        for (String name : getPlayerNames()) {
            List<TrumpCard> trumpCards = playerCards(name);
            int sum = calculateCardSum(name);
            blackjackResults.add(new BlackjackResult(name, trumpCards, sum));
        }
        return Collections.unmodifiableList(blackjackResults);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::name)
                .toList();
    }

    public List<TrumpCard> playerCards(String name) {
        Player player = findPlayer(name);
        return player.trumpCards();
    }

    public BlackjackResult calculateDealerResult() {
        int sum = dealer.calculateCardSum();
        List<TrumpCard> trumpCards = dealerCards();
        String name = dealerName();
        return new BlackjackResult(name, trumpCards, sum);
    }

    public List<TrumpCard> dealerCards() {
        return dealer.trumpCards();
    }

    public int calculateCardSum(String name) {
        Player player = findPlayer(name);
        return player.calculateCardSum();
    }

    public String dealerName() {
        return dealer.name();
    }

    public boolean isBust(String name) {
        Player player = findPlayer(name);
        return !player.isDrawable();
    }

    public void addCard(String name, TrumpCard trumpCard) {
        Player findPlayer = findPlayer(name);
        findPlayer.addDraw(trumpCard);
    }

    public void addDealerCard(TrumpCard trumpCard) {
        dealer.addDraw(trumpCard);
    }

    public boolean dealerDrawable() {
        return dealer.isDrawable();
    }
}
