package blackjack.model;

import blackjack.model.participant.Hand;

import java.util.List;

import static blackjack.model.CardFixtures.*;

public class HandFixtures {

    public static Hand createPlayerBust() {
        return new Hand(List.of(HEART_JACK, HEART_FIVE, HEART_TEN));
    }

    public static Hand createPlayerBlackjack() {
        return new Hand(List.of(HEART_JACK, HEART_ACE));
    }

    public static Hand createPlayer19Score() {
        return new Hand(List.of(HEART_JACK, HEART_NINE));
    }

    public static Hand createDealerBust() {
        return new Hand(List.of(CLUB_NINE, CLUB_FIVE, CLUB_EIGHT));
    }

    public static Hand createDealerBlackjack() {
        return new Hand(List.of(CLUB_ACE, CLUB_JACK));
    }

    public static Hand createDealer8Score() {
        return new Hand(List.of(CLUB_FIVE, CLUB_THREE));
    }

    public static Hand createDealer17Score() {
        return new Hand(List.of(CLUB_NINE, CLUB_EIGHT));
    }

    public static Hand createDealer19Score() {
        return new Hand(List.of(CLUB_NINE, CLUB_JACK));
    }

    public static Hand createDealer20Score() {
        return new Hand(List.of(CLUB_TEN, CLUB_JACK));
    }

    // todo 어디까지 static이어야 하는가?, 그리고 어디까지 Fixture 여야 하는가?

}
