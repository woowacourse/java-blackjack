package domain;

import domain.dto.NameAndCards;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class BlackjackTest {

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
        Assertions.assertThatCode(() -> new Blackjack(players, deck))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이들이_초기에_공개한_카드를_반환한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();

        // when
        NameAndCards dealer = blackjack.openDealerCards();
        List<NameAndCards> participants = blackjack.openParticipantsCards();
        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealer.cards().size())
                    .isEqualTo(1);

            for (NameAndCards nameAndCards : participants) {
                softly.assertThat(nameAndCards.cards().size())
                        .isEqualTo(2);
            }
        });
    }

    @Test
    void 특정_참여자에게_추가_카드_한_장을_분배한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();
        final int drawnCount = blackjack.getNameAndCardsByName("시소").cards().size();

        // when & then
        Assertions.assertThat(blackjack.addCardByName("시소").cards().size())
                .isEqualTo(drawnCount + 1);


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
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),
                new Card(CardShape.SPADE, CardNumber.EIGHT),

                new Card(CardShape.SPADE, CardNumber.EIGHT)
        )));
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();

        // when
        boolean actual = blackjack.addCardToDealerIfLowScore();

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
