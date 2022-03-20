package blackJack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Participants;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    @DisplayName("BlackJackGame 성공적으로 생성될 경우 null 값을 반환하지 않는다.")
    void createValidDealer() {
        Participants participants = Participants.fromNames(List.of("rookie"));

        assertThat(new BlackJackGame(Deck.create(), participants)).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 각 참가자들에게 기본적으로 2장의 카드를 분배한다.")
    void firstCardDispensing() {
        Participants participants = Participants.fromNames(List.of("kei", "rookie"));
        ;

        BlackJackGame blackJackGame = new BlackJackGame(Deck.create(), participants);
        blackJackGame.defaultDistributeCards();

        assertAll(
                () -> assertThat(participants.getPlayers().get(0).getCards().size()).isEqualTo(2),
                () -> assertThat(participants.getPlayers().get(1).getCards().size()).isEqualTo(2),
                () -> assertThat(participants.getDealer().getCards().size()).isEqualTo(2)
        );
    }
}
