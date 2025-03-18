package blackjack;

import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import card.DeckGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import player.Participant;
import player.Participants;

public class BlackjackTest {

    @Test
    void 블랙잭_객체를_생성한다() {
        // given
        Participants participants = new Participants(List.of(
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();

        // when & then
        Assertions.assertThatCode(() -> new Blackjack(participants, deck))
                .doesNotThrowAnyException();
    }

    @Test
    void 이름으로_블랙잭_객체를_생성한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();

        // when & then
        Assertions.assertThatCode(() -> new Blackjack(List.of("시소", "헤일러", "부기", "사나"), deck))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이들이_초기에_공개한_카드를_반환한다() {
        // given
        Participants participants = new Participants(List.of(
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();
        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();

        // when
        Map<String, List<Card>> map = blackjack.openParticipantsInitialCards();

        // then
        SoftAssertions.assertSoftly(softly -> {
            participants.getParticipants().forEach(player -> {
                softly.assertThat(map.get(player.getName()).size())
                        .isEqualTo(2);
            });
        });
    }

    @Test
    void 딜러가_초기에_공개한_카드를_반환한다() {
        // given
        Participants participants = new Participants(List.of(
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();
        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();

        // when
        List<Card> openedDealerInitialCards = blackjack.openDealerInitialCards();

        // then
        Assertions.assertThat(openedDealerInitialCards)
                .hasSize(1);
    }

    @Test
    void 특정_참여자에게_추가_카드_한_장을_분배한다() {
        // given
        Participant participant = new Participant("시소");
        Participants participants = new Participants(List.of(
                participant,
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();
        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();
        final int drawnCount = participant.getHandCards().size();

        // when
        blackjack.addCardToParticipant(participant);

        // then
        Assertions.assertThat(participant.getCards().size())
                .isEqualTo(drawnCount + 1);
    }

    @Test
    void 딜러의_카드_합이_16이하면_카드를_한장_추가한다() {
        // given
        Participants participants = new Participants(List.of(
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),
                new Card(CardSuit.SPADE, CardRank.EIGHT),

                new Card(CardSuit.SPADE, CardRank.EIGHT)
        )));
        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();

        // when
        boolean actual = blackjack.addCardToDealerIfLowScore();

        // then
        Assertions.assertThat(actual).isTrue();
    }
}
