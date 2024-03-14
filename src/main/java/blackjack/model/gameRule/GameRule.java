package blackjack.model.gameRule;

import blackjack.model.card.Card;
import blackjack.model.deck.Deck;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import java.util.List;

public class GameRule {

    private static final GamePlayRule GAME_PLAY_RULE = new GamePlayRule();
    private static final GameResultRule GAME_RESULT_RULE = new GameResultRule();

    public static final int BUST_STANDARD_SCORE = 22;
    public static final int BLACKJACK_STANDARD_SCORE = 21;
    public static final int BLACKJACK_STANDARD_DECK_SIZE = 2;
    public static final int PLAYER_HIT_MAX_SCORE = 20;
    public static final int DEALER_HIT_MAX_SCORE = 16;

    private GameRule() {
    }

    public static void initialSetting(List<Player> players) {
        GAME_RESULT_RULE.initializePlayerResults(players);
    }

    public static void applyCardScoringRule(Deck deck) {
        GAME_PLAY_RULE.cardScoringRule(deck);
    }

    public static Card applyDealerInitialCardOpenRule(Dealer dealer) {
        return GAME_PLAY_RULE.dealerInitialCardOpenRule(dealer);
    }

    public static Boolean applyPlayerHitRule(Player player) {
        return GAME_PLAY_RULE.playerHitRule(player);
    }

    public static Boolean applyDealerHitRule(Dealer dealer) {
        return GAME_PLAY_RULE.dealerHitRule(dealer);
    }

    public static List<Player> applyHitStayTargetPlayerDecisionRule(List<Player> players) {
        return GAME_RESULT_RULE.hitStayTargetPlayerDecisionRule(players);
    }

    public static void applyInitialResultRule(Dealer dealer, List<Player> players) {
        GAME_RESULT_RULE.InitialResultRule(dealer, players);
    }

    public static void applyFinalResultRule(Dealer dealer, List<Player> players) {
        GAME_RESULT_RULE.finalResultRule(dealer, players);
    }

    public static void applyGameResultProfit(Dealer dealer, Player player) {
        GAME_RESULT_RULE.applyGameResultProfit(dealer, player);
    }
}
