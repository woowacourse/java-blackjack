package domain.fixture;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.NINE_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;

import domain.card.Cards;
import domain.state.Blackjack;
import domain.state.Bust;
import domain.state.Stay;
import java.util.List;

public class StateFixture {

    public static Bust BUST_STATE = new Bust(new Cards(List.of(TEN_HEART(), NINE_HEART(), TEN_HEART())));
    public static Blackjack BLACKJACK_STATE = new Blackjack(new Cards(List.of(TEN_HEART(), ACE_HEART())));
    public static Stay STAY_STATE = new Stay(new Cards(List.of(TEN_HEART(), NINE_HEART())));
}
