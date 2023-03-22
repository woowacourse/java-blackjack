package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 최대_수_초과시_초과상태_반환() {
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("Q하트", 10));
        cardsByCardBox.add(new Card("j하트", 10));
        cardsByCardBox.add(new Card("k하트", 10));
        Cards cards = new Cards(cardsByCardBox);
        Betting betting = new Betting(10000, 0);

        Player player = new Player(cards, name, betting);

        assertThat(player.askPlayerState()).isEqualTo(PlayerState.MORE_THAN_MAXIMUM);
    }

    @Test
    void 최대_수_동일시_동일상태_반환() {
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("A하트", 11));
        cardsByCardBox.add(new Card("j하트", 10));
        Cards cards = new Cards(cardsByCardBox);
        Betting betting = new Betting(10000, 0);

        Player player = new Player(cards, name, betting);

        assertThat(player.askPlayerState()).isEqualTo(PlayerState.EQUAL_WITH_MAXIMUM);
    }

    @Test
    void 최대_수_미만시_미만상태_반환() {
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("7하트", 7));
        cardsByCardBox.add(new Card("8하트", 8));
        Cards cards = new Cards(cardsByCardBox);
        Betting betting = new Betting(10000, 0);

        Player player = new Player(cards, name, betting);

        assertThat(player.askPlayerState()).isEqualTo(PlayerState.LESS_THAN_MAXIMUM);
    }

    @Test
    void 블랙잭여부_판별가능() {
        Name name = new Name("hamad");
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card("Q하트", 10));
        cardsByCardBox.add(new Card("A하트", 11));
        Cards cards = new Cards(cardsByCardBox);
        Betting betting = new Betting(10000, 0);

        Player player = new Player(cards, name, betting);
        assertThat(player.isBlackJack()).isTrue();
    }
}
