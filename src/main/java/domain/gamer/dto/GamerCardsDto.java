package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;

import java.util.ArrayList;
import java.util.List;

public class GamerCardsDto {
    private String name;
    private List<Card> cards;

    private GamerCardsDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerCardsDto of(Gamer gamer) {
        List<Card> cards = gamer.getCards();
        return new GamerCardsDto(gamer.getName(), cards);
    }

    public static List<GamerCardsDto> of(Dealer dealer, Players players) {
        List<GamerCardsDto> playerDtos = new ArrayList<>();
        playerDtos.add(of(dealer));
        for (Player player : players) {
            playerDtos.add(of(player));
        }
        return playerDtos;
    }

    public static GamerCardsDto ofWithFirstCard(Gamer gamer) {
        List<Card> cards = gamer.getFirstCard();
        return new GamerCardsDto(gamer.getName(), cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
