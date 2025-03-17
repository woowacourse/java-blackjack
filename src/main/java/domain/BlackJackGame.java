package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Map<Name, Player> players;

    public BlackJackGame(Deck deck, Dealer dealer, Map<Name, Player> players) {
        validate(deck, dealer, players);
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    private void validate(Deck deck, Dealer dealer, Map<Name, Player> players) {
        validateNotNull(deck, dealer, players);
    }

    private void validateNotNull(Deck deck, Dealer dealer, Map<Name, Player> players) {
        if (deck == null || dealer == null || players == null) {
            throw new IllegalArgumentException("블랙잭게임은 덱과 딜러와 룰과 플레이어들을 가지고 있어야합니다.");
        }
    }

    public static BlackJackGame create(Map<Name, BettingMoney> playerInfos) {
        Deck deck = new Deck(
                Arrays.asList(TrumpCard.values()), new DefaultShuffle());
        Dealer dealer = new Dealer(
                Started.of(new Hand(deck.drawMultiple(Started.INITIAL_CARD_COUNT)), Score.SEVENTEEN));
        Map<Name, Player> players = playerInfos.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new Player(entry.getKey(), entry.getValue(),
                                Started.of(
                                        new Hand(deck.drawMultiple(Started.INITIAL_CARD_COUNT)), Score.TWENTY_ONE))));

        return new BlackJackGame(deck, dealer, players);
    }

    public List<TrumpCard> retrievePlayerCards(Name playerName) {
        validateContain(playerName);

        return players.get(playerName).retrieveCards();
    }

    public TrumpCard retrieveDealerFirstCard() {
        return dealer.retrieveFirstCard();
    }

    public boolean isPlayerHitAllowed(Name playerName) {
        validateContain(playerName);

        return players.get(playerName).isHitAllowed();
    }

    public void processPlayerHit(Name playerName) {
        if (!isPlayerHitAllowed(playerName)) {
            throw new IllegalStateException("플레이어는 더이상 히트할 수 없습니다.");
        }

        players.get(playerName).receiveCard(deck.draw());
    }

    public int processDealerHit() {
        int hitCount = 0;

        while (dealer.isHitAllowed()) {
            dealer.receiveCard(deck.draw());
            hitCount++;
        }

        return hitCount;
    }

    public Score calculatePlayerScore(Name playerName) {
        validateContain(playerName);

        return players.get(playerName).calculateScore();
    }

    public List<TrumpCard> retrieveDealerCards() {
        return dealer.retrieveCards();
    }

    public Score calculateDealerScore() {
        return dealer.calculateScore();
    }

    public Integer calculatePlayerRevenueAmount(Name playerName) {
        validateContain(playerName);
        Player player = players.get(playerName);
        
        Score playerScore = player.calculateScore();
        Score dealerScore = dealer.calculateScore();
        GameResult gameResult = GameResult.of(playerScore, dealerScore);

        BettingMoney bettingMoney = player.getBettingMoney();

        return bettingMoney.calculateRevenueAmount(gameResult.getMultiple());
    }

    private void validateContain(Name playerName) {
        if (!players.containsKey(playerName)) {
            throw new IllegalArgumentException("플레이어가 존재하지 않습니다.");
        }
    }
}
