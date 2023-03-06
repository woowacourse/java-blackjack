package domain;

import domain.card.Card;
import domain.card.CardHolder;
import domain.card.CardRepository;
import domain.card.Number;
import domain.card.Shape;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.PlayerReadOnly;
import domain.player.Players;
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
                Players.with(makeDealer(), GIVEN_NAMES),
                CardRepository.create(maxIndex -> 0)
        );
        blackJack.initializeCardsOfPlayers();
        List<PlayerReadOnly> players = blackJack.getPlayers().getAllPlayers();

        assertAll(() -> {
                    for (PlayerReadOnly player : players) {
                        assertThat(player.getCards()).hasSize(2);
                    }});
    }

    @Test
    @DisplayName("플레이어에게 한 장을 추가한다.")
    void givenPlayer_thenGivesCard() {
        BlackJack blackJack = new BlackJack(
                Players.with(makeDealer(), GIVEN_NAMES),
                CardRepository.create(maxIndex -> 0)
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
                Players.with(
                        makeDealer(
                                new Card(Shape.SPADE, Number.KING),
                                new Card(Shape.HEART, Number.SIX)), GIVEN_NAMES
                ), null
        );
        assertThat(blackJack.shouldDealerGetCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 총 점수가 17 이상인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrMoreSevenTeen() {
        BlackJack blackJack = new BlackJack(
                Players.with(
                        makeDealer(
                                new Card(Shape.SPADE, Number.KING),
                                new Card(Shape.HEART, Number.SEVEN)), GIVEN_NAMES
                ), null
        );
        assertThat(blackJack.shouldDealerGetCard()).isFalse();
    }

    @Test
    @DisplayName("딜러에게 한 장의 카드를 추가한다.")
    void thenGiveDealerCard() {
        Dealer dealer = makeDealer();
        BlackJack blackJack = new BlackJack(Players.with(dealer, GIVEN_NAMES), CardRepository.create(maxIndex -> 0));
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