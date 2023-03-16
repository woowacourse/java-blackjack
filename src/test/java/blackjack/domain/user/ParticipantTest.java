package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.cardpack.CardPack;
import blackjack.domain.card.cardpack.NoviceShuffleStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.fixture.FixtureCard.스페이드_10;
import static blackjack.domain.fixture.FixtureCard.스페이드_A;
import static blackjack.domain.fixture.FixtureCard.클로버_10;
import static blackjack.domain.fixture.FixtureCard.하트_10;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantTest {

    @Test
    void 유저는_카드팩에서_카드를_뽑는다() {
        // given
        Participant participant = new Participant("주노");
        CardPack cardPack = new CardPack();
        cardPack.shuffle(new NoviceShuffleStrategy());

        // when
        participant.drawCard(cardPack);

        // then
        List<Card> userCards = participant.showCards();
        Assertions.assertThat(userCards.get(0))
                .isEqualTo(스페이드_A);
    }

    @Test
    void 유저는_손패가_21점이_넘어가면_BUST() {
        // given
        Participant participant = new Participant("주노");
        CardPack cardPack = new CardPack(List.of(클로버_10, 스페이드_10, 하트_10));

        // when
        participant.drawCard(cardPack);
        participant.drawCard(cardPack);
        participant.drawCard(cardPack);

        // then
        Assertions.assertThat(participant.isBust()).isTrue();
    }


}
