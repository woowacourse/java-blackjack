package domain.game;

import domain.betting.Revenue;
import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public GameManager(Players players) {
        this.dealer = new Dealer();
        this.players = players;
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void distributeInitialCards() {
        distributeInitialCards(dealer);
        distributeCardToPlayers(players);
    }

    private void distributeCardToPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            distributeInitialCards(player);
        }
    }

    private void distributeInitialCards(Participant participant) {
        drawCardTo(participant);
        drawCardTo(participant);
    }

    public void drawCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }

    public Map<String, GameResult> getGameResult() {
        return players.judgeResultsAgainst(dealer);
    }

    public Map<Name, Revenue> getParticipantsProfit() {
        Map<Name, Revenue> playerRevenues = players.calculateProfitsAgainst(dealer);
        Map<Name, Revenue> finalRevenues = new LinkedHashMap<>();
        finalRevenues.put(dealer.getName(), calculateDealerRevenue(playerRevenues));
        finalRevenues.putAll(playerRevenues);
        return finalRevenues;
    }

    private Revenue calculateDealerRevenue(Map<Name, Revenue> playerRevenues) {
        BigDecimal totalPlayerRevenue = playerRevenues.values().stream()
                .map(Revenue::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Revenue(totalPlayerRevenue.negate());
    }
}
