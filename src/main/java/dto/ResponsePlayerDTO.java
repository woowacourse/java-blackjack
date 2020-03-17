package dto;

import domain.player.User;
import domain.player.Users;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResponsePlayerDTO {
    private String name;
    private List<String> cardNumbers;
    private String score;

    private ResponsePlayerDTO(User user) {
        this.name = user.getName();
        this.cardNumbers = user.getCardNumbers();
        this.score = Integer.toString(user.sumCardNumber());
    }

    public static List<ResponsePlayerDTO> createResponsePlayerDTOs(Users users) {
        List<ResponsePlayerDTO> responsePlayerDTOS = users.getUsers().stream()
                .map(ResponsePlayerDTO::new)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(responsePlayerDTOS);
    }

    public static ResponsePlayerDTO create(User user) {
        return new ResponsePlayerDTO(user);
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
