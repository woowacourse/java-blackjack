package blackjack.dto;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;

import java.util.List;

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

    public static GamerDto from(Dealer dealer) {
        return new GamerDto(
                dealer.getName(),
                new CardsDto(List.of(CardDto.from(dealer.getCards().getLast()))));
    }

    @Override
    public String toString() {
        return String.format("%s카드 : %s", name, cards);
    }
}
