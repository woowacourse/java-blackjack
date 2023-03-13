package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import blackjack.service.BlackjackGame;

import java.util.List;

class BlackjackGameTest {

    @Test
    @DisplayName("딜러와 플레이어가 버스트되지 않았을때 딜러가 2승 1패")
    void createResults_thenClassifyParticipants() {
        //given
        final Participants participants = Participants.from(List.of(
                Participant.from("파워", BetAmount.from("10000")),
                Participant.from("준팍", BetAmount.from("10000")),
                Participant.from("서브웨이", BetAmount.from("10000"))
        ));
        final List<Number> numbers = List.of(Number.SIX,
                Number.SEVEN, Number.TWO,
                Number.THREE, Number.FOUR,
                Number.TEN, Number.TEN,
                Number.SIX, Number.SIX);

        final Deck deck = Deck.from(TestCardGenerator.from(numbers));
        final Dealer dealer = Dealer.create();

        //when
        BlackjackGame blackjackGame = BlackjackGame.from(deck);
        blackjackGame.distributeInitialCards(participants, dealer);
        blackjackGame.distributeCard(dealer);
        blackjackGame.distributeCard(dealer);

        //then
        Assertions.assertThat(blackjackGame.calculateRevenues(participants, dealer).getDealerResult()).isEqualTo(10000);
    }
}
