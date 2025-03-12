package domain.game;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import domain.paticipant.Dealer;
import domain.paticipant.Participant;
import domain.paticipant.Player;

public class BlackjackTest {

	@Nested
	@DisplayName("블랙잭 게임 생성")
	class BlackjackConstruct {

		@DisplayName("블랙잭 게임을 생성한다.")
		@Test
		void blackjackConstruct() {
			// given
			final List<String> names = List.of("부기", "파랑", "히스타");

			// when
			final Blackjack blackjack = Blackjack.from(names);

			// then
			assertThat(blackjack.getPlayers().getPlayers()).hasSize(3);
			assertThat(blackjack.getDealer()).isNotNull();
			assertThat(blackjack.getDeck()).isNotNull();
		}
	}

	@Nested
	@DisplayName("블랙잭 초기 카드 분배")
	class InitPickCard {

		@DisplayName("player들과 dealer에게 초기 카드를 뽑아준다.")
		@Test
		void initPickCard() {
			// given
			final List<String> names = List.of("부기", "파랑", "히스타");
			final Blackjack blackjack = Blackjack.from(names);

			// when
			blackjack.initPickCard();

			// then
			assertSoftly(s -> {
				for (final Player player : blackjack.getPlayers().getPlayers()) {
					s.assertThat(player.getParticipant().getCardHand().getCards()).hasSize(2);
				}
				s.assertThat(blackjack.getDealer().getParticipant().getCardHand().getCards()).hasSize(2);
			});
		}
	}

	@Nested
	@DisplayName("플레이어 추가 카드 뽑기")
	class PickCardPlayersIfNotBust {

		@DisplayName("플레이어가 21점 이하라면, 카드 뽑기 여부를 물어보고 추가로 뽑는다.")
		@Test
		void pickCardPlayersIfNotBust() {
			// given
			final List<String> names = List.of("부기", "파랑", "히스타");
			final Blackjack blackjack = Blackjack.from(names);
			final BooleanSupplier alwaysYes = () -> true;
			final int bustScore = 21;

			// when
			blackjack.pickCardPlayersIfNotBust(alwaysYes);

			// then
			assertSoftly(s -> {
				for (final Player player : blackjack.getPlayers().getPlayers()) {
					s.assertThat(player.getParticipant().getCardHand().calculateAllScore(bustScore))
						.isGreaterThan(bustScore);
				}
			});
		}

	}

	@Nested
	@DisplayName("플레이어 카드 뽑는 여부 체크")
	class IsPickCardByPlayer {

		@DisplayName("플레이어의 점수가 BUST_SCORE 이하라면 true를 반환한다.")
		@Test
		void isPickCardByPlayers() {
			// given
			final Card card1 = new Card(Rank.ACE, Suit.CLUB);
			final Card card2 = new Card(Rank.TEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Participant participant = new Participant(cardHand);
			final Player player = new Player("", participant);
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByPlayer(player);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("플레이어의 점수가 BUST_SCORE 초과라면 false를 반환한다.")
		@Test
		void isPickCardByPlayers1() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.TEN, Suit.CLUB);
			final Card card3 = new Card(Rank.TWO, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2, card3));
			final Participant participant = new Participant(cardHand);
			final Player player = new Player("", participant);
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByPlayer(player);

