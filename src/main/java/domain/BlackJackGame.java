package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    public static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final Rule rule;

    public BlackJackGame(Deck deck, Dealer dealer, Rule rule) {
        validate(deck, dealer, rule);
        this.deck = deck;
        this.dealer = dealer;
        this.rule = rule;
    }

    private void validate(Deck deck, Dealer dealer, Rule rule) {
        validateNotNull(deck, dealer, rule);
    }

    private void validateNotNull(Deck deck, Dealer dealer, Rule rule) {
        if (deck == null || dealer == null || rule == null) {
            throw new IllegalArgumentException("블랙잭게임은 덱과 딜러와 룰을 가지고 있어야합니다.");
        }
    }

    public static BlackJackGame create() {
        Deck deck = new Deck(Arrays.asList(TrumpCard.values()), new DefaultShuffle());
        Dealer dealer = new Dealer(new Hand(deck.drawMultiple(INITIAL_CARD_COUNT)));
        Rule rule = new Rule();

        return new BlackJackGame(deck, dealer, rule);
    }

    public List<Player> createPlayers(Map<Name, BettingMoney> playerInfos) {
        return playerInfos.entrySet().stream()
                .map(entry -> new Player(
                        entry.getKey(), entry.getValue(),
                        new Hand(deck.drawMultiple(INITIAL_CARD_COUNT))))
                .toList();
    }

    public TrumpCard retrieveDealerFirstCard() {
        return dealer.retrieveFirstCard();
    }

    public boolean isPlayerHitAllowed(Player player) {
        return player.isHitAllowed(rule);
    }

    public void processPlayerHit(Player player) {
        if (!isPlayerHitAllowed(player)) {
            throw new IllegalStateException("플레이어는 더이상 히트할 수 없습니다.");
        }

        player.receiveCard(deck.draw());
    }

    public int processDealerHit() {
        int hitCount = 0;

        while (dealer.isHitAllowed(rule)) {
            dealer.receiveCard(deck.draw());
            hitCount++;
        }

        return hitCount;
    }

    public Score calculatePlayerScore(Player player) {
        return player.calculateScore(rule);
    }

    public List<TrumpCard> retrieveDealerCards() {
        return dealer.retrieveCards();
    }

    public Score calculateDealerScore() {
        return dealer.calculateScore(rule);
    }

    public Map<String, Integer> calculatePlayersRevenueAmount(List<Player> players) {
        return players.stream().collect(
                Collectors.toMap(Player::getName, player -> {
                    List<TrumpCard> playerCards = player.retrieveCards();
                    GameResult gameResult = dealer.calculateGameResult(rule, playerCards);
                    BettingMoney bettingMoney = player.getBettingMoney();

                    return bettingMoney.calculateRevenueAmount(gameResult.getMultiple());
                }));
    }
}
