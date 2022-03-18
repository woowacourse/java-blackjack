package blackjack.domain.game;

import blackjack.domain.card.CardDistributor;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.Map;

public class BlackjackGame {

    private static final int INIT_CARD_COUNT = 2;

    private final Participants participants;
    private final CardDistributor cardDistributor;

    public BlackjackGame(Map<Name, BettingAmount> participantInfos, DeckGenerator deckGenerator) {
        cardDistributor = new CardDistributor(deckGenerator);
        this.participants = new Participants(participantInfos);
    }

    public void initCardsAllParticipants() {
        initCards(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            initCards(player);
        }
    }

    private void initCards(Participant participant) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            participants.drawCard(participant, cardDistributor.distribute());
        }
    }

    public void drawCard(Participant participant) {
        participants.drawCard(participant, cardDistributor.distribute());
    }

    public GameResult createGameResult() {
        return new GameResult(participants);
    }

    public Participants getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "participants=" + participants +
                ", cardDistributor=" + cardDistributor +
                '}';
    }
}
