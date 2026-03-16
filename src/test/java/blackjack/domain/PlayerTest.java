package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        Name newPlayerName = Name.of("한다");
        player = Player.of(newPlayerName, BetAmount.of(1000));
    }

    @Test
    void 플레이어_생성시_name이_null이면_예외_발생한다() {
        Name nullName = null;

        assertThatThrownBy(() -> Player.of(nullName, BetAmount.of(10000)))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("name 은 null 이 올 수 없습니다.");
    }

    @Test
    void 플레이어를_생성한다() {
        assertThat(player).isNotNull();
    }

    @Test
    void 플레이어의_이름을_반환한다() {
        assertThat(player.name()).isEqualTo("한다");
    }


    @Test
    void 플레이어는_초기에_비어있는_핸드를_가진다() {
        assertThat(player.countCards()).isEqualTo(0);
    }

    @Test
    void 플레이어가_카드1장_받는다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        player.receiveCard(spadeAce);
        assertThat(player.countCards()).isEqualTo(1);
    }

    @Test
    void 플레이어가_여러_카드를_받는다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        player.receiveCard(spadeAce);
        player.receiveCard(heartKing);
        assertThat(player.countCards()).isEqualTo(2);
    }

    @Test
    void 플레이어의_현재_점수를_계산한다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        player.receiveCard(spadeAce);
        player.receiveCard(heartKing);

        assertThat(player.score()).isEqualTo(21);
    }

    @Test
    void 플레이어가_여러_카드를_한번에_받는다() {
        List<TrumpCard> cards = new ArrayList<>();
        cards.add(TrumpCard.of(Suit.SPADE, Rank.ACE));
        cards.add(TrumpCard.of(Suit.HEART, Rank.KING));

        player.receiveCards(cards);
        assertThat(player.countCards()).isEqualTo(2);
    }

    @Test
    void 플레이어가_21이하면_카드를_더_받을_수_있다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        player.receiveCard(spadeKing);

        assertThat(player.canHit()).isTrue();
    }

    @Test
    void 플레이어가_21을_초과하면_카드를_받을_수_없다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        TrumpCard heartQueen = TrumpCard.of(Suit.HEART, Rank.QUEEN);
        TrumpCard diamondFive = TrumpCard.of(Suit.DIAMOND, Rank.FIVE);

        player.receiveCard(spadeKing);
        player.receiveCard(heartQueen);
        player.receiveCard(diamondFive);

        assertThat(player.canHit()).isFalse();
    }

    @Test
    void 플레이어의_보유_카드_목록을_반환한다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        player.receiveCard(spadeAce);
        player.receiveCard(heartKing);

        assertThat(player.getCards()).hasSize(2);
        assertThat(player.getCards()).containsExactly(spadeAce, heartKing);
    }

    @Test
    void 처음_두장의_합이_21이면_블랙잭이다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        player.receiveCard(spadeAce);
        player.receiveCard(heartKing);

        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    void 세장_이상으로_합이_21이면_블랙잭이_아니다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);
        TrumpCard cloverKing = TrumpCard.of(Suit.CLOVER, Rank.KING);

        player.receiveCard(spadeAce);
        player.receiveCard(heartKing);
        player.receiveCard(cloverKing);

        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    void 두장이지만_합이_21이_아니면_블랙잭이_아니다() {
        TrumpCard spadeJack = TrumpCard.of(Suit.SPADE, Rank.JACK);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        player.receiveCard(spadeJack);
        player.receiveCard(heartKing);

        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    void 플레이어의_배팅금액_값을_가져온다() {
        Player player = Player.of(Name.of("handa"), BetAmount.of(3000));
        assertThat(player.betAmount().money()).isEqualTo(3000);
    }
}