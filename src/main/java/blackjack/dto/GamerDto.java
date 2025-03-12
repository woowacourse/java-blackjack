package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.dto.card.CardDto;
import blackjack.dto.card.CardsDto;

public record GamerDto(
    String name,
    CardsDto cards
) {

    public static GamerDto from(Gamer gamer) {
        return new GamerDto(
            gamer.getName(),
            new CardsDto(gamer.getCards().stream()
                .map(CardDto::from)
                .toList()));
    }

    public static GamerDto dealerFrom(Dealer dealer) {
        return new GamerDto(
            dealer.getName(),
            new CardsDto(List.of(CardDto.from(dealer.getCards().getLast()))));
    }

    @Override
    public String toString() {
        return String.format("%s카드 : %s", name, cards);
    }
}
