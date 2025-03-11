package domain;

import domain.card.CardProvider;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.Participants;
import domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

    private static final int INITIAL_DRAW_SIZE = 2;
    private static final int DEFAULT_DRAW_SIZE = 1;

    private final CardProvider provider;
    private final Participants participants;

    public GameManager(List<String> playerNames, CardProvider provider) {
        this.provider = provider;
        this.participants = createParticipants(playerNames);
    }

    private Participants createParticipants(List<String> playerNames) {
        List<Participant> participants = playerNames.stream()
                .map(ParticipantName::new)
                .map(name -> new Player(name, new Cards(provider.provideCards(INITIAL_DRAW_SIZE))))
                .collect(Collectors.toList());
        participants.add(new Dealer(new Cards(provider.provideCards(INITIAL_DRAW_SIZE))));
        return new Participants(participants);
    }

    public void drawCard(Participant participant) {
        participant.drawCard(provider.provideCards(DEFAULT_DRAW_SIZE));
    }

    public boolean shouldPlayerHit(Participant player) {
        return !player.isBust();
    }

    public boolean shouldDealerHit() {
        Participant dealer = participants.findDealer();
        if (dealer.shouldHit()) {
            drawCard(dealer);
            return true;
        }
        return false;
    }

    public Map<Participant, ResultStatus> findGameResult() {
        return ResultStatus.judgeGameResult(participants);
    }

    public Participants findParticipants() {
        return participants;
    }
}
