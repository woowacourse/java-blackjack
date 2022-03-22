package blackjack.domain;

import blackjack.domain.betting.BettingPlayer;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.result.PlayerProfit;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final int CHANGE_TO_NEGATIVE = -1;

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame create(List<Participant> players) {
        return new BlackjackGame(Participants.from(players), Deck.createShuffledCards());
    }

    public void drawBaseCards() {
        participants.drawBaseCards(deck);
    }

    public void takeMoreCard(Participant participant) {
        if (participant.shouldReceive()) {
            participant.hit(deck.draw());
        }
    }

    public List<PlayerProfit> calculatePlayerProfit(List<BettingPlayer> bettingPlayers) {
        return bettingPlayers.stream()
            .map(player -> new PlayerProfit(player.getName(), player.calculateProfit(
                participants.getDealer())))
            .collect(Collectors.toList());
    }

    public int calculateDealerProfit(List<BettingPlayer> bettingPlayers) {
        return calculatePlayerProfit(bettingPlayers).stream()
            .mapToInt(player -> player.getProfit() * CHANGE_TO_NEGATIVE)
            .sum();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    public Participant getDealer() {
        return participants.getDealer();
    }
}
