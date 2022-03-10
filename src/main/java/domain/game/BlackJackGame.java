package domain.game;

import domain.card.Card;
import domain.card.CardDistributor;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final String DEALER_NAME = "딜러";

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
        cards.add(cardDistributor.distribute());
        cards.add(cardDistributor.distribute());
        return new Cards(cards);
    }

    public void drawPlayerCard(Participant participant) {
        int index = participants.indexOf(participant);
        Participant nowParticipant = participants.get(index);
        nowParticipant.drawCard(cardDistributor.distribute());
    }

    public List<Participant> getPlayers() {
        return participants.stream()
                .filter(participant -> participant instanceof Player)
                .collect(Collectors.toUnmodifiableList());
    }

    public Participant getDealer() {
        return participants.stream()
                .filter(participant -> participant instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 딜러가 존재하지 않습니다."));
    }
}
