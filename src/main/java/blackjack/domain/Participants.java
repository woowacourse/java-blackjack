package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int INITIAL_HAND_OUT_COUNT = 2;

    private final Dealer dealer;
    private final List<Player> players;

    private Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public static Participants of(List<String> playerNames) {
        validatePlayerNames(playerNames);
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Participants(players);
    }

    private static void validatePlayerNames(List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void handOut(Deck deck) {
        handOutToParticipant(dealer, deck.draw(INITIAL_HAND_OUT_COUNT));
        for (Player player : players) {
            handOutToParticipant(player, deck.draw(INITIAL_HAND_OUT_COUNT));
        }
    }

    private void handOutToParticipant(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.take(card);
        }
    }

    public List<Card> getDealerCards() {
        return new ArrayList<>(dealer.getCards());
    }

    public List<List<Card>> getPlayersCards() {
        return players.stream()
                .map(Player::getCards)
                .collect(Collectors.toList());
    }
}
