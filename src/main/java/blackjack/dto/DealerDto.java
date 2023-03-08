package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Score;

import java.util.ArrayList;
import java.util.List;

public class DealerDto {

    private final String name;
    private final List<Card> cards;
    private final Score score;

    public DealerDto(final Dealer dealer) {
        this.name = dealer.getName();
        this.cards = new ArrayList<>(dealer.showCards());
        this.score = dealer.getScore();
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
