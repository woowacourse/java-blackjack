package team.blackjack.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.config.AppConfig;
import team.blackjack.domain.rule.BlackjackRule;
import team.blackjack.registry.ParticipantRegistry;
import team.blackjack.service.dto.PlayerRequest;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final ParticipantRegistry participantRegistry;
    private final Deck deck;

    public BlackjackGame(List<PlayerRequest> playerRequests) {
        this.participantRegistry = AppConfig.getInstance().participantRegistry();
        this.deck = new Deck();

        createPlayers(playerRequests);
    }

    public Dealer getDealer() {
        return participantRegistry.getDealer();
    }

    public List<Player> getPlayers() {
        return participantRegistry.getPlayers();
    }

    public void dealInitialCardsTo(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participant.hit(deck.draw());
        }
    }

    public void dealCardTo(Participant participant) {
        participant.hit(deck.draw());
    }

    public List<String> getPlayerNames() {
        return participantRegistry.getPlayerNames();
    }

    public Map<String, List<String>> getAllPlayerCards() {
        final Map<String, List<String>> result = new LinkedHashMap<>();

        List<Player> players = participantRegistry.getPlayers();
        for (Player player : players) {
            result.put(player.getName(), getPlayerCardInAllHand(player));
        }

        return result;
    }

    public Map<String, Integer> getAllPlayerScores() {
        List<Player> players = participantRegistry.getPlayers();

        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getScore)
                );
    }

    public List<String> getDealerCards() {
        return participantRegistry.getDealer().getAllCards();
    }

    public int getDealerScore() {
        return participantRegistry.getDealer().getScore();
    }

    public Map<String, BigDecimal> calculatePlayersPayout(BlackjackRule blackjackRule) {
        Map<String, BigDecimal> playerPayouts = new LinkedHashMap<>();

        Hand dealerHand = participantRegistry.getDealer().getHand();
        List<Player> players = participantRegistry.getPlayers();

        for (Player player : players) {
            playerPayouts.put(player.getName(), calculatePlayerPayout(player, dealerHand, blackjackRule));
        }

        return playerPayouts;
    }

    public BigDecimal calculateDealerPayout(Map<String, BigDecimal> playerPayouts) {
        BigDecimal sum = playerPayouts.values()
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.negate();
    }

    /**
     * 헬퍼 메소드
     */
    private void createPlayers(List<PlayerRequest> playerRequests) {
        List<Player> players = new ArrayList<>();
        playerRequests.forEach(
                playerRequest -> players.add(
                        new Player(playerRequest.name(), playerRequest.stake())));

        participantRegistry.savePlayers(players);
    }

    private List<String> getPlayerCardInAllHand(Player player) {
        return player.getCardInAllHands();
    }

    private BigDecimal calculatePlayerPayout(Player player, Hand dealerHand, BlackjackRule blackjackRule) {
        Hand playerHand = player.getHands().getFirst();
        return player.getPayout(blackjackRule.judgePlayerResult(playerHand, dealerHand));
    }
}
