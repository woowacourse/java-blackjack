package blackjack.domian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private static final int PLAYER_MAX_SIZE = 10;
    private static final int PLAYER_MIN_SIZE = 2;

    private final List<Player> players;
    private final Deck deck;
    private final List<Card> cards;

    public Dealer(List<Player> players, Deck deck) {
        if (players.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어 수는 최대 " + PLAYER_MAX_SIZE + "명입니다.");
        }
        if (players.size() < PLAYER_MIN_SIZE) {
            throw new IllegalArgumentException("플레이어 수는 최소 " + PLAYER_MIN_SIZE + "명입니다.");
        }
        this.players = players;
        this.deck = deck;
        this.cards = new ArrayList<>();
    }

    public void pickCards() {
        cards.addAll(List.of(deck.draw(), deck.draw()));
    }

    public void handOutCard() {
        for (Player player : players) {
            player.send(deck.draw(), deck.draw());
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void pickAdditionalCard() {
        int maxScore = calculateMaxScore();
        while (maxScore >= 0 && maxScore <= 16) {
            cards.add(deck.draw());
            maxScore = calculateMaxScore();
        }
    }

    public int calculateMaxScore() {
        return solve2(0, 0);
    }

    private int solve2(int depth, int totalScore) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        List<Integer> scores = card.getRank().getScore();
        int max = Integer.MIN_VALUE;
        for (int score : scores) {
            int sum = solve2(depth + 1, totalScore + score);
            if (sum > 21) {
                continue;
            }
            max = Math.max(max, sum);
        }

        return max;
    }
}
