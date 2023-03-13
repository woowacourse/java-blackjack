package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParticipantTest {

    public static final Card CLUB_EIGHT = Card.of(CardSuit.CLUB, CardNumber.EIGHT);
    public static final Card HEART_EIGHT = Card.of(CardSuit.HEART, CardNumber.EIGHT);
    public static final Card DIA_ACE = Card.of(CardSuit.DIAMOND, CardNumber.ACE);

    @Test
    @DisplayName("첫 카드 뽑기 - 실패 : 이미 카드를 뽑았다면 첫 카드 뽑기를 수행 시 예외 발생")
    void drawFirstCards_fail_if_already_draw() {
        //given
        Participant participant = new Player(new Name("도치"));
        CardDeck cardDeck = new CardDeck(List.of(CLUB_EIGHT, HEART_EIGHT, DIA_ACE));

        // when
        participant.drawFirstTurnCards(cardDeck);
        //then
        assertThatThrownBy(() -> participant.drawFirstTurnCards(cardDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 첫 카드를 뽑은 상태입니다.");
    }
}
