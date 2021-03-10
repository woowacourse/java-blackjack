package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import java.util.List;

public class PlayerResult {
    // todo Dto로 안만드려면 얘한테도 책임을 줘야할텐데...

    private final String name;
    private final List<Card> cards;
    private final int sum;
    private final MatchResult winOrLose;

    public PlayerResult(String name, List<Card> cards, int sum, MatchResult winOrLose) {
        this.name = name;
        this.cards = cards;
        this.sum = sum;
        this.winOrLose = winOrLose;
    }

    public static PlayerResult from(Player player, MatchResult winOrLose) {
        return new PlayerResult(
                player.getName(),
                player.getCards(),
                player.getHandTotal(),
                winOrLose);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getSum() {
        return sum;
    }

    public String getWinOrLose() {
        return winOrLose.getName();
    }
}
