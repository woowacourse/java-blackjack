package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {

    private static final int DEALER_POSITION = 0;

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants create(List<String> playerNames) {
        validateNotEmptyPlayers(playerNames);
        List<Participant> participants = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        participants.add(DEALER_POSITION, new Dealer());
        return new Participants(participants);
    }

    private static void validateNotEmptyPlayers(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public void readyForGame(Deck deck) {
        participants.forEach(participant -> {
            participant.receiveCard(deck.draw());
            participant.receiveCard(deck.draw());
        });
    }

    public boolean hasDrawablePlayer() {
        return players().stream()
                .anyMatch(Participant::isDrawable);
    }

    public String nextDrawablePlayerName() {
        return findNextDrawablePlayer()
                .name();
    }

    public void handOutCardToPlayer(Card card) {
        findNextDrawablePlayer()
                .receiveCard(card);
    }

    public void standCurrentPlayer() {
        Player nextPlayer = (Player) findNextDrawablePlayer();
        nextPlayer.stand();
    }

    private Participant findNextDrawablePlayer() {
        return players().stream()
                .filter(Participant::isDrawable)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("카드를 받을 수 있는 플레이어가 없습니다."));
    }

    private List<Participant> players() {
        int firstPlayerPosition = DEALER_POSITION + 1;
        int numberOfParticipants = participants.size();

        return participants.subList(firstPlayerPosition, numberOfParticipants);
    }

    public boolean isDealerDrawable() {
        return dealer().isDrawable();
    }

    public void handOutCardToDealer(Card card) {
        dealer().receiveCard(card);
    }

    public int getDealerScore() {
        return dealer().score();
    }

    private Participant dealer() {
        return participants.get(DEALER_POSITION);
    }

    public Map<String, Integer> getPlayerScores() {
        return players().stream()
                .collect(Collectors.toMap(Participant::name, Participant::score, (d1, d2) -> d1, LinkedHashMap::new));
    }
}
