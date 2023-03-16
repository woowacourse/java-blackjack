package blackjack.blackjacGameTest;

import static blackjack.Fixtures.BET_AMOUNT_10000;
import static blackjack.Fixtures.BLACKJACK_CARDS;
import static blackjack.Fixtures.CARDS_OF_BUST;
import static blackjack.Fixtures.CARDS_SUM_17;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.deck.CardsGenerator;
import blackjack.domain.deck.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.ParticipantPrizeDto;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Player;
import blackjack.domain.participant.player.Players;
import blackjack.fixedCaradsGenerator.FixedCardsGenerator;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    BlackjackGame blackjackGame;
    Players players;
    Dealer dealer;
    Deck deck;

    @BeforeEach
    void setUp() {

        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator();
        deck = new Deck(fixedCardsGenerator);
        players = new Players();
        dealer = new Dealer();
        blackjackGame = new BlackjackGame(players, dealer, deck);
    }

    @Test
    @DisplayName("플레이어를 만들고 플레이어즈에 추가할 수 있다")
    void addPlayer() {
        Player polo = new Player(new Name("폴로"), BET_AMOUNT_10000);
        blackjackGame.addPlayer(polo);

        assertThat(blackjackGame).extracting("players")
                .isNotSameAs(players);
    }

    @Test
    @DisplayName("딜러에게 카드를 두 장 줄 수 있다.")
    void supplyCardsToDealer() {
        blackjackGame.supplyCardsToDealer();

        assertThat(dealer.showCards())
                .contains(new Card(CardNumber.ACE, Pattern.DIAMOND), new Card(CardNumber.ACE, Pattern.SPADE));
    }

    @Test
    @DisplayName("플레이어들에게 카드를 두 장씩 줄 수 있다.")
    void supplyCardsToPlayers() {
        Player player1 = new Player(new Name("폴로"), BET_AMOUNT_10000);
        Player player2 = new Player(new Name("로지"), BET_AMOUNT_10000);
        blackjackGame.addPlayer(player1);
        blackjackGame.addPlayer(player2);

        blackjackGame.supplyCardsToPlayers();

        assertThat(player1.showCards()).contains(new Card(CardNumber.ACE, Pattern.DIAMOND),
                new Card(CardNumber.ACE, Pattern.SPADE));
        assertThat(player2.showCards()).contains(new Card(CardNumber.ACE, Pattern.CLOVER),
                new Card(CardNumber.ACE, Pattern.HEART));

    }

    @Test
    @DisplayName("플레이어와 딜러의 최종 수익을 반환할 수 있다.")
    void calculatePrize() {
        //given
        dealer.hit(CARDS_SUM_17);
        Player blackjack = new Player(new Name("폴로"), BET_AMOUNT_10000);
        blackjack.hit(BLACKJACK_CARDS);
        Player bust = new Player(new Name("로지"), BET_AMOUNT_10000);
        bust.hit(CARDS_OF_BUST);
        blackjackGame.addPlayer(blackjack);
        blackjackGame.addPlayer(bust);

        List<ParticipantPrizeDto> prize = blackjackGame.calculatePrize();

        assertThat(prize.stream().map(ParticipantPrizeDto::getParticipantName)).contains("딜러", "폴로", "로지");
    }

    @Test
    @DisplayName("플레이어와 딜러의 최종 수익을 반환할 수 있다.")
    void calculateDealerPriceValueExactly() {
        //given
        dealer.hit(CARDS_SUM_17);
        Player blackjack = new Player(new Name("폴로"), BET_AMOUNT_10000);
        blackjack.hit(BLACKJACK_CARDS);
        Player bust = new Player(new Name("로지"), BET_AMOUNT_10000);
        bust.hit(CARDS_OF_BUST);
        blackjackGame.addPlayer(blackjack);
        blackjackGame.addPlayer(bust);
        //when
        List<ParticipantPrizeDto> participantsPrize = blackjackGame.calculatePrize();
        ParticipantPrizeDto dealerPrize = participantsPrize.stream()
                .filter(dto -> dto.getParticipantName() == "딜러").findFirst().orElseThrow();
        //then
        assertThat(dealerPrize.getPrizeAmount()).isEqualTo(-5000);
    }

    @DisplayName("딜러에게 추가 카드를 주는 기능")
    @Nested
    class SupplyAdditionalCardToDealer {
        @Test
        @DisplayName("언더 스코어가 아니면 카드를 주지 않는다.")
        void doesNotSupply() {
            dealer.hit(
                    Arrays.asList(new Card(CardNumber.KING, Pattern.SPADE), new Card(CardNumber.KING, Pattern.HEART)));
            int beforeSize = dealer.showCards().size();
            blackjackGame.supplyAdditionalCardToDealerAnd(ignore -> {
            });
            int afterSize = dealer.showCards().size();

            assertThat(afterSize).isEqualTo(beforeSize);
        }

        @Test
        @DisplayName("언더 스코어 이면 카드를 받는다.")
        void supply() {
            dealer.hit(
                    Arrays.asList(new Card(CardNumber.KING, Pattern.HEART), new Card(CardNumber.SIX, Pattern.DIAMOND)));
            int beforeSize = dealer.showCards().size();
            blackjackGame.supplyAdditionalCardToDealerAnd(ignore -> {
            });
            int afterSize = dealer.showCards().size();

            assertThat(afterSize - beforeSize).isEqualTo(1);
        }
    }
}
