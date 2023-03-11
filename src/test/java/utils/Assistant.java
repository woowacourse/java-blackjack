package utils;

import domain.card.Card;
import domain.user.User;

import java.util.Arrays;

import static domain.card.Suit.HEART;

public class Assistant {
    public static void addCards(User user, String... letters) {
        Arrays.stream(letters)
                .forEach(letter -> user.addCard(new Card(HEART, letter)));
    }
}
