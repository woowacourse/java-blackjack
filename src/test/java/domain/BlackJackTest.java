package domain;

import domain.card.Card;
import domain.card.CardHolder;
import domain.card.DeckOfCards;
import domain.card.Number;
import domain.card.Shape;
import domain.player.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackJackTest {

    private static final List<Name> GIVEN_NAMES = Name.of(List.of("여우", "아벨"));

    @Test
    @DisplayName("게임 시작 시 플레이어들에게 카드를 2장씩 나눠준다.")
    void whenStartingGame_thenPerPlayerHavingTwoCard() {
        BlackJack blackJack = new BlackJack(
                new Players(makeDealer(), Participant.from(GIVEN_NAMES)),
                DeckOfCards.create(maxIndex -> 0)
        );
        blackJack.initializeCardsOfPlayers();
        List<PlayerReadOnly> players = blackJack.getPlayers().getAllPlayers();

        assertAll(() -> {
                    for (PlayerReadOnly player : players) {
                        assertThat(player.getCards()).hasSize(2);
                    }});
    }

    @Test
    @DisplayName("전체 플레이어에게 카드를 한 장씩 추가한다.")
    void givesAllPlayersACard() {
        Dealer dealer = makeDealer();
        List<Participant> participants = Participant.from(GIVEN_NAMES);
        BlackJack blackJack = new BlackJack(
                new Players(dealer, participants),
                DeckOfCards.create(maxIndex -> 0)
        );
        blackJack.giveCardToAllPlayers();

        assertThat(dealer.getCards()).hasSize(1);
        participants.forEach(participant -> assertThat(participant.getCards()).hasSize(1));
    }

    @Test
    @DisplayName("특정 플레이어에게 한 장을 추가한다.")
    void givenPlayer_thenGivesCard() {
        BlackJack blackJack = new BlackJack(
                new Players(makeDealer(), Participant.from(GIVEN_NAMES)),
                DeckOfCards.create(maxIndex -> 0)
        );
        PlayerReadOnly participant = blackJack.getParticipants().get(0);

        blackJack.giveCard(participant);

        List<Card> cards = participant.getCards();
        assertThat(cards).hasSize(1);
    }

    @Test
    @DisplayName("딜러의 총 점수가 16 이하인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrLessSixTeen() {
        BlackJack blackJack = new BlackJack(
                new Players(
                        makeDealer(
                                new Card(Shape.SPADE, Number.KING),
                                new Card(Shape.HEART, Number.SIX)), Participant.from(GIVEN_NAMES)
                ), null
        );
        assertThat(blackJack.shouldDealerGetCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 총 점수가 17 이상인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrMoreSevenTeen() {
        BlackJack blackJack = new BlackJack(
                new Players(
                        makeDealer(
                                new Card(Shape.SPADE, Number.KING),
                                new Card(Shape.HEART, Number.SEVEN)), Participant.from(GIVEN_NAMES)
                ), null
        );
        assertThat(blackJack.shouldDealerGetCard()).isFalse();
    }

    @Test
    @DisplayName("딜러에게 한 장의 카드를 추가한다.")
    void thenGiveDealerCard() {
        Dealer dealer = makeDealer();
        BlackJack blackJack = new BlackJack(new Players(dealer, Participant.from(GIVEN_NAMES)), DeckOfCards.create(maxIndex -> 0));
        blackJack.giveCardToDealer();
        assertThat(dealer.getCards()).hasSize(1);
    }

    private static Dealer makeDealer(Card... cards) {
        if (cards.length == 0) {
            return new Dealer(CardHolder.makeEmptyHolder());
        }
        return new Dealer(new CardHolder(List.of(cards)));
    }
}