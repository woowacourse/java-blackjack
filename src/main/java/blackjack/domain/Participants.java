package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private final List<Playable> participants;

    public Participants(Players players, Dealer dealer) {
        participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.values());
    }

    public String names() {
        return participants.stream().filter(playable -> playable instanceof Player).map(Playable::getName).collect(Collectors.joining(", "));
    }

    public List<String> cards() {
        List<String> results = new ArrayList<>();
        for (Playable playable : participants) {
            results.add(playable.getName() + cardsToString(playable.getUnmodifiableCards()));
        }
        return results;
    }

    public List<String> result() {
        List<String> results = new ArrayList<>();
        for (Playable playable : participants) {
            results.add(playable.getName() + cardsToString(playable.getUnmodifiableCards()) + " - 결과: " + playable.sumCardsForResult());
        }
        return results;
    }

    private String cardsToString(List<Card> cards) {
        return cards.stream().map(Card::getName).collect(Collectors.joining(", ", "카드: ", ""));
    }

    public Map<String, String> finalResult() {
        Playable dealer = participants.stream().filter(playable -> playable instanceof Dealer).findFirst().orElse(null);
        List<Playable> players = participants.stream().filter(playable -> playable instanceof Player).collect(Collectors.toList());
        final Map<String, String> results = new LinkedHashMap<>();
        int win = 0;
        int lose = 0;
        int draw = 0;
        for (Playable player : players) {
            if (dealer.result(player.sumCardsForResult()) == 1) {
                win += 1;
            }
            if (dealer.result(player.sumCardsForResult()) == -1) {
                lose += 1;
            }
            if (dealer.result(player.sumCardsForResult()) == 0) {
                draw += 1;
            }
        }
        results.put(dealer.getName(), win + "승 " + lose + "패 " + draw + "무");
        for (Playable player : players) {
            if (player.result(dealer.sumCardsForResult()) == 1) {
                results.put(player.getName(), "승");
            }
            if (player.result(dealer.sumCardsForResult()) == -1) {
                results.put(player.getName(), "패");
            }
            if (player.result(dealer.sumCardsForResult()) == 0) {
                results.put(player.getName(), "무");
            }
        }
        return results;
    }
}
