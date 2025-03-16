package domain;

import domain.card.CardProvider;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.ParticipantNames;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;
import java.util.Map;

public class GameManager {

    private static final int INITIAL_DRAW_SIZE = 2;
    private static final int DEFAULT_DRAW_SIZE = 1;

    private final CardProvider provider;
    private final Players players;
    private final Dealer dealer;

    public GameManager(ParticipantNames playerNames, Map<ParticipantName, BettingAmount> bettingAmounts, CardProvider provider) {
        this.provider = provider;
        this.players = createPlayers(playerNames, bettingAmounts);
        this.dealer = new Dealer(drawInitialCards());
    }

    private Players createPlayers(ParticipantNames playerNames, Map<ParticipantName, BettingAmount> bettingAmounts) {
        List<Player> players = playerNames.participantNames()
                .stream()
                .map(name -> {
                    Cards cards = drawInitialCards();
                    return new Player(name, bettingAmounts.get(name), cards);
                }).toList();
        return new Players(players);
    }

    private Cards drawInitialCards() {
        return new Cards(provider.provideCards(INITIAL_DRAW_SIZE));
    }

    public void drawCardForPlayer(Player player) {
        drawCard(player);
    }

    private void drawCard(Participant participant) {
        participant.drawCard(provider.provideCards(DEFAULT_DRAW_SIZE));
    }

    public boolean shouldDealerHit() {
        return dealer.shouldHit();
    }

    public void drawCardForDealer() {
        drawCard(dealer);
    }

    public Map<Player, Integer> findPlayersProfits() {
        return GameResult.calculateProfits(dealer, players);
    }

    public int findDealerProfit() {
        return GameResult.calculateDealerProfits(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
