package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final String DEALER_NAME = "딜러";
    private static final int FIRST_HIT_COUNT = 2;

    private final Participants participants;
    private final Cards cards;

    private BlackjackGame(Participants participants, Cards cards) {
        this.participants = participants;
        this.cards = cards;
    }

    public static BlackjackGame of(List<String> playerName, Cards cards) {
        return new BlackjackGame(Participants.from(playerName, new Dealer()), cards);
    }

    public Map<Player, WinningResult> generatePlayersResult(BlackJackReferee referee) {
        for (Player player : findPlayers()) {
           referee.createResult(findDealer(),player);
        }
        return referee.getPlayerWinningResult();
    }

    public void getTwoHitCards(final Participant participant) {
        for (int count = 0; count < FIRST_HIT_COUNT; count++) {
            participant.hit(cards.pick());
        }
    }

    public void hitDealerCard() {
        Dealer dealer = findDealer();
        while (dealer.decideHit()) {
            dealer.hit(cards.pick());
        }
    }

    public void hitPlayerCard(Player player) {
        player.hit(cards.pick());
    }

    public List<Player> findPlayers() {
        return getParticipants().stream()
                .filter(participant -> !participant.getParticipantName().equals(new ParticipantName(DEALER_NAME)))
                .map(it -> (Player) it)
                .collect(Collectors.toList());
    }

    public Dealer findDealer() {
        return (Dealer) getParticipants().stream()
            .filter(participant -> participant.getParticipantName().equals(new ParticipantName(DEALER_NAME)))
            .findFirst()
            .get();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public List<String> getParticipantsName() {
        return participants.getParticipantsName();
    }
}
