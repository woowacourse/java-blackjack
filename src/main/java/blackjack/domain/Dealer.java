package blackjack.domain;

// TODO 기능을 위한 has a 관계로 바라보기
public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    // TODO이 메서드만 보고 어떤 의미를 나타내는지 파악이 힘듦
    public boolean draw(Deck deck) {
        if (shouldDraw()) {
            return addCard(deck.draw());
        }
        return false;
    }

    // TODO 예기치 못한 상황(카드가 없는 상황)에 대한 방어가 필요
    public String getFirstCardName() {
        return hands.getHands()
                .get(0)
                .getCardName();
    }

    private boolean shouldDraw() {
        return hands.getHandsScore() <= DEALER_DRAW_THRESHOLD;
    }
}
