package domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        validateDuplicate(names);
        List<Participant> players = names.stream()
                                         .map(Player::new)
                                         .collect(Collectors.toList());
        players.add(new Dealer());
        return new Participants(players);
    }

    private static void validateDuplicate(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복인 이름은 입력하실 수 없습니다.");
        }
    }

    public void addTwoCards(CardDeck cardDeck) {
        for (Participant player : participants) {
            List<Card> cards = cardDeck.pickTwice();
            player.addCards(cards);
        }
    }

    public Map<Participant, GameResult> getResult() {
        Map<Participant, GameResult> result = new HashMap<>();
        List<Participant> players = participants.stream()
                                                .filter(participant -> participant instanceof Player)
                                                .collect(Collectors.toList());
        for (Participant player : players) {
            result.put(player, getGameResult(player.getScore()));
        }
        return result;
    }

    private GameResult getGameResult(int score) {
        if (score > getDealerScore()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    private int getDealerScore() {
        Participant dealer = participants.stream()
                                         .filter(participant -> participant instanceof Dealer)
                                         .findAny()
                                         .orElseThrow(() -> new IllegalArgumentException("딜러를 " +
                                                 "찾지 못했습니다."));
        return dealer.getScore();
    }

    public List<Participant> toList() {
        return List.copyOf(participants);
    }
}
