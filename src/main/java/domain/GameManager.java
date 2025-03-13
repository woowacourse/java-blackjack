package domain;

import domain.card.CardProvider;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.ParticipantNames;
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

    public GameManager(ParticipantNames playerNames, Map<ParticipantName, BettingAmount> bettingAmounts, CardProvider provider) {
        this.provider = provider;
        this.participants = createParticipants(playerNames, bettingAmounts);
    }

    private Participants createParticipants(ParticipantNames playerNames, Map<ParticipantName, BettingAmount> bettingAmounts) {
        List<Participant> participants = initPlayers(playerNames, bettingAmounts);
        initDealer(participants);
        return new Participants(participants);
    }

    private List<Participant> initPlayers(ParticipantNames playerNames, Map<ParticipantName, BettingAmount> bettingAmounts) {
        return playerNames.getParticipantNames()
                .stream()
                .map(name -> {
                    Cards cards = drawInitialCards();
                    return new Player(name, bettingAmounts.get(name), cards);
                }).collect(Collectors.toList());
    }

    private void initDealer(List<Participant> participants) {
        participants.add(new Dealer(drawInitialCards()));
    }

    private Cards drawInitialCards() {
        return new Cards(provider.provideCards(INITIAL_DRAW_SIZE));
    }

    public boolean shouldPlayerHit(Participant player) {
        return player.shouldHit();
    }

    public void drawCardForPlayer(Participant player, boolean answer) {
        if (answer) {
            drawCard(player);
        }
    }

    private void drawCard(Participant player) {
        player.drawCard(provider.provideCards(DEFAULT_DRAW_SIZE));
    }

    public boolean shouldDealerHit() {
        Participant dealer = participants.findDealer();
        if (dealer.shouldHit()) {
            drawCard(dealer);
            return true;
        }
        return false;
    }

    public Map<Participant, Integer> findPlayersProfits() {
        return GameResult.calculateProfits(participants);
    }

    public int findDealerProfit() {
        return GameResult.calculateDealerProfits(participants);
    }

    public Participants findParticipants() {
        return participants;
    }
}
