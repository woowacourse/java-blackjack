package model.result;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantCardTest {

    @DisplayName("딜러 카드 결과 생성")
    @Test
    void createDealerCardWithFirstCard() {
        List<Card> cards = List.of(new Card(ACE, HEART), new Card(JACK, SPADE));
        Dealer dealer = new Dealer(cards);
        ParticipantCard dealerCard = ParticipantCard.createWithFirstCard(dealer);
        assertAll(
            () -> assertThat(dealerCard.getCards()).hasToString("[A하트]"),
            () -> assertThat(dealerCard.getName()).isEqualTo("딜러")
        );
    }

    @DisplayName("플레이어 카드 결과 생성")
    @Test
    void createPlayerCardWhitAllCards() {
        List<Card> cards = List.of(new Card(ACE, HEART), new Card(JACK, SPADE));
        Player player = new Player("jojo", cards);
        ParticipantCard playerCard = ParticipantCard.createWithAllCard(player);
        assertAll(
            () -> assertThat(playerCard.getCards()).hasToString("[A하트, J스페이드]"),
            () -> assertThat(playerCard.getName()).isEqualTo("jojo")
        );
    }
}
