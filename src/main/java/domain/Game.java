package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Game {
    public static final int ADDITIONAL_THRESHOLD = 16;
    public static final int BLACKJACK_VALUE = 21;
    public static final int CARD_COUNT = 2;
    public static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final Player dealer;
    private final Players players;

    public Game(Deck deck, Players players) {
        this.deck = deck;
        this.players = players;
        this.dealer = new Player(DEALER_NAME);
        dealer.addInitializedCard(deck);
    }

    public Map<MatchCase, Integer> calculateDealerMatch(Map<String, MatchCase> playerResult){
        Map<MatchCase, Integer> dealerMatchResult = new LinkedHashMap<>();
        for (MatchCase matchCase : playerResult.values()){
            dealerMatchResult.put(matchCase, dealerMatchResult.getOrDefault(matchCase,0)+1);
        }
        return dealerMatchResult;
    }

    // 승/패 계산
    public Map<String, MatchCase> calculateMatch(){
        Map<String, MatchCase> matchResult = new LinkedHashMap<>();

        // 1. 참가자들이 모두 burst면 딜러가 승리한다.
        if (players.isAllPlayerBurst()){
            for (Player player : players){
                matchResult.put(player.getName(), MatchCase.LOSE);
                player.calculateMoney(MatchCase.LOSE,  dealer.isDealerBlackjack());
            }
            return matchResult;
        }

        // 2. 딜러가 burst이면 살아남은 참가자는 우승이다.
        if (dealer.isBust()){
            for (Player player : players){
                if (!player.isBust()){
                    matchResult.put(player.getName(), MatchCase.WIN);
                    player.calculateMoney(MatchCase.WIN,  dealer.isDealerBlackjack());
                    continue;
                }
                matchResult.put(player.getName(), MatchCase.LOSE);
                player.calculateMoney(MatchCase.LOSE,  dealer.isDealerBlackjack());

            }
            return matchResult;
        }

        // 딜러가 burst가 아니면, 딜러보다 크면 승, 작으면 패, 같은면 무승부이다.
        for  (Player player : players){
            MatchCase matchCase = player.calculateMatchCase(dealer.getCardsTotalSum());
            matchResult.put(player.getName(), matchCase);
            player.calculateMoney(matchCase, dealer.isDealerBlackjack());
        }
        return matchResult;
    }


    public Map<String, Integer> getBettingScore(Game game) {
        Map<String, Integer> bettingResult = new LinkedHashMap<>();
        for(Player player : players){
            bettingResult.put(player.getName(), player.getBettingScore());
        }
        return bettingResult;
    }

    public int getTotalMoney(){
        return players.getTotalBettingScore();
    }

//    private static boolean isPlayerLose(Player player, boolean dealerBurst, int dealerTotal) {
//        return player.isBust() || (!dealerBurst && player.getFinalScore() < dealerTotal);
//    }
//
//    public void updateBettingScore(int money) {
//        betMoney(-money);
//    }
//
    public boolean needAdditionalCard() {
        return dealer.getCardsTotalSum() <= ADDITIONAL_THRESHOLD;
    }

    public boolean isAllPlayerBurst() {
        return players.isAllPlayerBurst();
    }

    public void addCard(Player player) {
        players.addAdditionalCard(player,deck.pop());
    }

    public Card getDealerFirstCard() {
        return dealer.getCards().getFirst();
    }

    public void addDealerAdditionalCard() {
        dealer.add(deck.pop());
    }

    public Players getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }

}
