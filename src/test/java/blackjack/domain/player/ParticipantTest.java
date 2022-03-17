package blackjack.domain.player;

import blackjack.domain.betting.Money;
import blackjack.domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("참여자 이름은 비어있을 수 없다")
    void checkNameNullOrEmpty(String name) {
        AcceptStrategy inputStrategy = new ParticipantAcceptStrategy();
        assertThatThrownBy(() -> new Participant("pobi", inputStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("참가자는 추가로 카드를 받을 수 있다.")
    void addParticipantCard() {
        Deck deck = new Deck(new RandomCardGenerator());
        Participant participant = new Participant("pobi", name -> true);
        participant.addCard(deck.draw());
        participant.addCard(deck.draw());
        int size = participant.getCards().size();
        participant.addCard(deck.draw());
        assertThat(participant.getCards().size()).isEqualTo(size + 1);
    }

    @Test
    @DisplayName("참가자는 Burst가 되지 않으면 카드를 받을 수 있다.")
    void acceptCardWhenNotBurst() {
        Participant participant = new Participant("pobi", name -> true);

        participant.addCard(new Card(Type.CLOVER, Score.TWO));
        participant.addCard(new Card(Type.SPADE, Score.ACE));
        participant.addCard(new Card(Type.DIAMOND, Score.NINE));

        assertThat(participant.acceptableCard()).isEqualTo(true);
    }

    @Test
    @DisplayName("참가자는 Burst가 되면 카드를 받을 수 없다.")
    void notAcceptCardWhenBurst() {
        Participant participant = new Participant("pobi", name -> true);
        participant.addCard(new Card(Type.DIAMOND, Score.KING));
        participant.addCard(new Card(Type.DIAMOND, Score.JACK));
        participant.addCard(new Card(Type.DIAMOND, Score.TWO));
        assertThat(participant.acceptableCard()).isEqualTo(false);
    }

}
