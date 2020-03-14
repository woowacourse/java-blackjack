package dto;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResponsePlayerDTO {
    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ",";

    private String name;
    private String cardInfo;
    private String score;

    private ResponsePlayerDTO(User user) {
        setName(user);
        List<Card> cards = user.getCard();
        this.cardInfo = cards.stream().map(Card::toString).collect(Collectors.joining(DELIMITER));
        this.score = Integer.toString(user.sumCardNumber());
    }

    private void setName(User user) {
        if (user instanceof Dealer) {
            this.name = DEALER_NAME;
            return;
        }

        Player player = (Player) user;
        this.name = player.getName();
    }

    public static List<ResponsePlayerDTO> of(Users users) {
        List<ResponsePlayerDTO> responsePlayerDTOS = new ArrayList<>();

        responsePlayerDTOS.add(new ResponsePlayerDTO(users.getDealer()));
        responsePlayerDTOS.addAll(users.getUsers().stream()
                .map(ResponsePlayerDTO::new)
                .collect(Collectors.toList()));

        return Collections.unmodifiableList(responsePlayerDTOS);
    }

    public static ResponsePlayerDTO of(User user) {
        return new ResponsePlayerDTO(user);
    }

    public String getName() {
        return this.name;
    }

    public String getCardInfo() {
        return this.cardInfo;
    }

    public String getScore() {
        return this.score;
    }
}
