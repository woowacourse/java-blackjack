package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.ArrayList;
import java.util.List;

public class GamerCardsAssembler {
    private GamerCardsAssembler() {
    }

    public static GamerCardsDto create(Gamer gamer) {
        List<Card> cards = gamer.getCards();
        return new GamerCardsDto(gamer.getName(), cards);
    }

    public static GamerCardsDto createWithFirstCard(Gamer gamer) {
        List<Card> cards = gamer.getFirstCard();
        return new GamerCardsDto(gamer.getName(), cards);
    }

    public static List<GamerCardsDto> createDtos(Dealer dealer, Players players) {
        List<GamerCardsDto> gamerCardsDtos = new ArrayList<>();
        gamerCardsDtos.add(createWithFirstCard(dealer));
        for (Player player : players) {
            gamerCardsDtos.add(create(player));
        }
        return gamerCardsDtos;
    }
}
