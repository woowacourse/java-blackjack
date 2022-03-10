package blackjack.dto;

import static java.util.stream.Collectors.*;

import blackjack.domain.Dealer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerDto {

    private final String name;
    private final int totalScore;
    private final List<CardDto> cards;

    private DealerDto(String name, int totalScore, List<CardDto> cards) {
        this.name = name;
        this.totalScore = totalScore;
        this.cards = new ArrayList<>(cards);
    }

    public static DealerDto from(Dealer dealer) {
        List<CardDto> cards = dealer.getCards()
                .stream()
                .map(CardDto::from)
                .collect(toList());

        return new DealerDto(dealer.getName(), dealer.getTotalScore(), cards);
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<CardDto> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
