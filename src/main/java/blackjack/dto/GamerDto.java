package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Gamer;

import java.util.List;
import java.util.stream.Collectors;

public class GamerDto {

    private GamerDto() {
    }

    public static List<String> getPartOfCards(final Dealer dealer) {
        return dealer.openPartOfCards().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getCards(final Gamer gamer) {
        return gamer.openCards().stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }
}
