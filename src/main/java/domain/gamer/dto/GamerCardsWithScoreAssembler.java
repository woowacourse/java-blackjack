package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.ArrayList;
import java.util.List;

public class GamerCardsWithScoreAssembler {
    private GamerCardsWithScoreAssembler() {
    }

    public static GamerCardsWithScoreDto create(Gamer gamer) {
        List<Card> cards = gamer.getCards();
        return new GamerCardsWithScoreDto(gamer.getName(), cards, gamer.calculateScore().getScore());
    }

    public static List<GamerCardsWithScoreDto> createDtos(Dealer dealer, Players players) {
        List<GamerCardsWithScoreDto> gamerCardsWithScoreDtos = new ArrayList<>();
        gamerCardsWithScoreDtos.add(create(dealer));
        for (Player player : players) {
            gamerCardsWithScoreDtos.add(create(player));
        }
        return gamerCardsWithScoreDtos;
    }
}
