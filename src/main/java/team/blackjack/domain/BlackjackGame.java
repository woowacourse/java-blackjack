package team.blackjack.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.config.AppConfig;
import team.blackjack.domain.rule.BlackjackRule;
import team.blackjack.service.dto.PlayerRequest;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final BlackjackRule blackjackRule;
    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(List<PlayerRequest> playerRequests) {
        this.blackjackRule = AppConfig.getInstance().blackjackRule();
        this.dealer = new Dealer();
        this.players = createPlayers(playerRequests);
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
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
        return this.players.stream()
                .map(Player::getName)
                .toList();
    }

    public Map<String, List<String>> getAllPlayerCards() {
        final Map<String, List<String>> result = new LinkedHashMap<>();
        for (Player player : players) {
            result.put(player.getName(), getPlayerCardInAllHand(player));
        }

        return result;
    }

    public Map<String, Integer> getAllPlayerScores() {
        return this.players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getScore)
                );
    }

    public List<String> getDealerCards() {
        return dealer.getAllCards();
    }

    public int getDealerScore() {
        return this.dealer.getScore();
    }

    public Result getPlayerResult(Player player, Dealer dealer) {
        return blackjackRule.judgePlayerResult(player.getHands().getFirst(), dealer.getHand());
    }

    public Result getDealerResult(Dealer dealer, Player player) {
        return getPlayerResult(player, dealer).reverse();
    }

    public Map<String, BigDecimal> calculatePlayerPayout() {
        Map<String, BigDecimal> playerPayouts = new LinkedHashMap<>();

        Hand dealerHand = dealer.getHand();
        for (Player player : players) {
            Hand playerHand = player.getHands().getFirst();
            playerPayouts.put(player.getName(),
                    player.getPayout(blackjackRule.judgePlayerResult(playerHand, dealerHand)));
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
    private List<String> getPlayerCardInAllHand(Player player) {
        return player.getCardInAllHands();
    }

    private List<Player> createPlayers(List<PlayerRequest> playerRequests) {
        List<Player> players = new ArrayList<>();
        playerRequests.forEach(
                playerRequest -> players.add(
                        new Player(playerRequest.name(), playerRequest.stake())));

        return players;
    }
}
