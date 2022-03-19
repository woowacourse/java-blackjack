package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDistributor;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import java.util.Map;
import java.util.function.Supplier;

public class BlackjackGame {

    public static final int INIT_CARD_COUNT = 2;

    private final Participants participants;
    private final CardDistributor cardDistributor;

    public BlackjackGame(Map<Name, BettingAmount> participantInfos, DeckGenerator deckGenerator) {
        cardDistributor = new CardDistributor(deckGenerator);
        this.participants = new Participants(participantInfos);
    }

    public void initCardsAllParticipants() {
        Supplier<Card> supplier = cardDistributor::distribute;
        participants.initCardsAllParticipants(supplier);
    }

    public void drawCard(Participant participant) {
        participant.drawCard(cardDistributor.distribute());
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
