package blackjack.model.card;

import blackjack.model.game.CardGroup;

public enum CardType {
    NORMAL_2(2, "2", CardGroup.NORMAL),
    NORMAL_3(3, "3", CardGroup.NORMAL),
    NORMAL_4(4, "4", CardGroup.NORMAL),
    NORMAL_5(5, "5", CardGroup.NORMAL),
    NORMAL_6(6, "6", CardGroup.NORMAL),
    NORMAL_7(7, "7", CardGroup.NORMAL),
    NORMAL_8(8, "8", CardGroup.NORMAL),
    NORMAL_9(9, "9", CardGroup.NORMAL),
    NORMAL_10(10, "10", CardGroup.NORMAL),
    ACE(1, "A", CardGroup.ACE),
    KING(10, "K", CardGroup.SPECIAL),
    QUEEN(10, "Q", CardGroup.SPECIAL),
    JACK(10, "J", CardGroup.SPECIAL),
    ;

    private final int point;
    private final String detail;
    private final CardGroup cardGroup;

    CardType(int point, String detail, CardGroup cardGroup) {
        this.point = point;
        this.detail = detail;
        this.cardGroup = cardGroup;
    }

    public int getPoint() {
        return point;
    }

    public String getDetail() {
        return detail;
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }
}
