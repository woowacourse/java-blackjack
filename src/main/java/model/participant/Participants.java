package model.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Participants {
    private static final String dealerName = "딜러";

    private final List<Participant> values;

    private Participants(List<Participant> values) {
        this.values = List.copyOf(values);
    }

    public static Participants from(List<String> names) {
        validatePlayerNames(names);

        List<Participant> participants = new ArrayList<>();
        participants.add(Dealer.from(dealerName));

        List<Player> players = names.stream()
                .map(Player::from)
                .toList();

        participants.addAll(players);

        return new Participants(participants);
    }

    private static void validatePlayerNames(List<String> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }

        if (names.contains(dealerName)) {
            throw new IllegalArgumentException("플레이어는 '" + dealerName + "'라는 이름을 사용할 수 없습니다.");
        }
    }

    public Dealer getDealer() {
        return (Dealer) values.getFirst();
    }

    public List<Player> getPlayers() {
        return values.stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .toList();
    }

    public List<Participant> asList() {
        return List.copyOf(values);
    }

    @Override
    public String toString() {
        return "Participants{" +
                "values=" + values +
                '}';
    }
}
