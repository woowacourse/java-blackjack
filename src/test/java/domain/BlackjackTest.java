package domain;

import domain.dto.OpenCardsResponse;
import domain.dto.PlayerResponse;
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
        OpenCardsResponse response = blackjack.openInitialCards();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.dealer().cards().size())
                    .isEqualTo(1);

            for (PlayerResponse playerResponse : response.participants()) {
                softly.assertThat(playerResponse.cards().size())
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
        final int drawnCount = blackjack.getPlayerByName("시소").cards().size();

        // when & then
        Assertions.assertThat(blackjack.addCardToCurrentParticipant("시소").cards().size())
                .isEqualTo(drawnCount + 1);


    }
}
