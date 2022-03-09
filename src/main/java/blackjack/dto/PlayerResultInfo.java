package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import java.util.List;

public class PlayerResultInfo {

    private final String name;
    private final List<Card> cards;
    private final int score;

    private PlayerResultInfo(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static PlayerResultInfo from(final Player player) {
        return new PlayerResultInfo(player.getName(), player.cards(), player.calculateResultScore());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
