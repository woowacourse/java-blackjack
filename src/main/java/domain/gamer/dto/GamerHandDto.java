package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;

import java.util.List;

public record GamerHandDto(
        String playerName,
        String handOnCards
) {
    public static GamerHandDto from(Gamer gamer) {
        return new GamerHandDto(gamer.getMyName(), joining(openGamerCards(gamer)));
    }

    public static GamerHandDto onlyFirstCard(Dealer dealer) {
        return new GamerHandDto(dealer.getMyName(), dealer.openMyFirstCard().openCard());
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
