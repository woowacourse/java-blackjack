package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    public static final int DEALER_DRAW_BOUND = 16;

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand drawCardWhenStart(CardDeck cardDeck) {
        hand.drawCardWhenStart(cardDeck);
        return hand;
    }

    public Hand drawCard(CardDeck cardDeck) {
        hand.drawCard(cardDeck);
        return hand;
    }

    public Hand getHand() {
        return hand;
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalCardNumber();
    }

    public boolean isOverBurstBound() {
        int totalCardNumber = calculateTotalCardNumber();
        return hand.isOverBurstBound(totalCardNumber);
    }

    public boolean isOverDrawBound() {
        return calculateTotalCardNumber() > DEALER_DRAW_BOUND;
    }

    public int getCardsCount() {
        return hand.getCardsCount();
    }

    public List<String> judgeGameResult(List<Player> players) {
        List<String> gameResult = new ArrayList<>();
        for (Player player : players) {
            gameResult.add(judgeWin(player));
        }
        return gameResult;
    }

    private String judgeWin(Player player) {
        if (player.isOverBurstBound()) {
            return "패";
        }
        if (isOverBurstBound()) {
            return "승";
        }
        int playerTotalCardNumber = player.calculateTotalCardNumber();
        int dealerTotalCardNumber = calculateTotalCardNumber();
        if (playerTotalCardNumber < dealerTotalCardNumber) {
            return "패";
        }
        if (playerTotalCardNumber > dealerTotalCardNumber) {
            return "승";
        }
        if (playerTotalCardNumber == 21) {
            return judgeBlackJack(player, playerTotalCardNumber);
        }
        return "무";
    }

    private String judgeBlackJack(Player player, int playerTotalCardNumber) {
        int playerCardCount = player.getCardsCount();
        int dealerCardCount = getCardsCount();
        if (playerCardCount == dealerCardCount) {
            return "무";
        }
        if (playerCardCount == 2) {
            return "승";
        }
        if (dealerCardCount == 2) {
            return "패";
        }
        throw new IllegalArgumentException("[ERROR] 승패 판정에 실패하였습니다.");
    }
}
