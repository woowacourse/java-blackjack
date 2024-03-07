package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.PlayingCardShape.*;
import static domain.PlayingCardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("플레이어의 이름을 입력하면 참가자 인스턴스를 생성한다.")
    @Test
    void createPlayerTest() {
        // Given
        PlayerName playerName = new PlayerName("kelly");

        // When
        Player player = Player.of(playerName);

        // Then
        assertThat(player).isNotNull();
    }

    @DisplayName("플레이어가 버스트가 아니면 True를 반환한다.")
    @Test
    void isDrawableTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, KING));
        Hand hand = new Hand(playingCards);
        Player player = new Player(new PlayerName("kelly"), hand);

        // When
        Boolean isBurst = player.isDrawable();

        // Then
        assertThat(isBurst).isTrue();
    }

    @DisplayName("플레이어는 덱으로부터 카드를 한 장 뽑는다.")
    @Test
    void drawTest() {
        // Given
        Deck deck = Deck.init();
        Hand initHand = Hand.init();
        int initCardNumberSum = initHand.getCardsNumberSum();
        Player player = new Player(new PlayerName("kelly"), initHand);

        // When
        player.draw(deck);

        // Then
        assertThat(initCardNumberSum).isNotEqualTo(initHand.getCardsNumberSum());
    }
}
