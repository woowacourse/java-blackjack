package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Deck;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Participants(List.of("아톰", "구름")))
                .doesNotThrowAnyException();
    }

    @DisplayName("참여자들은 딜러 포함 최소 2명 이상이어야 한다.")
    @Test
    void validatePlayerSize() {
        List<String> empty = Collections.emptyList();
        assertThatThrownBy(() -> new Participants(empty))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참여자들은 중복된 이름을 가질 수 없다.")
    @Test
    void validateDuplicatedPlayerName() {
        List<String> playerNames = List.of("atom", "atom");
        assertThatThrownBy(() -> new Participants(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참여자들은 딜러와 이름이 같을 수 없다.")
    @Test
    void validateDuplicatedDealerName() {
        List<String> playerNames = List.of("딜러");
        assertThatThrownBy(() -> new Participants(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참여자들은 초기 카드를 뽑을 수 있다.")
    @Test
    void drawInitialCards() {
        Participants participants = new Participants(List.of("아톰", "구름"));
        Deck deck = Deck.createShuffledDeck();

        participants.drawInitialCards(deck);

        assertThat(participants.getDealer().getCards()).hasSize(2);
        assertThat(participants.getPlayers().get(0).getCards()).hasSize(2);
        assertThat(participants.getPlayers().get(1).getCards()).hasSize(2);
    }
}
