package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Face;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("참여인원이 1명 미만일 경우, 예외 처리한다")
    void whenPlayerIsLessThanOneThrowsException() {
        List<String> playerNames = new ArrayList<>();
        List<Cards> playerCards = new ArrayList<>();

        assertThatThrownBy(() -> new Players(playerCards, playerNames)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
    }

    @Test
    @DisplayName("카드뭉치와 플레이어 참여숫자가 다를경우 예외 처리한다")
    void whenCardsGroupIsLessThanOneThrowsException() {
        List<String> playerNames = new ArrayList<>(Arrays.asList("one"));
        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList1.add(new Card(Suit.DIAMOND, Face.KING));
        List<Card> cardList2 = new ArrayList<>();
        cardList2.add(new Card(Suit.DIAMOND, Face.JACK));
        cardList2.add(new Card(Suit.DIAMOND, Face.KING));
        List<Cards> playerCards = new ArrayList<>(Arrays.asList(new Cards(cardList1), new Cards(cardList2)));

        assertThatThrownBy(() -> new Players(playerCards, playerNames)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어에게 나눠줄 카드 뭉치 숫자와 플레이어 수는 동일해야 합니다.");
    }

    @Test
    @DisplayName("참여인원이 7명 초과일 경우, 예외 처리한다")
    void whenPlayerIsGreaterThanSevenThrowsException() {
        List<String> playerNames = new ArrayList<>();
        List<Cards> cards = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Card> cardList = new ArrayList<>();
            cardList.add(new Card(Suit.DIAMOND, Face.ACE));
            cardList.add(new Card(Suit.DIAMOND, Face.ACE));
            playerNames.add("pobi");
            cards.add(new Cards(cardList));
        }

        assertThatThrownBy(() -> new Players(cards, playerNames)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의수는 1명 이상 7명 이하여야 합니다.");
    }
}
