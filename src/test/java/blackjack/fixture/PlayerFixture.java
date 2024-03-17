package blackjack.fixture;

import blackjack.domain.player.BettingAmount;
import blackjack.domain.card.CardValue;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Name;
import java.util.List;

public class PlayerFixture {
    public static GamePlayer 게임_플레이어_생성(List<CardValue> cardValues) {
        Name name = new Name("초롱");
        BettingAmount bettingAmount = new BettingAmount("10000");
        return new GamePlayer(name, CardFixture.카드_목록_생성(cardValues), bettingAmount);
    }

    public static GamePlayer 게임_플레이어_생성(Name name, BettingAmount bettingAmount) {
        return new GamePlayer(name, CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR)), bettingAmount);
    }

    public static Dealer 딜러_생성(Name name) {
        return new Dealer(name, CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR)));
    }
}
