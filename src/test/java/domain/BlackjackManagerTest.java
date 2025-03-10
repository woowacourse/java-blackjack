package domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlackjackManagerTest {

    @Test
    void 블랙잭_객체를_생성한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();

        // when & then
        Assertions.assertThatCode(() -> new BlackjackManager(players, deck))
                .doesNotThrowAnyException();
    }

    @Test
    void 특정_참여자에게_추가_카드_한_장을_분배한다() {
        // given
        Player siso = new Participant("시소");
        Players players = new Players(List.of(
                new Dealer(),
                siso,
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();
        BlackjackManager blackjackManager = new BlackjackManager(players, deck);
        blackjackManager.distributeInitialCards();
        final int beforeDrawnCount = siso.getCards().size();
        blackjackManager.addOneCard(siso);
        final int afterDrawnCount = siso.getCards().size();

        // when & then
        Assertions.assertThat(afterDrawnCount)
                .isEqualTo(beforeDrawnCount + 1);
    }

    @Test
    void 딜러의_카드_합이_16이하면_카드를_한장_추가한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),

                new Card(Suit.SPADE, Rank.EIGHT)
        )));
        BlackjackManager blackjackManager = new BlackjackManager(players, deck);
        blackjackManager.distributeInitialCards();

        // when
        boolean actual = blackjackManager.addCardToDealerIfLowScore();

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
