package blackjack.domain.user;

import blackjack.domain.Result;

public class Player extends User {

    public static final String CANNOT_DETERMINE_RESULT = "승패를 계산할 수 없습니다";

    public Player(String name) {
        super(name);
    }

    @Override
    public String showInitialCardInfo() {
        return super.showCardInfo();
    }
}
