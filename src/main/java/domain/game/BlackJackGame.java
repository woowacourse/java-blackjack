package domain.game;

import domain.card.Card;
import domain.card.CardDistributor;
import domain.card.Cards;
import domain.participant.Command;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    public static final String DEALER_NAME = "딜러";
    private static final String NOT_EXIST_DEALER_ERROR = "딜러가 존재하지 않습니다.";
    private static final int INIT_CARD_COUNT = 2;

    private final List<Participant> participants = new ArrayList<>();
    private final CardDistributor cardDistributor = new CardDistributor();

    public BlackJackGame(List<Name> names) {
        this.participants.add(new Dealer(new Name(DEALER_NAME), drawInitialCards()));
        this.participants.addAll(initializePlayers(new ArrayList<>(names)));
    }

    private List<Player> initializePlayers(List<Name> names) {
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
        int index = participants.indexOf(participant);
        Participant nowParticipant = participants.get(index);
        nowParticipant.drawCard(cardDistributor.distribute());
    }

    public GameResult createGameResult() {
        return new GameResult(findPlayers(), findDealer());
    }

    public List<Participant> findPlayers() {
        return participants.stream()
                .filter(participant -> participant instanceof Player)
                .collect(Collectors.toUnmodifiableList());
    }

    public Participant findDealer() {
        return participants.stream()
                .filter(participant -> participant instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_DEALER_ERROR));
    }

    @Override
    public String toString() {
        return "BlackJackGame{" +
                "participants=" + participants +
                ", cardDistributor=" + cardDistributor +
                '}';
    }
}
