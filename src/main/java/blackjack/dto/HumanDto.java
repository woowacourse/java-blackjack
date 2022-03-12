package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Human;

import java.util.List;
import java.util.stream.Collectors;

public class HumanDto {

    private HumanDto() {
    }

    public static List<String> getPartOfCards(final Dealer dealer) {
        return dealer.openPartOfCards().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getCards(final Human human) {
        return human.openCards().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }
}
