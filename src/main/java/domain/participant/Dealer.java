package domain.participant;

import domain.constants.CardCommand;

public class Dealer extends Participant {

    // TODO: 뷰에서 출력을 위해 public으로 변경하였다. 괜찮은가? 직접 타이핑하는 것보단 낫지 않은가 ...

    public static final String DEALER_NAME = "딜러";
    private static final int CARD_PICK_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    // TODO: 사실 상 이 친구는 매개변수를 사용하지 않는다. 그러나 추상 클래스의 다형성을 위해 사용하였따. 다른 방법이 있는가?
    @Override
    public boolean canPickCard(CardCommand cardCommand) {
        return calculateScore() > CARD_PICK_THRESHOLD;
    }
}
