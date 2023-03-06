package domain;

import domain.card.Card;
import domain.player.Name;
import domain.player.PlayerReadOnly;
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
        BlackJack blackJack = new BlackJack(GIVEN_NAMES, cardSize -> 0);
        blackJack.initializeCardsOfPlayers();

        List<PlayerReadOnly> players = blackJack.getPlayers().getAllPlayers();

        assertAll(
                () -> assertThat(players.get(0).getCards()).hasSize(2),
                () -> assertThat(players.get(1).getCards()).hasSize(2),
                () -> assertThat(players.get(2).getCards()).hasSize(2)
        );
    }

    @Test
    @DisplayName("플레이어에게 한 장을 추가한다.")
    void givenPlayer_thenGivesCard() {
        BlackJack blackJack = new BlackJack(GIVEN_NAMES, cardSize -> 0);
        blackJack.initializeCardsOfPlayers();
        List<PlayerReadOnly> participants = blackJack.getParticipants();

        blackJack.giveCard(participants.get(1));

        List<Card> cards = participants.get(1).getCards();
        assertThat(cards).hasSize(3);
    }

    @Test
    @DisplayName("딜러의 총 점수가 16 이하인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrLessSixTeen() {
        BlackJack blackJack = new BlackJack(GIVEN_NAMES, cardSize -> 5);
        blackJack.initializeCardsOfPlayers();

        assertThat(blackJack.shouldDealerGetCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 총 점수가 17 이상인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrMoreSevenTeen() {
        BlackJack blackJack = new BlackJack(GIVEN_NAMES, cardSize -> 7);
        blackJack.initializeCardsOfPlayers();

        assertThat(blackJack.shouldDealerGetCard()).isFalse();
    }

    @Test
    @DisplayName("딜러에게 한 장의 카드를 추가한다.")
    void thenGiveDealerCard() {
        BlackJack blackJack = new BlackJack(GIVEN_NAMES, cardSize -> 0);
        blackJack.initializeCardsOfPlayers();

        blackJack.giveCardToDealer();

        assertThat(blackJack.getPlayers().getDealer().getCards()).hasSize(3);
    }
}