package blackjack.fixture;

import blackjack.domain.card.CardValue;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.info.Name;
import blackjack.domain.player.Participant;

import java.util.List;

public class PlayerFixture {
    public static GamePlayer 게임_플레이어_생성(final List<CardValue> cardValues) {
        final Name name = new Name("초롱");
        return new GamePlayer(name, CardFixture.카드_목록_생성(cardValues));
    }

    public static GamePlayer 게임_플레이어_생성(final Name name) {
        return new GamePlayer(name, CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR)));
    }

    public static Participant 참가자_생성(final List<CardValue> cardValues) {
        return new ParticipantImpl(CardFixture.카드_목록_생성(cardValues));
    }

    public static Dealer 딜러_생성(final Name name) {
        return new Dealer(name, CardFixture.카드_목록_생성(List.of(CardValue.EIGHT, CardValue.FOUR)));
    }
}