			// then
			assertThat(actual).isFalse();
		}

	}

	@Nested
	@DisplayName("딜러 카드 뽑는 여부 체크")
	class IsPickCardByDealer {

		@DisplayName("딜러 점수가 주어진 조건 이하라면 true를 반환한다.")
		@Test
		void isPickCardByDealer() {
			// given
			final Card card1 = new Card(Rank.SIX, Suit.CLUB);
			final Card card2 = new Card(Rank.TEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Dealer dealer = new Dealer(new Participant(cardHand));
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByDealer(dealer);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("딜러 점수가 주어진 조건 초과라면 false를 반환한다.")
		@Test
		void isPickCardByDealer1() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Dealer dealer = new Dealer(new Participant(cardHand));
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByDealer(dealer);

			// then
			assertThat(actual).isFalse();
		}
	}

	@Nested
	@DisplayName("BUST_SCORE를 기준으로 점수를 계산한다.")
	class CalculateScore {

		@DisplayName("BUST_SCORE를 기준으로 점수를 계산한다.")
		@Test
		void calculateScore() {
			// given
			final Card card1 = new Card(Rank.ACE, Suit.CLUB);
			final Card card2 = new Card(Rank.ACE, Suit.HEART);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Participant participant = new Participant(cardHand);
			final Blackjack blackjack = new Blackjack();

			// when
			final int actual = blackjack.calculateScore(participant);

			// then
			assertThat(actual).isEqualTo(12);
		}
	}

	@Nested
	@DisplayName("BUST_SCORE를 기준으로 duel을 진행한다.")
	class DuelDealerVsPlayer {

		@DisplayName("Dealer와 player는 결투를 진행한다.")
		@Test
		void duelDealerVsPlayer() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Participant participant = new Participant(cardHand);
			final Dealer dealer = new Dealer(new Participant(cardHand));
			final Player player = new Player("", participant);
			final Blackjack blackjack = new Blackjack();

			// when
			blackjack.duelDealerVsPlayer(dealer, player);

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(0);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(0);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
			});
		}

		@DisplayName("플레이어가 BUST가 아니면서 딜러보다 더 BUST_SCORE에 가깝다면 플레이어는 우승으로, 딜러는 패배로 기록한다.")
		@Test
		void duel() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.CLUB));
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final Participant playerParticipant = new Participant(new CardHand(playerCards));
			final Participant dealerParticipant = new Participant(new CardHand(dealerCards));
			final Player player = new Player(" ", playerParticipant);
			final Dealer dealer = new Dealer(dealerParticipant);
			final Blackjack blackjack = new Blackjack();

			// when
			blackjack.duelDealerVsPlayer(dealer, player);

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(0);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(0);
			});
		}

		@DisplayName("player의 점수가 BUST_SCORE 초과라면, 딜러가 우승한다.")
		@Test
		void duel1() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.SPADE),
				new Card(Rank.TEN, Suit.HEART));
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final Participant playerParticipant = new Participant(new CardHand(playerCards));
			final Participant dealerParticipant = new Participant(new CardHand(dealerCards));
			final Player player = new Player(" ", playerParticipant);
			final Dealer dealer = new Dealer(dealerParticipant);
			final Blackjack blackjack = new Blackjack();

			// when
			blackjack.duelDealerVsPlayer(dealer, player);

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(0);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(0);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
			});
		}

		@DisplayName("딜러의 점수가 bust이고, 플레이어가 BUST_SCORE 이하라면, 플레이어가 우승한다.")
		@Test
		void duel2() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.SPADE),
				new Card(Rank.TEN, Suit.HEART));
			final Participant playerParticipant = new Participant(new CardHand(playerCards));
			final Participant dealerParticipant = new Participant(new CardHand(dealerCards));
			final Player player = new Player(" ", playerParticipant);
			final Dealer dealer = new Dealer(dealerParticipant);
			final Blackjack blackjack = new Blackjack();

			// when
			blackjack.duelDealerVsPlayer(dealer, player);

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(0);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(0);
			});
		}

		@DisplayName("동점이라면, 딜러가 우승한다.")
		@Test
		void duel3() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final Participant playerParticipant = new Participant(new CardHand(playerCards));
			final Participant dealerParticipant = new Participant(new CardHand(dealerCards));
			final Player player = new Player(" ", playerParticipant);
			final Dealer dealer = new Dealer(dealerParticipant);
			final Blackjack blackjack = new Blackjack();

			// when
			blackjack.duelDealerVsPlayer(dealer, player);

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(1);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(0);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(0);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(1);
			});
		}
	}

	@Nested
	@DisplayName("딜러와 플레이어에게 카드를 제공하라")
	class PickCard {

		@DisplayName("플레이어에게 카드를 한 장 제공하라")
		@Test
		void pickCardToPlayer() {
			// given
			final Player player = new Player("");
			final Blackjack blackjack = new Blackjack();
			final Deck deck = Deck.createShuffledDeck();

			// when
			blackjack.pickCard(player, deck);

			// then
			assertThat(player.getParticipant().getCardHand().getCards()).hasSize(1);
		}

		@DisplayName("딜러에게 카드를 한 장 제공하라")
		@Test
		void pickCardToDealer() {
			// given
			final Dealer dealer = new Dealer();
			final Blackjack blackjack = new Blackjack();
			final Deck deck = Deck.createShuffledDeck();

			// when
			blackjack.pickCard(dealer, deck);

			// then
			assertThat(dealer.getParticipant().getCardHand().getCards()).hasSize(1);
		}
	}
}
