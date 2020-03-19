package blackjack.player.domain;

//todo : 지금은 승, 패로 되어있는데 profit을 뱉도록 변경해야함.

// class DealerTest {
//
// 	@DisplayName("딜러와 겜블러가 비교하여 결과를 만들어 낸다.")
// 	@ParameterizedTest
// 	@CsvSource(value = {"TWO,LOSE", "FIVE,DRAW", "SIX,WIN"})
// 	void getReport(CardNumber cardNumber, GameResult expect) {
// 		//given
// 		Dealer dealer = new Dealer(new CardBundle());
// 		dealer.addCard(Card.of(Symbol.DIAMOND, CardNumber.FIVE));
//
// 		Player gambler = new Gambler(new CardBundle(), aPlayerInfo("bebop"));
// 		gambler.addCard(Card.of(Symbol.DIAMOND, cardNumber));
//
// 		//when
// 		GameReport report = dealer.createReport((Gambler)gambler);
// 		report.getProfit()
// 		//then
// 		assertThat(report).isEqualTo(expect);
// 	}
//
// 	@DisplayName("딜러의 카드패가 16을 초과하면 뽑을수 없다.")
// 	@ParameterizedTest
// 	@CsvSource(value = {"TEN,false", "NINE,true"})
// 	void isDrawable(CardNumber cardNumber, boolean expect) {
// 		//given
// 		Player dealer = new Dealer(new CardBundle());
// 		dealer.addCard(Card.of(Symbol.DIAMOND, CardNumber.SEVEN));
// 		dealer.addCard(Card.of(Symbol.DIAMOND, cardNumber));
//
// 		//when
// 		boolean drawable = dealer.isHit();
//
// 		//then
// 		assertThat(drawable).isEqualTo(expect);
// 	}
// }