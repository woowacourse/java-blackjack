package blackjack.fixture;

import blackjack.CardValue;
import blackjack.GamePlayer;
import blackjack.Name;
import java.util.List;

public class PlayerFixture {
    public static GamePlayer 플레이어_생성(List<CardValue> cardValues) {
        Name name = new Name("초롱");
        return new GamePlayer(name, CardFixture.카드_목록_생성(cardValues));
    }
}
