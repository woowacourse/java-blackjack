package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.TrumpSuit;
import blackjack.constant.TrumpRank;
import blackjack.constant.MatchResult;
import blackjack.gambler.Nickname;
import blackjack.gambler.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {

    @Test
    void 초기_카드_두장을_받아_플레이어를_생성한다() {
        // given
        List<Card> initialCards = makeCards(TrumpRank.ACE, TrumpRank.EIGHT);
        Cards cards = new Cards(initialCards);

        // when // then
        assertDoesNotThrow(() -> new Player(new Nickname("pobi"), cards));
    }

    @Test
    void 초기_카드_세장을_받으면_예외를_발생시킨다() {
        // given
        List<Card> initialCards = makeCards(TrumpRank.ACE, TrumpRank.EIGHT);
        initialCards.add(new Card(TrumpRank.EIGHT, TrumpSuit.HEART));
        Cards cards = new Cards(initialCards);

        // when // then
        assertThatThrownBy(() -> new Player(new Nickname("pobi"), cards)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 초기 카드는 2장을 받아야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, EIGHT, ACE, false",
            "EIGHT, SEVEN, SIX, false",
            "KING, KING, TWO, true",
            "QUEEN, JACK, KING, true"
    })
    void 카드를_한장_받았을_때_21이_넘는지_확인한다(TrumpRank rank1, TrumpRank rank2, TrumpRank rank3, boolean expected) {
        // given
        List<Card> initialCards = makeCards(rank1, rank2);
        Card card = new Card(rank3, TrumpSuit.SPADE);
        Cards cards = new Cards(initialCards);
        Player player = new Player(new Nickname("pobi"),cards);
        player.addOneCard(card);

        // when
        boolean isOverBustStandard = player.isBust();

        // then
        assertThat(isOverBustStandard).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, ACE, KING, 12",
            "ACE, THREE, FOUR, 18",
            "ACE, THREE, KING, 14",
    })
    void 카드들의_합을_구한다(TrumpRank rank1, TrumpRank rank2, TrumpRank rank3, int expected) {
        // given
        List<Card> initialCards = makeCards(rank1, rank2);
        Cards cards = new Cards(initialCards);
        Player player = new Player(new Nickname("pobi"), cards);
        player.addOneCard(new Card(rank3, TrumpSuit.HEART));

        // when
        int sumCards = player.sumCardScores();

        // then
        assertThat(sumCards).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "KING, KING, EIGHT, 28, LOSE",
            "KING, NINE, EIGHT, 28, LOSE",
            "KING, KING, EIGHT, 27, LOSE",
            "KING, THREE, FOUR, 27, WIN",
            "KING, KING, EIGHT, 18, LOSE",
            "KING, THREE, EIGHT, 18, WIN",
            "KING, THREE, FOUR, 18, LOSE",
            "KING, THREE, EIGHT, 21, DRAW"
    })
    void 딜러와의_승무패를_정한다(TrumpRank rank1, TrumpRank rank2, TrumpRank rank3, int dealerScore,
                       MatchResult expected) {
        // given
        List<Card> initialCards = makeCards(rank1, rank2);
        Cards cards = new Cards(initialCards);
        Player player = new Player(new Nickname("pobi"), cards);
        player.addOneCard(new Card(rank3, TrumpSuit.HEART));

        // when
        MatchResult matchResult = player.compareTo(dealerScore);

        // then
        assertThat(matchResult).isEqualTo(expected);
    }

    private List<Card> makeCards(TrumpRank rank1, TrumpRank rank2) {
        List<Card> initialCards = new ArrayList<>();
        initialCards.add(new Card(rank1, TrumpSuit.DIAMOND));
        initialCards.add(new Card(rank2, TrumpSuit.HEART));
        return initialCards;
    }
}
