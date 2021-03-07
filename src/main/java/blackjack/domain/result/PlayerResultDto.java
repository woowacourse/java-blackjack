package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import java.util.List;

public class PlayerResultDto {

    private final String name;
    private final List<Card> cards;
    private final int sum;
    private final MatchResult winOrLose;

    public PlayerResultDto(String name, List<Card> cards, int sum, MatchResult winOrLose) {
        this.name = name;
        this.cards = cards;
        this.sum = sum;
        this.winOrLose = winOrLose;
    }

    public static PlayerResultDto from(Player player, MatchResult winOrLose) {
        return new PlayerResultDto(
                player.getName(),
                player.getCards(),
                player.sumCard(),
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
