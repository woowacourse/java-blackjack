package blackjack.domain.game;

import blackjack.domain.card.CardDistributor;
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

    private static final String ERROR_LIST_SIZE = "플레이어 개수가 올바르지 않습니다.";
    public static final String DEALER_NAME = "딜러";
    private static final int INIT_CARD_COUNT = 2;

    private final Participants participants;
    private final CardDistributor cardDistributor;

    public BlackjackGame(List<Name> names, List<BettingAmount> bettingAmounts, DeckGenerator deckGenerator) {
        cardDistributor = new CardDistributor(deckGenerator);

        Dealer dealer = new Dealer(new Name(DEALER_NAME));
        List<Player> players = initializePlayers(new ArrayList<>(names), new ArrayList<>(bettingAmounts));
        this.participants = new Participants(players, dealer);
    }

    private List<Player> initializePlayers(List<Name> names, List<BettingAmount> bettingAmounts) {
        if (names.size() != bettingAmounts.size()) {
            throw new IllegalArgumentException(ERROR_LIST_SIZE);
        }
        return IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), bettingAmounts.get(i)))
                .collect(Collectors.toUnmodifiableList());
    }

    public void initCardsAllParticipants() {
        initCards(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            initCards(player);
        }
    }

    private void initCards(Participant participant) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            drawCard(participant);
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
