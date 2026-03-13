package domain;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.enums.MatchCase;

public class Game {
    public static final int ADDITIONAL_THRESHOLD = 16;
    public static final int BLACKJACK_VALUE = 21;
    public static final double BLACKJACK_BONUS = 1.5;
    public static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final Player dealer;
    private final Players players;

    public Game(Deck deck, Players players, Player dealer) {
        this.deck = deck;
        this.players = players;
        this.dealer = dealer;
    }

    public Map<MatchCase, Integer> calculateDealerMatch(Map<String, MatchCase> playerResult) {
        Map<MatchCase, Integer> dealerMatchResult = new LinkedHashMap<>();
        for (MatchCase matchCase : playerResult.values()) {
            dealerMatchResult.put(matchCase, dealerMatchResult.getOrDefault(matchCase, 0) + 1);
        }
        return dealerMatchResult;
    }

    public Map<String, MatchCase> calculateMatch() {
        Map<String, MatchCase> matchResult = new LinkedHashMap<>();
        // 1. 참가자들이 모두 burst면 딜러가 승리한다.
        if (players.isAllPlayerBurst()) {
            return getPlayersAllBurstCase(matchResult);
        }
        // 2. 딜러가 burst이면 살아남은 참가자는 우승이다.
        if (dealer.isBust()) {
            return getDealerBurstCase(matchResult);
        }
        // 3. 딜러가 burst가 아니면, 딜러보다 크면 승, 작으면 패, 같은면 무승부이다.
        return getGeneralCase(matchResult);
    }

    private Map<String, MatchCase> getGeneralCase(Map<String, MatchCase> matchResult) {
        for (Player player : players) {
            MatchCase matchCase = player.calculateMatchCase(dealer.getCardsTotalSum());
            matchResult.put(player.getName(), matchCase);
            player.calculateMoney(matchCase, dealer.isDealerBlackjack());
        }
        return matchResult;
    }

    private Map<String, MatchCase> getDealerBurstCase(Map<String, MatchCase> matchResult) {
        for (Player player : players) {
            if (!player.isBust()) {
                matchResult.put(player.getName(), MatchCase.WIN);
                player.calculateMoney(MatchCase.WIN, dealer.isDealerBlackjack());
                continue;
            }
            matchResult.put(player.getName(), MatchCase.LOSE);
            player.calculateMoney(MatchCase.LOSE, dealer.isDealerBlackjack());
        }
        return matchResult;
    }

    private Map<String, MatchCase> getPlayersAllBurstCase(Map<String, MatchCase> matchResult) {
        for (Player player : players) {
            matchResult.put(player.getName(), MatchCase.LOSE);
            player.calculateMoney(MatchCase.LOSE, dealer.isDealerBlackjack());
        }
        return matchResult;
    }

    public Map<String, Integer> getBettingScore(Game game) {
        Map<String, Integer> bettingResult = new LinkedHashMap<>();
        for (Player player : players) {
            bettingResult.put(player.getName(), player.getBettingScore());
        }
        return bettingResult;
    }

    public boolean needAdditionalCard() {
        return dealer.getCardsTotalSum() <= ADDITIONAL_THRESHOLD;
    }

    public boolean isAllPlayerBurst() {
        return players.isAllPlayerBurst();
    }

    public void addCard(Player player) {
        players.addAdditionalCard(player, deck.pop());
    }

    public void addDealerAdditionalCard() {
        dealer.add(deck.pop());
    }

    public Card getDealerFirstCard() {
        return dealer.getCards().getFirst();
    }

    public int getTotalMoney() {
        return players.getTotalBettingScore();
    }

    public Players getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }

}
