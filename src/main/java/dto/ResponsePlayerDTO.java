package dto;

import domain.player.Player;
import domain.player.Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResponsePlayerDTO {
    private String name;
    private List<String> cardNumbers;
    private String score;

    private ResponsePlayerDTO(Player player) {
        this.name = player.getName();
        this.cardNumbers = player.getCardNumbers();
        this.score = Integer.toString(player.sumCardNumber());
    }

    public static List<ResponsePlayerDTO> createResponsePlayerDTOs(Players players) {
        List<ResponsePlayerDTO> responsePlayerDTOS = new ArrayList<>();
        responsePlayerDTOS.add(new ResponsePlayerDTO(players.getDealer()));
        responsePlayerDTOS.addAll(players.getUsers().stream()
                .map(ResponsePlayerDTO::new)
                .collect(Collectors.toList()));
        return Collections.unmodifiableList(responsePlayerDTOS);
    }

    public static ResponsePlayerDTO create(Player player) {
        return new ResponsePlayerDTO(player);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getCardNumbers() {
        return this.cardNumbers;
    }

    public String getScore() {
        return this.score;
    }
}
