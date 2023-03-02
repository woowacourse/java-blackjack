package model;

import model.card.Card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;
    private static final int CAN_RECEIVE_DEALER_MAX_NUMBER = 16;

    private final Map<User, List<Card>> scoreBoards;

    public Result(final Users users) {
        this.scoreBoards = createScoreBoards(users);
    }

    private Map<User, List<Card>> createScoreBoards(final Users users) {
        Map<User, List<Card>> scoreBoards = new LinkedHashMap<>();
        users.getUsers().forEach(player -> {
            List<Card> cards = new ArrayList<>();
            scoreBoards.put(player, cards);
        });
        return scoreBoards;
    }

    public void addCard(final User user, final Card card) {
        scoreBoards.get(user).add(card);
    }

    public boolean canPlayerReceiveCard(final User user) {
        return CAN_RECEIVE_MAX_NUMBER >= scoreBoards.get(user).stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public boolean canDealerReceiveCard() {
        return CAN_RECEIVE_DEALER_MAX_NUMBER >= scoreBoards.get(new Dealer("딜러")).stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public Map<User, List<Card>> getScoreBoards() {
        return scoreBoards;
    }
}
