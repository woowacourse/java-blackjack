package game;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardHand;
import card.Deck;
import card.Rank;
import card.Suit;
import paticipant.Dealer;
import paticipant.Participant;
import paticipant.Player;
import paticipant.Players;
import value.Count;
import value.Score;

public class BlackjackTest {
	private static final Count ZERO = Count.from(0);
	private static final Count ONE = Count.from(1);

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
			assertSoftly(s -> {
				s.assertThat(blackjack.getPlayers().getPlayers()).hasSize(3);
				s.assertThat(blackjack.getDealer()).isNotNull();
				s.assertThat(blackjack.getDeck()).isNotNull();
			});
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
			final Function<String, Boolean> alwaysYes = (name) -> true;
			final Score bustScore = Score.from(21);

			// when
			blackjack.pickCardPlayersIfNotBust(alwaysYes, x -> {
			});

			// then
			assertSoftly(s -> {
				for (final Player player : blackjack.getPlayers().getPlayers()) {
					s.assertThat(
							player.getParticipant().getCardHand().calculateAllScore(bustScore).isGreaterThan(bustScore))
						.isTrue();
				}
			});
		}
	}

	@Nested
	@DisplayName("딜러 추가 카드 뽑기")
	class PickCardDealerIfNotMax {

		@DisplayName("딜러가 16점 이하라면, 카드를 추가로 뽑는다.")
		@Test
		void pickCardDealerIfNotMax() {
			// given
			final Blackjack blackjack = Blackjack.from(List.of(""));

			// when
			blackjack.pickCardDealerIfNotMax();

			// then
			assertThat(blackjack.getDealer().isPickCard()).isFalse();
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
			final int actual = blackjack.calculateScore(participant).value();

			// then
			assertThat(actual).isEqualTo(12);
		}
	}

	@Nested
	@DisplayName("듀얼을 진행")
	class Duel {

		@DisplayName("딜러와 모든 플레이들간의 듀얼을 진행한다.")
		@Test
		void duel() {
			// given
			final List<String> playerNames = List.of("부기", "파랑", "구구");
			final Blackjack blackjack = Blackjack.from(playerNames);

			// when
			blackjack.duel();

			// then
			assertSoftly(s -> {
				for (final Player player : blackjack.getPlayers().getPlayers()) {
					s.assertThat(player.getParticipant().getDuelHistory().getDrawCount()).isEqualTo(ONE);
				}
				s.assertThat(blackjack.getDealer().getParticipant().getDuelHistory().getDrawCount())
					.isEqualTo(Count.from(playerNames.size()));
			});
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
			final Blackjack blackjack = new Blackjack(new Players(List.of(player)), dealer, Deck.createShuffledDeck());

			// when
			blackjack.duel();

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(dealer.getParticipant().getDuelHistory().getDrawCount()).isEqualTo(ONE);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getDrawCount()).isEqualTo(ONE);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
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
			final Blackjack blackjack = new Blackjack(new Players(List.of(player)), dealer, Deck.createShuffledDeck());

			// when
			blackjack.duel();

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ONE);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ONE);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
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
			final Blackjack blackjack = new Blackjack(new Players(List.of(player)), dealer, Deck.createShuffledDeck());

			// when
			blackjack.duel();

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ONE);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ONE);
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
			final Blackjack blackjack = new Blackjack(new Players(List.of(player)), dealer, Deck.createShuffledDeck());

			// when
			blackjack.duel();

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ONE);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ONE);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
			});
		}

		@DisplayName("동점이라면, 무승부를 추가하라")
		@Test
		void duel3() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB));
			final Participant playerParticipant = new Participant(new CardHand(playerCards));
			final Participant dealerParticipant = new Participant(new CardHand(dealerCards));
			final Player player = new Player(" ", playerParticipant);
			final Dealer dealer = new Dealer(dealerParticipant);
			final Blackjack blackjack = new Blackjack(new Players(List.of(player)), dealer, Deck.createShuffledDeck());

			// when
			blackjack.duel();

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(dealer.getParticipant().getDuelHistory().getDrawCount()).isEqualTo(ONE);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getDrawCount()).isEqualTo(ONE);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
			});
		}

		@DisplayName("딜러와 플레이어 모두 버스트라면, 무승부를 추가하라")
		@Test
		void duel4() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.HEART),
				new Card(Rank.TEN, Suit.SPADE));
			final List<Card> dealerCards = List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.THREE, Suit.HEART),
				new Card(Rank.TEN, Suit.SPADE));
			final Participant playerParticipant = new Participant(new CardHand(playerCards));
			final Participant dealerParticipant = new Participant(new CardHand(dealerCards));
			final Player player = new Player(" ", playerParticipant);
			final Dealer dealer = new Dealer(dealerParticipant);
			final Blackjack blackjack = new Blackjack(new Players(List.of(player)), dealer, Deck.createShuffledDeck());

			// when
			blackjack.duel();

			// then
			assertSoftly(s -> {
				s.assertThat(dealer.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(dealer.getParticipant().getDuelHistory().getDrawCount()).isEqualTo(ONE);
				s.assertThat(dealer.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(ZERO);
				s.assertThat(player.getParticipant().getDuelHistory().getDrawCount()).isEqualTo(ONE);
				s.assertThat(player.getParticipant().getDuelHistory().getLoseCount()).isEqualTo(ZERO);
			});
		}
	}
}
