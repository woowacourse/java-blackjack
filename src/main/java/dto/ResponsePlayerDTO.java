package dto;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;

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

    private ResponsePlayerDTO(Player player) {
        setName(player);
        List<Card> cards = player.getCard();
        this.cardInfo = cards.stream().map(Card::toString).collect(Collectors.joining(DELIMITER));
        this.score = Integer.toString(player.sumCardNumber());
    }

    private void setName(Player player) {
        if (player instanceof Dealer) {
            this.name = DEALER_NAME;
            return;
        }
        User targetUser = (User) player;
        this.name = targetUser.getName();
    }

    public static List<ResponsePlayerDTO> getResult(Players players) {
        List<ResponsePlayerDTO> responsePlayerDTOS = new ArrayList<>();
        responsePlayerDTOS.add(new ResponsePlayerDTO(players.getDealer()));
        responsePlayerDTOS.addAll(players.getUsers().stream()
                .map(ResponsePlayerDTO::new)
                .collect(Collectors.toList()));
        return Collections.unmodifiableList(responsePlayerDTOS);
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
