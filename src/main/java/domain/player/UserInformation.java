package domain.player;

import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class UserInformation {
    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ",";

    private String name;
    private String cardInformation;
    private String score;

    public UserInformation(User user) {
        determineUser(user);
        List<Card> cards = user.getCard();

        this.cardInformation = cards.stream().map(Card::toString).collect(Collectors.joining(DELIMITER));
        this.score = Integer.toString(user.sumCardNumber());
    }

    private void determineUser(User user) {
        if (user instanceof Dealer) {
            this.name = DEALER_NAME;
            return;
        }

        Player player = (Player) user;
        this.name = player.getName();
    }

    public String getName() {
        return this.name;
    }

    public String getCardInformation() {
        return this.cardInformation;
    }

    public String getScore() {
        return this.score;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }
}
