package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDistributor;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    public static final String DEALER_NAME = "딜러";
    private static final String NOT_EXIST_DEALER_ERROR = "딜러가 존재하지 않습니다.";

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
