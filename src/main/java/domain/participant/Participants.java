package domain.participant;

import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;

import domain.CardResult;
import domain.card.Hand;
import java.util.ArrayList;
import java.util.List;

public class Participants {

    public static final int MINIMUM_BOUND = 1;
    public static final int MAXIMUM_BOUND = 5;
    
    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_DRAW_BOUND = 16;

    private final Participant dealer;
    private final List<Participant> players;

    public Participants(final List<Participant> players) {
        validatePlayerCounts(players);

        dealer = new Participant(new Name(DEALER_NAME), new Hand(new ArrayList<>()));
        this.players = new ArrayList<>(players);
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return List.copyOf(players);
    }

    public List<CardResult> getCardResults() {
        final List<CardResult> cardResults = new ArrayList<>();
        cardResults.add(new CardResult(dealer.getName(), dealer.getHand(), dealer.getScore()));

        for (final Participant participant : players) {
            cardResults.add(new CardResult(participant.getName(), participant.getHand(), participant.getScore()));
        }
        return cardResults;
    }

    // TODO: 검증에 대한 테스트 필요
    private static void validatePlayerCounts(final List<Participant> participants) {
        if (participants.size() < MINIMUM_BOUND || participants.size() > MAXIMUM_BOUND) {
            throw new IllegalStateException(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }
}
