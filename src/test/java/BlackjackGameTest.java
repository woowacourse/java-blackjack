import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private BlackjackGame blackjackGame;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        Player pobi = new Player(new PlayerName("pobi"));
        Player crong = new Player(new PlayerName("crong"));

        players = List.of(pobi, crong);
        blackjackGame = new BlackjackGame(players);
    }

    @DisplayName("게임 참가자에게 카드를 나눠줄 수 있다.")
    @Test
    void giveCardToSuccessTest() {
        Player pobi = players.get(0);

        blackjackGame.giveCardTo(pobi);

        Assertions.assertThat(pobi.cardSize()).isEqualTo(1);
    }

    @DisplayName("게임 시작 시, 모든 플레이어에게 두 장의 카드를 나눠준다.")
    @Test
    void giveInitCardsSuccessTest() {
        Player pobi = players.get(0);
        Player crong = players.get(1);

        blackjackGame.giveInitCards();

        Assertions.assertThat(pobi.cardSize()).isEqualTo(2);
        Assertions.assertThat(crong.cardSize()).isEqualTo(2);
    }

    @DisplayName("딜러는 카드의 합이 17이 넘을때 까지 추가 카드를 받아야 한다.")
    @Test
    void giveAdditionalCardToDealerSuccessTest(){
        blackjackGame.giveInitCards();
        blackjackGame.giveAdditionalCardToDealer();

        Participant dealer = blackjackGame.getDealer();

        Assertions.assertThat(dealer.calculateScore())
                .isGreaterThanOrEqualTo(17);
    }

    @DisplayName("딜러의 점수를 기준으로 결과를 결정한다.")
    @Test
    void resultSuccessTest() {
        Participant dealer = blackjackGame.getDealer();
        Card valueTen = new Card(Shape.DIAMOND, Number.TEN);
        Card valueNine = new Card(Shape.DIAMOND, Number.NINE);
        Card valueEight = new Card(Shape.DIAMOND, Number.EIGHT);
        giveCardsTo(dealer, List.of(valueTen, valueNine));

        Player pobi = players.get(0);
        giveCardsTo(pobi, List.of(valueTen, valueTen));

        Player crong = players.get(1);
        giveCardsTo(crong, List.of(valueTen, valueEight));

        blackjackGame.result();

        Assertions.assertThat(pobi.getResult()).isEqualTo(Result.WIN);
        Assertions.assertThat(crong.getResult()).isEqualTo(Result.LOSE);
        Assertions.assertThat(dealer.getResult()).contains(Result.LOSE, Result.WIN);
    }

    public void giveCardsTo(Participant participant, List<Card> cards){
        for (Card card : cards) {
            participant.receiveCard(card);
        }
    }
}
