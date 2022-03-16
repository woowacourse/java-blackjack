package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDistributor;
import blackjack.domain.card.Cards;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackjackGame {

    public static final String DEALER_NAME = "딜러";
    private static final int INIT_CARD_COUNT = 2;

    private final Participants participants;
    private final CardDistributor cardDistributor;

    public BlackjackGame(List<Name> names, List<BettingAmount> bettingAmounts, DeckGenerator deckGenerator) {
        cardDistributor = new CardDistributor(deckGenerator);
        Dealer dealer = new Dealer(new Name(DEALER_NAME), drawInitialCards());
        List<Player> players = initializePlayers(new ArrayList<>(names), new ArrayList<>(bettingAmounts));
        this.participants = new Participants(players, dealer);
    }

    private List<Player> initializePlayers(List<Name> names, List<BettingAmount> bettingAmounts) {
        if (names.size() != bettingAmounts.size()) {
            throw new IllegalArgumentException("TODO");
        }
        return IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), drawInitialCards(), bettingAmounts.get(i)))
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
        return new GameResult(participants.getPlayers(), participants.getDealer());
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
