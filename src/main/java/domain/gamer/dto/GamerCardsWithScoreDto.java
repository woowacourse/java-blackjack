package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.ArrayList;
import java.util.List;

public class GamerCardsWithScoreDto {
    private String name;
    private List<Card> cards;
    private int score;

    private GamerCardsWithScoreDto(String name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static GamerCardsWithScoreDto of(Gamer gamer) {
        List<Card> cards = gamer.getCards();
        return new GamerCardsWithScoreDto(gamer.getName(), cards, gamer.calculateScore().getScore());
    }

    public static List<GamerCardsWithScoreDto> of(Dealer dealer, Players players) {
        List<GamerCardsWithScoreDto> gamerCardsWithScoreDtos = new ArrayList<>();
        gamerCardsWithScoreDtos.add(of(dealer));
        for (Player player : players) {
            gamerCardsWithScoreDtos.add(of(player));
        }
        return gamerCardsWithScoreDtos;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}

