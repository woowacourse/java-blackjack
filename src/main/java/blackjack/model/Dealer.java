package blackjack.model;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int PICK_THRESHOLD = 16;

    private Dealer(Hands hands) {
        super(DEALER_NAME, hands);
    }

    public static Dealer create() {
        return new Dealer(Hands.empty());
    }

    // 딜러가 초기 2장의 카드를 받는다.
    @Override
    public void pickInitCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());

        Card secondPickedCard = cardDeck.pick();
        secondPickedCard.flip();
        hands.addCard(secondPickedCard);
    }

    // 16점을 초과하면 false를 반환한다.
    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }

    // 플레이어와 딜러의 숫자를 비교한다.
    public Result compare(Player player) {
        // 플레이어가 버스트되면 무조건 딜러 승
        if (player.isBust()) {
            return Result.WIN;
        }

        // 플레이어가 버스트 되지 않고, 딜러가 버스트 되면 플레이어 승
        if (this.isBust()) {
            return Result.LOSE;
        }

        // 딜러의 점수가 더 높으면 승
        if (this.hands.isTotalScoreOver(player.getCurrentTotalScore())) {
            return Result.WIN;
        }

        // 플레이어 점수가 더 높으면 패
        if (player.getCurrentTotalScore() > this.hands.calculateTotalScore()) {
            return Result.LOSE;
        }

        // 무승부
        return Result.DRAW;
    }
}
