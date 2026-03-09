package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;

import java.util.List;

public record PlayerHandDto(
        String playerName,
        String handOnCards
) {

    public static PlayerHandDto from(Gamer gamer) {
        return new PlayerHandDto(gamer.getMyName(), joining(openGamerCards(gamer)));
    }

    public static PlayerHandDto onlyFirstCard(Dealer dealer) {
        return new PlayerHandDto(dealer.getMyName(), dealer.openMyFirstCard().openCard());
    }

    private static String joining(List<String> strings) {
        return String.join(", ", strings);
    }

    private static List<String> openGamerCards(Gamer gamer) {
        return gamer.openMyCards().stream()
                .map(Card::openCard)
                .toList();
    }

}
