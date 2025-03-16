package domain.user;

import domain.Card;
import domain.CardDeck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private static final int USER_MIN_COUNT = 1;
    private static final int USER_MAX_COUNT = 7;

    private final List<User> participants = new ArrayList<>();

    public Participants(List<User> users) {
        validate(users);
        participants.addAll(users);
    }

    private void validate(List<User> users) {
        long distinctUserCount = users.stream()
                .map(User::getName)
                .distinct()
                .count();

        if (distinctUserCount != users.size()) {
            throw new IllegalArgumentException("유저는 중복될 수 없습니다.");
        }
        if (users.isEmpty() || users.size() > USER_MAX_COUNT) {
            throw new IllegalArgumentException("유저는 " + USER_MIN_COUNT + "명 이상 " + USER_MAX_COUNT + "명 이하로 등록해야 합니다.");
        }
    }

    public void drawFirstCard(CardDeck cardDeck) {
        for (User participant : participants) {
            participant.drawCard(cardDeck.drawCard());
        }
    }

    public void drawCard(User user, Card card) {
        if (user.isDrawable()) {
            user.drawCard(card);
        }
    }

    public User getDealer() {
        return participants.stream()
                .filter(participant -> participant.getName().equals(Dealer.DEALER_NAME))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 게임에 참여하지 않았습니다."));
    }

    public List<User> getPlayers() {
        return participants.stream()
                .filter(participant -> !participant.getName().equals(Dealer.DEALER_NAME))
                .toList();
    }

    public Map<String, List<Card>> getPlayersAllCard() {
        Map<String, List<Card>> firstPlayersCard = new HashMap<>();
        List<User> players = this.getPlayers();
        for (User player : players) {
            List<Card> cards = player.openInitialCard();
            firstPlayersCard.put(player.getName(), cards);
        }
        return firstPlayersCard;
    }
}
