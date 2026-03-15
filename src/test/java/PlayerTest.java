import domain.Amount;
import domain.Card;
import domain.CardDeck;
import domain.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private CardDeck cardDeck;

    private Player createPlayer(String name) {
        return new Player(name, new Amount(1000));
    }

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck();
    }

    @Test
    @DisplayName("문자열을 이름으로 한 사용자를 생성한다.")
    public void make_user_name_to_string(){
        String input = "jason";
        Player player = createPlayer(input);
        Assertions.assertThat(player.getName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("첫 딜에 사용자는 패를 2장 갖는다.")
    public void dealer_should_open_1_card (){
        Player player = createPlayer("json");
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.draw());
        cards.add(cardDeck.draw());
        player.receiveInitCard(cards);
        assertThat(player.getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드의 합이 21이 넘을 시 버스트 판정이다")
    public void if_card_sum_over21_burst(){
        int score = 22;
        Player player = createPlayer("json");
        boolean result = player.isBurst(score);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘지 않으면 버스트가 아니다")
    public void if_card_sum_under21_burst(){
        int score = 20;
        Player player = createPlayer("json");
        boolean result = player.isBurst(score);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("첫 배부 때 합이 21 나오면 블랙잭 판정이다")
    public void if_card_sum_equals21_blackjack(){
        Player player = createPlayer("json");
        player.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_TEN));
        boolean result = player.isBlackjack();
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("첫 배부 때 합이 21이 안 나오면 블랙잭 판정이 아니다")
    public void if_card_sum_not21_blackjack(){
        Player player = createPlayer("json");
        player.receiveInitCard(List.of(Card.CLUB_ACE, Card.CLUB_NINE));
        boolean result = player.isBlackjack();
        assertThat(result).isFalse();
    }
}
