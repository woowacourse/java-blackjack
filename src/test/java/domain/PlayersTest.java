package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.hand.Hand;
import domain.participant.*;
import org.junit.jupiter.api.Test;

class PlayersTest {
    Player bustPlayer = Player.of(
            new PlayerInfo(Name.from("pobi"), BettingMoney.of(1000)),
            new Hand(new ArrayList<>(List.of(
                    Card.of(CardNumber.JACK, CardShape.CLOVER),
                    Card.of(CardNumber.KING, CardShape.HEART),
                    Card.of(CardNumber.QUEEN, CardShape.DIAMOND))))
    );

    Player normalPlayer = Player.of(
            new PlayerInfo(Name.from("jason"), BettingMoney.of(2000)),
            new Hand(new ArrayList<>(List.of(
                    Card.of(CardNumber.JACK, CardShape.CLOVER),
                    Card.of(CardNumber.QUEEN, CardShape.DIAMOND)
            ))));

    Players allBustPlayers = Players.from(List.of(bustPlayer, bustPlayer, bustPlayer));
    Players containBustPlayers = Players.from(List.of(bustPlayer, normalPlayer, normalPlayer));
    Players allNormalPlayers = Players.from(List.of(normalPlayer, normalPlayer, normalPlayer));

    @Test
    void 모든_플레이어_버스트_여부_확인() {
        assertThat(allBustPlayers.isAllBust()).isTrue();
        assertThat(containBustPlayers.isAllBust()).isFalse();
        assertThat(allNormalPlayers.isAllBust()).isFalse();
    }
}
