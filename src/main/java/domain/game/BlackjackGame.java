package domain.game;

import domain.card.Card;
import domain.card.CardDistributor;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    public static final String DEALER_NAME = "딜러";
    private static final int INIT_CARD_COUNT = 2;

    private final Participants participants;
    private final CardDistributor cardDistributor = new CardDistributor();

    public BlackjackGame(List<Name> names) {
        this.participants = new Participants(initializePlayers(new ArrayList<>(names)));
        this.participants.add(new Dealer(new Name(DEALER_NAME), drawInitialCards()));
    }

    private List<Participant> initializePlayers(List<Name> names) {
        return names.stream()
                .map(name -> new Player(name, drawInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            cards.add(cardDistributor.distribute());
        }
        return new Cards(cards);
    }

    public void drawCard(Participant participant) {
        participants.drawCard(participant, cardDistributor.distribute());
    }

    public GameResult createGameResult() {
        return new GameResult(participants.findPlayers(), participants.findDealer());
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
