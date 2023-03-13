package blackjack.dto;

import blackjack.domain.user.Dealer;

import java.util.List;
import java.util.stream.Collectors;

public class DealerDto {

    private final String name;
    private final List<CardDto> cards;
    private final int score;

    public DealerDto(final Dealer dealer) {
        this.name = dealer.getName();
        this.score = dealer.getScore().getValue();
        this.cards = dealer.showCards().stream()
                .map(CardDto::new)
                .collect(Collectors.toList());
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
