package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.constant.TrumpRank;
import blackjack.constant.TrumpSuit;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 플레이어의_초기_카드를_등록한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        PlayerName playerName = new PlayerName("제프리");
        Player player = new Player(playerName);

        // when
        player.drawInitializeHand(cards);

        // then
        assertThat(player.getCards().getSize()).isEqualTo(2);
    }

    @Test
    void 플레이어의_초기_카드가_두장_미만일시_에러를_발생시킨다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND)
        );
        PlayerName playerName = new PlayerName("제프리");
        Player player = new Player(playerName);

        // when // then
        assertThatThrownBy(() -> player.drawInitializeHand(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 초기 카드는 2장을 받아야 합니다.");
    }

    @Test
    void 플레이어의_초기_카드가_두장_초과일시_에러를_발생시킨다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE),
                createCard(TrumpRank.THREE, TrumpSuit.SPADE)
        );
        PlayerName playerName = new PlayerName("제프리");
        Player player = new Player(playerName);

        // when // then
        assertThatThrownBy(() -> player.drawInitializeHand(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 초기 카드는 2장을 받아야 합니다.");
    }

    @Test
    void 플레이어의_초기_배팅금액을_등록한다() {
        // given
        PlayerName playerName = new PlayerName("제프리");
        Player player = new Player(playerName);
        int betAmount = 10000;

        // when
        player.updateBetAmount(betAmount);

        // then
        assertThat(player.getBetAmount()).isEqualTo(10000);
    }

    @Test
    void 플레이어의_카드를_오픈한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        PlayerName playerName = new PlayerName("제프리");
        Player player = new Player(playerName);
        player.drawInitializeHand(cards);

        // when
        List<Card> openedCards = player.openCards();

        // then
        assertThat(openedCards).hasSize(2);
        assertThat(openedCards.getFirst().getRank()).isEqualTo(TrumpRank.ACE);
        assertThat(openedCards.getFirst().getSuit()).isEqualTo(TrumpSuit.DIAMOND);
    }

    @Test
    void 플레이어에게_카드를_한장_나눠준다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        PlayerName playerName = new PlayerName("제프리");
        Player player = new Player(playerName);
        player.drawInitializeHand(cards);
        Card card = createCard(TrumpRank.THREE, TrumpSuit.SPADE);

        // when
        player.addCard(card);

        // then
        assertThat(player.getCards().getSize()).isEqualTo(3);
        assertThat(player.openCards().getLast().getRank()).isEqualTo(TrumpRank.THREE);
        assertThat(player.openCards().getLast().getSuit()).isEqualTo(TrumpSuit.SPADE);
    }

    private Cards createCards(Card... cards) {
        return Arrays.stream(cards)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
    }

    private Card createCard(TrumpRank rank, TrumpSuit suit) {
        return new Card(rank, suit);
    }

}
