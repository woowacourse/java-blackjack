// package domain;
//
// import static org.assertj.core.api.Assertions.*;
// import static org.junit.jupiter.api.Assertions.*;
//
// import java.util.List;
// import java.util.Map;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
//
// import domain.card.Card;
// import domain.card.Deck;
// import domain.card.RandomShuffleStrategy;
// import domain.card.Rank;
// import domain.card.Suit;
// import domain.game.Result;
// import domain.people.Dealer;
// import domain.people.Participant;
// import domain.people.Participants;
// import domain.people.Player;
//
// class ParticipantsTest {
//
//     private Participants participants;
//     private Deck deck;
//
//     @BeforeEach
//     void setUp() {
//         participants = Participants.from(List.of("leo", "reo", "reoleo"));
//         deck = Deck.from((orderedDeck) -> orderedDeck);
//     }
//
//     @Test
//     @DisplayName("참가자들의 이름에 문제가 없으면 참가자들을 정상적으로 생성한다.")
//     void participantsTest() {
//         assertThatCode(() -> Participants.from(List.of("leo", "reo", "reoleo"))).doesNotThrowAnyException();
//     }
//
//     @Test
//     @DisplayName("참가자들에게 카드를 한 장씩 나누어준다.")
//     void participantsDealTest() {
//         participants.findDealer().deal(Deck.from(new RandomShuffleStrategy()), participants.getParticipants());
//         List<Player> players = participants.findPlayers();
//         for (Player player : players) {
//             assertThat(player.getCardNames().size()).isEqualTo(1);
//         }
//     }
//
//     @Test
//     @DisplayName("플레이어(딜러 제외)의 목록을 반환한다.")
//     void findPlayersTest() {
//         assertThat(participants.findPlayers().size()).isEqualTo(3);
//     }
//
//     @Test
//     @DisplayName("딜러를 반환한다.")
//     void findDealerTest() {
//         assertThat(participants.findDealer()).isInstanceOf(Dealer.class);
//     }
//
//     @Test
//     @DisplayName("참가자들의 handValue를 계산한다.")
//     void getScores() {
//         participants.findDealer().deal(deck, participants.getParticipants());
//
//         Map<Participant, String> scores = participants.getScores();
//         for (Player player : participants.findPlayers()) {
//             assertThat(scores.get(player)).isEqualTo("10");
//         }
//     }
//
//     @Test
//     @DisplayName("플레이어의 게임 결과를 반환한다.")
//     void getPlayerResult() {
//         participants.findDealer().deal(deck, participants.getParticipants());
//         Map<String, Result> playerResults = participants.getPlayerResults();
//         for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
//             assertThat(playerResult.getValue()).isEqualTo(Result.LOSE);
//         }
//         assertThat(playerResults.size()).isEqualTo(3);
//     }
//
//     @Test
//     @DisplayName("딜러와 참가자가 버스트가 아니면, 더 높은 점수의 참가자가 승리한다.")
//     void NoBustResultTest() {
//         //given
//         Participants participant = Participants.from(List.of("abc"));
//         for (Player player : participant.findPlayers()) {
//             player.receiveCard(Card.from(Suit.CLOVER, Rank.TEN));
//         }
//
//         //when
//         participant.findDealer().receiveCard(Card.from(Suit.SPADE, Rank.ACE));
//
//         Map<String, Result> playerResults = participant.getPlayerResults();
//         Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);
//
//         //then
//         assertAll(
//             () -> assertThat(playerResults.get("abc")).isEqualTo(Result.LOSE),
//             () -> assertThat(dealerResults.get(Result.WIN)).isEqualTo(1));
//     }
//
//     @Test
//     @DisplayName("딜러와 참가자가 둘 다 버스트인 경우 무승부를 반환한다.")
//     void BothBustTest() {
//         //given
//         Participants participant = Participants.from(List.of("abc"));
//         for (Player player : participant.findPlayers()) {
//             player.receiveCard(Card.from(Suit.CLOVER, Rank.QUEEN));
//             player.receiveCard(Card.from(Suit.HEART, Rank.TEN));
//             player.receiveCard(Card.from(Suit.CLOVER, Rank.TWO));
//         }
//         Dealer dealer = participant.findDealer();
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.THREE));
//
//         //when
//         Map<String, Result> playerResults = participant.getPlayerResults();
//         Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);
//
//         //then
//         assertAll(
//             () -> assertThat(playerResults.get("abc")).isEqualTo(Result.TIE),
//             () -> assertThat(dealerResults.get(Result.TIE)).isEqualTo(1));
//     }
//
//     @Test
//     @DisplayName("딜러가 버스트이고 참가자가 버스트가 아닌 경우 참가자가 승리한다.")
//     void playerWinWhenDealerBust() {
//         //given
//         Participants participant = Participants.from(List.of("abc"));
//         for (Player player : participant.findPlayers()) {
//             player.receiveCard(Card.from(Suit.CLOVER, Rank.QUEEN));
//             player.receiveCard(Card.from(Suit.HEART, Rank.QUEEN));
//         }
//         Dealer dealer = participant.findDealer();
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.THREE));
//
//         //when
//         Map<String, Result> playerResults = participant.getPlayerResults();
//         Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);
//
//         //then
//         assertAll(
//             () -> assertThat(playerResults.get("abc")).isEqualTo(Result.WIN),
//             () -> assertThat(dealerResults.get(Result.LOSE)).isEqualTo(1));
//     }
//
//     @Test
//     @DisplayName("딜러가 버스트가 아니고 참가자가 버스트가 아닌 경우 딜러가 승리한다.")
//     void dealerWinWhenPlayerBust() {
//         //given
//         Participants participant = Participants.from(List.of("abc"));
//         for (Player player : participant.findPlayers()) {
//             player.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
//             player.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
//             player.receiveCard(Card.from(Suit.SPADE, Rank.THREE));
//         }
//         Dealer dealer = participant.findDealer();
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.QUEEN));
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.KING));
//
//         //when
//         Map<String, Result> playerResults = participant.getPlayerResults();
//         Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);
//
//         //then
//         assertAll(
//             () -> assertThat(playerResults.get("abc")).isEqualTo(Result.LOSE),
//             () -> assertThat(dealerResults.get(Result.WIN)).isEqualTo(1));
//     }
//
//     @Test
//     @DisplayName("딜러와 참가자가 버스트가 아니고, 동점인 경우 카드 개수가 적은 사람이 승리한다.")
//     void sameHandValueBothNoBust() {
//         //given
//         Participants participant = Participants.from(List.of("abc"));
//         for (Player player : participant.findPlayers()) {
//             player.receiveCard(Card.from(Suit.CLOVER, Rank.QUEEN));
//             player.receiveCard(Card.from(Suit.HEART, Rank.QUEEN));
//         }
//         Dealer dealer = participant.findDealer();
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.FOUR));
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.SIX));
//         dealer.receiveCard(Card.from(Suit.SPADE, Rank.TEN));
//
//         //when
//         Map<String, Result> playerResults = participant.getPlayerResults();
//         Map<Result, Integer> dealerResults = participant.getDealerResults(playerResults);
//
//         //then
//         assertAll(
//             () -> assertThat(playerResults.get("abc")).isEqualTo(Result.WIN),
//             () -> assertThat(dealerResults.get(Result.LOSE)).isEqualTo(1));
//     }
// }
