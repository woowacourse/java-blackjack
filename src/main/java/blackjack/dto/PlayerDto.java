package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.user.Player;
import blackjack.domain.user.Score;

import java.util.ArrayList;
import java.util.List;

public class PlayerDto {

    private final String name;
    private final List<Card> cards;
    private final Score score;

    public PlayerDto(final Player player) {
        this.name = player.getName();
        this.cards = new ArrayList<>(player.showCards());
        this.score = player.getScore();
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }
}
