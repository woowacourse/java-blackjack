package blackjack.blackjacgame;

import static blackjackgame.Result.LOSE;
import static blackjackgame.Result.TIE;
import static blackjackgame.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import betting.BettingAmount;
import betting.PlayersBettingTable;
import blackjack.fixedcaradsgenerator.FixedCardsGenerator;
import blackjackgame.BlackjackGame;
import blackjackgame.Result;
import card.Card;
import card.Rank;
import card.Suit;
import deck.CardsGenerator;
import deck.Deck;
import dto.DealerWinningDto;
import participants.Count;
import participants.Dealer;
import participants.Name;
import participants.Participants;
import participants.Player;
import participants.Players;

class BlackjackGameTest {
    BlackjackGame blackjackGame;
    Dealer dealer;
    Players players;
    Participants participants;
    Deck deck;
    PlayersBettingTable playersBettingTable;

    @BeforeEach
    void setUp() {
        List<Card> cards = List.of(
                new Card(Rank.ACE, Suit.HEART),
                new Card(Rank.ACE, Suit.CLOVER),
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.ACE, Suit.DIAMOND)
        );
        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator(cards);
        deck = new Deck(fixedCardsGenerator);
        dealer = new Dealer();
        players = new Players();
        playersBettingTable = new PlayersBettingTable();
        participants = new Participants(dealer, players);
        blackjackGame = new BlackjackGame(participants, deck, playersBettingTable);
    }

    @Test
    @DisplayName("플레이어를 만들고 플레이어즈에 추가한다.")
    void addPlayer() {
        blackjackGame.addPlayers(List.of("폴로", "로지"));

        assertThat(participants.getPlayersCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가인원중 중복된 이름이 있으면 예외가 발생한다.")
    void validateDuplicateName() {
        List<String> names = List.of("폴로", "로지", "폴로", "에단", "아코", "주노", "리오");

        assertThatThrownBy(() -> blackjackGame.addPlayers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("참가인원이 6명을 초과하면 예외를 발생한다")
    void validateMaxPlayer() {
        List<String> names = List.of("폴로", "로지", "마코", "에단", "아코", "주노", "리오");

        assertThatThrownBy(() -> blackjackGame.addPlayers(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가인원은 최대 6명입니다.");
    }

    @Test
    @DisplayName("딜러에게 카드를 두 장 준다.")
    void supplyCardsToDealer() {
        blackjackGame.supplyCardsToDealer();

        assertThat(dealer.showCards())
                .contains(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.ACE, Suit.SPADE));
    }

    @Test
    @DisplayName("플레이어들에게 카드를 두 장씩 준다.")
    void supplyCardsToPlayers() {
        Player player1 = new Player(new Name("폴로"));
        Player player2 = new Player(new Name("로지"));
        players.add(player1);
        players.add(player2);

        blackjackGame.supplyCardsToPlayers();

        assertThat(player1.showCards()).contains(new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.ACE, Suit.SPADE));
        assertThat(player2.showCards()).contains(new Card(Rank.ACE, Suit.CLOVER),
                new Card(Rank.ACE, Suit.HEART));

    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어는 카드를 한장 추가로 받는다.")
    void supplyAdditionalCard() {
        Player player1 = new Player(new Name("폴로"));
        players.add(player1);
        blackjackGame.supplyCardsToPlayers();

        blackjackGame.supplyAdditionalCard(0);

        assertThat(player1.showCards()).contains(new Card(Rank.ACE, Suit.DIAMOND),
                new Card(Rank.ACE, Suit.SPADE), new Card(Rank.ACE, Suit.CLOVER));
    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어가 버스트인 경우 true를 반환한다.")
    void isBust() {
        List<Card> cards = List.of(
                new Card(Rank.KING, Suit.HEART),
                new Card(Rank.KING, Suit.DIAMOND),
                new Card(Rank.KING, Suit.SPADE),
                new Card(Rank.KING, Suit.CLOVER)
        );

        Deck fixedDeck = new Deck(new FixedCardsGenerator(cards));
        blackjackGame = new BlackjackGame(participants, fixedDeck, playersBettingTable);

        Player player1 = new Player(new Name("폴로"));
        players.add(player1);
        blackjackGame.supplyCardsToPlayers();
        blackjackGame.supplyAdditionalCard(0);

        assertThat(blackjackGame.isBust(0)).isTrue();
    }

    @Test
    @DisplayName("인덱스에 해당하는 플레이어가 버스트가 아닌경우 false를 반환한다.")
    void isBustFalse() {
        Player player1 = new Player(new Name("폴로"));
        players.add(player1);
        blackjackGame.supplyCardsToPlayers();
        blackjackGame.supplyAdditionalCard(0);

        assertThat(blackjackGame.isBust(0)).isFalse();
    }


    @Test
    @DisplayName("현재 플레이어의 인원수를 반환한다.")
    void countPlayers() {
        blackjackGame.addPlayers(List.of("폴로", "로지"));

        assertThat(blackjackGame.countPlayer()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러에게 추가카드를 준다.")
    void supplyAdditionalCardToDealer() {
        int beforeSize = dealer.showCards().size();
        blackjackGame.supplyAdditionalCardToDealer();
        int afterSize = dealer.showCards().size();

        assertThat(afterSize - beforeSize).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 게임 결과를 반환한다.")
    void getDealerWinningResult() {
        dealer.win();
        dealer.win();
        dealer.lose();
        dealer.tie();

        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        Name name = dealerWinningResult.getName();
        Map<Result, Count> dealerResult = dealerWinningResult.getWinningMap();

        assertThat(name.getValue()).isEqualTo("딜러");
        assertThat(dealerResult.get(WIN).getCount()).isEqualTo(2);
        assertThat(dealerResult.get(LOSE).getCount()).isEqualTo(1);
        assertThat(dealerResult.get(TIE).getCount()).isEqualTo(1);
    }

    @Nested
    @DisplayName("딜러가 카드를 추가로 받을 수 있는지 확인하는 기능")
    class canDealerHitTest {
        @Test
        @DisplayName("딜러가 버스트가 아니고 언더스코어인 경우")
        void canDealerHitUnderScoreAndNotBust() {
            dealer.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.TWO, Suit.SPADE));

            assertThat(blackjackGame.canDealerHit()).isTrue();
        }

        @Test
        @DisplayName("딜러가 버스트인경우")
        void cantDealerHitCuzBust() {
            dealer.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.KING, Suit.SPADE));
            dealer.hit(new Card(Rank.TWO, Suit.SPADE));

            assertThat(blackjackGame.canDealerHit()).isFalse();
        }

        @Test
        @DisplayName("버스트가 아니고, 언더스코어가 아닌경우")
        void canDealerHit() {
            dealer.hit(new Card(Rank.KING, Suit.HEART));
            dealer.hit(new Card(Rank.EIGHT, Suit.SPADE));

            assertThat(blackjackGame.canDealerHit()).isFalse();
        }

        @Test
        @DisplayName("이름과 배팅 금액을 입력 받아 베팅맵에 저장한다")
        void saveBetAmount() {
            Player player = new Player(new Name("폴로"));
            players.add(player);
            player.win();

            blackjackGame.saveBetAmount("폴로", 10000);

            BettingAmount amount = playersBettingTable.getBettingAmountByPlayer(player);
            assertThat(amount.getAmount()).isEqualTo(10000);
        }
    }
}
