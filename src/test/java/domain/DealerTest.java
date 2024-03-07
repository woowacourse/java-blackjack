package domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("참여자에게 카드 2장을 나눠준다.")
    void dealCards() {
        //given
        final Players players = Players.from(List.of("레디", "제제"));
        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = new Dealer(cardDeck);

        //when
        dealer.startDeal(players);

        //then
        Assertions.assertThat(players.getPlayers()).allMatch(player -> player.handsSize() == 2);
    }

    @Test
    @DisplayName("참여자의 답변이 y라면 카드를 한장 추가한다.")
    void addOneCard() {
        //given
        final Participant redddy = new Participant("레디", Hands.createEmptyPacket());
        final Participant zeze = new Participant("제제", Hands.createEmptyPacket());

        final Players players = new Players(List.of(redddy, zeze));

        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = new Dealer(cardDeck);
        dealer.startDeal(players);

        //when
        dealer.deal(redddy, Answer.HIT);
        dealer.deal(zeze, Answer.STAY);

        //then
        Assertions.assertThat(redddy.handsSize()).isEqualTo(3);
        Assertions.assertThat(zeze.handsSize()).isEqualTo(2);
    }
}
