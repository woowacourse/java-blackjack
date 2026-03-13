package team.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    public Dealer dealer;
    public Player pobi;
    public Player jason;
    public Players players;
    public BlackjackGame game;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        pobi = new Player("pobi");
        jason = new Player("jason");
        players = new Players(List.of(
                pobi,
                jason
        ));
        game = new BlackjackGame(dealer, players);
    }

    @Test
    void 초기화_이후에는_참여자_모두_카드_2장() {
        game.drawInitialCards();

        assertThat(game.getAllPlayerNames()).containsExactly("pobi", "jason");
        assertThat(game.getPlayerCardsByName("pobi")).hasSize(2);
        assertThat(game.getPlayerCardsByName("jason")).hasSize(2);
        assertThat(game.getDealerCards()).hasSize(2);
    }

    @Test
    void batMoney_호출시_해당_플레이어_배팅금이_설정된다() {
        int pobiBatMoney = 1000;
        int jasonBatMoney = 500;
        game.batMoney("pobi", pobiBatMoney);
        game.batMoney("jason", jasonBatMoney);

        assertThat(pobi.getBatMoney()).isEqualTo(pobiBatMoney);
        assertThat(jason.getBatMoney()).isEqualTo(jasonBatMoney);
    }

    @Test
    void 딜러의_수익은_플레이어_수익_합의_음수이다() {
        game.batMoney("pobi", 1000);
        game.batMoney("jason", 1000);
        game.drawInitialCards();

        Map<String, Double> playerRevenue = game.calculateAllPlayerRevenue();
        double expectedDealerRevenue = -playerRevenue.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        assertThat(game.calculateDealerRevenue()).isEqualTo(expectedDealerRevenue);
    }


    @Test
    void _30과_20에_대한_버스트_여부를_반환한다() {
        pobi.hit(Card.KING_OF_HEARTS);
        pobi.hit(Card.QUEEN_OF_HEARTS);
        pobi.hit(Card.JACK_OF_HEARTS); // 30으로 버스트

        jason.hit(Card.TEN_OF_HEARTS);
        jason.hit(Card.SIX_OF_HEARTS); // 16으로 버스트 아님

        boolean pobiShouldHit = game.isPlayerBust("pobi");
        boolean jasonShouldHit = game.isPlayerBust("jason");

        // shouldPlayerHit은 해당 플레이어의 버스트 여부를 반환 (현재 구현: isBust() 그대로 반환)
        assertThat(pobiShouldHit).isIn(true);
        assertThat(jasonShouldHit).isIn(false);
    }

    @Test
    void 플레이어는_hit을_하는_경우_카드가_한_장_늘어난다() {
        game.drawInitialCards();
        int sizeBefore = game.getPlayerCardsByName("pobi").size();

        game.hitPlayer("pobi");

        assertThat(game.getPlayerCardsByName("pobi")).hasSize(sizeBefore + 1);
    }

    @Test
    void 딜러는_점수가_17_미만이면_Hit을_해야한다() {
        game.drawInitialCards();
        int dealerScore = game.getDealerScore();

        boolean shouldHit = game.shouldDealerHit();

        assertThat(shouldHit).isEqualTo(dealerScore < 17);
    }

    @Test
    void 딜러가_hit하는경우_딜러_카드가_한_장_늘어난다() {
        game.drawInitialCards();
        int sizeBefore = game.getDealerCards().size();

        game.hitDealer();

        assertThat(game.getDealerCards()).hasSize(sizeBefore + 1);
    }

    @Test
    void getDealerScore_딜러_점수_반환() {
        game.drawInitialCards();

        int score = game.getDealerScore();

        assertThat(score).isBetween(0, 21);
    }

    @Test
    void 플레이어별_카드_목록_반환() {
        game.drawInitialCards();

        Map<String, List<Card>> allCards = game.getAllPlayerCards();

        assertThat(allCards).containsKeys("pobi", "jason");
        assertThat(allCards.get("pobi")).hasSize(2);
        assertThat(allCards.get("jason")).hasSize(2);
    }

    @Test
    void 플레이어_이름_목록_반환() {
        assertThat(game.getAllPlayerNames()).containsExactly("pobi", "jason");
    }

    // --- 실제 게임 흐름 시나리오 테스트 ---

    @Test
    void 한_판_진행_배팅_초기카드_수익계산_끝까지_정상_동작() {
        game.batMoney("pobi", 1000);
        game.batMoney("jason", 500);

        game.drawInitialCards();

        Map<String, Double> playerRevenue = game.calculateAllPlayerRevenue();
        double dealerRevenue = game.calculateDealerRevenue();

        assertThat(game.getAllPlayerNames()).containsExactly("pobi", "jason");
        assertThat(game.getPlayerCardsByName("pobi")).hasSize(2);
        assertThat(game.getPlayerCardsByName("jason")).hasSize(2);
        assertThat(game.getDealerCards()).hasSize(2);
        assertThat(playerRevenue).containsOnlyKeys("pobi", "jason");
        assertThat(dealerRevenue).isEqualTo(-playerRevenue.values().stream().mapToDouble(Double::doubleValue).sum());
    }

    @Test
    void 한_판_진행_플레이어_히트_후_수익계산() {
        game.batMoney("pobi", 1000);
        game.batMoney("jason", 1000);
        game.drawInitialCards();

        while (!game.isPlayerBust("pobi") && game.getPlayerCardsByName("pobi").size() < 5) {
            game.hitPlayer("pobi");
        }

        Map<String, Double> playerRevenue = game.calculateAllPlayerRevenue();
        double dealerRevenue = game.calculateDealerRevenue();

        assertThat(playerRevenue).containsOnlyKeys("pobi", "jason");
        assertThat(dealerRevenue).isEqualTo(-playerRevenue.values().stream().mapToDouble(Double::doubleValue).sum());
    }

    @Test
    void 한_판_진행_딜러_17까지_히트_후_수익계산() {
        game.batMoney("pobi", 1000);
        game.batMoney("jason", 1000);
        game.drawInitialCards();

        while (game.shouldDealerHit()) {
            game.hitDealer();
        }

        assertThat(game.getDealerScore()).isGreaterThanOrEqualTo(17);
        Map<String, Double> playerRevenue = game.calculateAllPlayerRevenue();
        double dealerRevenue = game.calculateDealerRevenue();
        assertThat(dealerRevenue).isEqualTo(-playerRevenue.values().stream().mapToDouble(Double::doubleValue).sum());
    }

    @Test
    void 한_게임의_전체_흐름_플레이어_히트_딜러_히트_수익계산() {
        game.batMoney("pobi", 1000);
        game.batMoney("jason", 1000);

        // 초기 카드 강제로 설정 (실제 게임에서는 랜덤이지만 테스트에서는 고정)
        // 19
        pobi.hit(Card.ACE_OF_SPADES);
        pobi.hit(Card.EIGHT_OF_HEARTS);

        // jason은 20으로 시작해서 히트해서 버스트 시도
        jason.hit(Card.TEN_OF_HEARTS);
        jason.hit(Card.KING_OF_HEARTS);
        jason.hit(Card.FIVE_OF_DIAMONDS); // 25로 버스트

        // 딜러는 17로 시작해서 히트해서 18로 만듦
        dealer.hit(Card.KING_OF_CLUBS);
        dealer.hit(Card.KING_OF_SPADES);

        Map<String, Double> playerRevenue = game.calculateAllPlayerRevenue();
        double dealerRevenue = game.calculateDealerRevenue();

        assertThat(playerRevenue).containsOnlyKeys("pobi", "jason");
        assertThat(dealerRevenue).isEqualTo(-playerRevenue.values().stream().mapToDouble(Double::doubleValue).sum());
    }
}
