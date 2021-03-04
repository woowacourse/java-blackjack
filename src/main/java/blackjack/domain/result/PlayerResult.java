package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import java.util.List;

public class PlayerResult {

    private final String name;
    private final List<Card> cards;
    private final int sum;
    private final WinOrLose winOrLose;

    public PlayerResult(String name, List<Card> cards, int sum, WinOrLose winOrLose) {
        this.name = name;
        this.cards = cards;
        this.sum = sum;
        this.winOrLose = winOrLose;
    }

    public static PlayerResult from(Player player, WinOrLose winOrLose) {
        return new PlayerResult(
                player.getName(),
                player.getCardHand(),
                player.getCardSum(),
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
