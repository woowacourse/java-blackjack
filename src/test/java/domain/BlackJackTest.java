package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.player.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static domain.Textures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackJackTest {

    private static final Map<Name, Bet> GIVEN_NAMES_BETS = Map.of(
            Name.of("여우"), Bet.from(3000),
            Name.of("아벨"), Bet.from(3000)
    );

    @Test
    @DisplayName("게임 시작 시 플레이어들에게 카드를 2장씩 나눠준다.")
    void whenStartingGame_thenPerPlayerHavingTwoCard() {
        BlackJack blackJack = new BlackJack(
                Players.from(makeDealer(), Player.from(GIVEN_NAMES_BETS)),
                Deck.create(maxIndex -> 0)
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
        Player dealer = makeDealer();
        List<Player> gamblers = Player.from(GIVEN_NAMES_BETS);
        BlackJack blackJack = new BlackJack(
                Players.from(dealer, gamblers),
                Deck.create(maxIndex -> 0)
        );

        blackJack.giveCardToAllPlayers();

        assertThat(dealer.getCards()).hasSize(1);
        gamblers.forEach(participant -> assertThat(participant.getCards()).hasSize(1));
    }

    @Test
    @DisplayName("특정 플레이어에게 한 장을 추가한다.")
    void givenPlayer_thenGivesCard() {
        BlackJack blackJack = new BlackJack(
                Players.from(makeDealer(), Player.from(GIVEN_NAMES_BETS)),
                Deck.create(maxIndex -> 0)
        );
        PlayerReadOnly participant = blackJack.getParticipants().get(0);

        blackJack.giveCard(participant);

        assertThat(participant.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("딜러의 총 점수가 16 이하인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrLessSixTeen() {
        BlackJack blackJack = new BlackJack(
                Players.from(
                        makeDealerWithCards(List.of(SPADE_KING, HEART_SIX)),
                        Player.from(GIVEN_NAMES_BETS)
                ), null
        );
        assertThat(blackJack.shouldDealerGetCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 총 점수가 17 이상인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrMoreSevenTeen() {
        BlackJack blackJack = new BlackJack(
                Players.from(
                        makeDealerWithCards(List.of(SPADE_KING, HEART_SEVEN)),
                        Player.from(GIVEN_NAMES_BETS)
                ), null
        );
        assertThat(blackJack.shouldDealerGetCard()).isFalse();
    }

    @Test
    @DisplayName("딜러에게 한 장의 카드를 추가한다.")
    void thenGiveDealerCard() {
        List<Card> cards = new ArrayList<>();
        Player dealer = makeDealerWithCards(cards);
        BlackJack blackJack = new BlackJack(
                Players.from(dealer, Player.from(GIVEN_NAMES_BETS)),
                Deck.create(maxIndex -> 0)
        );

        blackJack.giveCardToDealer();

        assertThat(cards).hasSize(1);
    }
}