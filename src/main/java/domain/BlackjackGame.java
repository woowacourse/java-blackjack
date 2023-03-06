package domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int DEALER_POSITION = 0;

    private Players players;
    private Dealer dealer;
    private Deck deck;

    public void setParticipants(List<String> playerNames) {
        players = Players.from(playerNames);
        dealer = new Dealer();
    }

    public void handOutInitialCards(ShuffleStrategy shuffleStrategy) {
        Objects.requireNonNull(players, "플레이어가 없는 상태에서 카드를 나눠줄 수 없습니다.");

        deck = Deck.create();
        deck.shuffle(shuffleStrategy);

        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        players.receiveCard(deck);
        players.receiveCard(deck);
    }

    public boolean hasDrawablePlayer() {
        return players.hasDrawablePlayer();
    }

    public Player getCurrentDrawablePlayer() {
        return players.getCurrentDrawablePlayer();
    }

    public void hitOrStand(Decision decision) {
        if (decision == Decision.HIT) {
            players.handOutCardToCurrentPlayer(deck.draw());
            return;
        }
        players.standCurrentPlayer();
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void handOutCardToDealer() {
        dealer.receiveCard(deck.draw());
    }

    public Map<String, GameOutcome> getPlayersOutcome() {
        return players.battleWith(dealer);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = players.getPlayers()
                .stream()
                .map(player -> (Participant) player)
                .collect(Collectors.toList());
        participants.add(DEALER_POSITION, dealer);
        return participants;
    }
}
