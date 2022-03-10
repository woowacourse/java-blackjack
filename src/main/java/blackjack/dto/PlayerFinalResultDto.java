package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public class PlayerFinalResultDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    private PlayerFinalResultDto(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static PlayerFinalResultDto from(final Player player) {
        return new PlayerFinalResultDto(player.getName(), player.getCards(), player.calculateScore());
    }

    public static PlayerFinalResultDto from(final Dealer dealer) {
        return new PlayerFinalResultDto(dealer.getName(), dealer.getCards(), dealer.calculateScore());
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
