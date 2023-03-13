package model.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import model.card.CardFixture;
import model.card.Deck;
import model.card.RandomShuffleMaker;
import model.money.Bet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Participants participants;
    private Dealer dealer;
    private Player bebe;
    private Player ethan;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        final Name bebeName = new Name("bebe");
        final Name ethanName = new Name("ethan");
        participants = Participants.of(List.of(bebeName, ethanName), List.of(new Bet(10_000), new Bet(15_000)), dealer);
        bebe = participants.getPlayers().get(0);
        ethan = participants.getPlayers().get(1);
    }

    @DisplayName("receiveInitialCards가 두 장의 카드를 주는지 확인한다.")
    @Test
    void receiveInitialCards() {
        // given
        participants.receiveInitialCards(Deck.create(new RandomShuffleMaker()));

        // when, then
        assertAll(
                () -> assertThat(bebe.getHand().getCards()).hasSize(2),
                () -> assertThat(dealer.getHand().getCards()).hasSize(2)
        );
    }

    @Test
    @DisplayName("totalBets을 하면 플레이어의 배팅 금액과 게임 상태를 검사해서 전체 수익이 나온다.")
    void getTotalBetsTest() {
        // given
        dealer.receiveCard(CardFixture.DIAMOND_KING);
        dealer.receiveCard(CardFixture.DIAMOND_SEVEN);

        bebe.receiveCard(CardFixture.DIAMOND_ACE);
        bebe.receiveCard(CardFixture.DIAMOND_JACK);

        ethan.receiveCard(CardFixture.DIAMOND_SEVEN);
        ethan.receiveCard(CardFixture.DIAMOND_SIX);

        // when, then
        assertThat(participants.calculateTotalProfits()).isEqualTo(0);
    }
}
