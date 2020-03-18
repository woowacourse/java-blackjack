package common;

import domain.card.Card;
import domain.user.User;

import java.util.List;

public class GamerDto {
    private String name;
    private List<Card> cards;

    private GamerDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerDto of(User user) {
        List<Card> cards = user.getPlayingCards();
        return new GamerDto(user.getName(), cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
