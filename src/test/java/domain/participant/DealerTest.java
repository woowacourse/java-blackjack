package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.assertj.core.api.InstanceOfAssertFactories.type;

class DealerTest {

    private Card card;
    private Dealer dealer;

    @BeforeEach
    void init() {
        card = Card.create(CardPattern.HEART, CardNumber.ACE);
        dealer = Dealer.create();
    }

    @Test
    @DisplayName("create()는 호출하면, 딜러를 생성한다")
    void create_whenCall_thenSuccess() {
        assertThatCode(() -> Dealer.create())
                .doesNotThrowAnyException();

        assertThat(Dealer.create())
                .isExactlyInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_thenSuccess() {
        // when
        dealer.addCard(card);

        // then
        assertThat(dealer)
                .extracting("participantCard", type(ParticipantCard.class))
                .extracting("cards", as(list(Card.class)))
                .hasSize(1);
    }

    @Test
    @DisplayName("getFirst()는 호출하면, 딜러의 첫 번째 카드를 조회한다")
    void getFirst_whenCall_thenReturnFirstCard() {
        // given
        dealer.addCard(card);

        // when
        Card actual = dealer.getFirstCard();

        // then
        assertThat(actual).isEqualTo(card);
    }
}
