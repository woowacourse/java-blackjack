package blackjack.domain.player;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPack;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import blackjack.domain.card.RandomBlackjackShuffle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("여러 플레이어 테스트")
class PlayersTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);

    @Test
    @DisplayName("플레이어 목록을 추가한다")
    void player_List() {
        Players players = new Players(
                new Dealer(),
                List.of(new Gambler("두리", 0), new Gambler("비타", 0))
        );

        assertAll(
                () -> assertThat(players.getGamblers().size()).isEqualTo(2),
                () -> assertThat(players.getDealer()).isNotNull()
        );
    }

    @Test
    @DisplayName("모든 플레이어의 카드를 초기화한다")
    void 모든_플레이어의_카드를_초기화한다() {
        CardPack cardPack = new CardPack(new RandomBlackjackShuffle());

        List<Gambler> gamblers = List.of(
                new Gambler("비타", 0),
                new Gambler("두리", 0)
        );

        Players players = new Players(new Dealer(), gamblers);
        players.distributeInitialCards(() -> cardPack.getDealByCount(2));

        for (Gambler gambler : gamblers) {
            assertThat(gambler.getHand().getCards().getCards())
                    .hasSize(2);
        }

        assertThat(gamblers.getFirst().getHand().getCards().getCards())
                .isNotEqualTo(gamblers.getLast().getHand().getCards());
    }

    @Test
    @DisplayName("딜러에게 카드를 추가한다")
    void 딜러에게_카드를_추가한다() {
        Players players = new Players(new Dealer(), List.of());

        Cards cards = new Cards(List.of(ACE));
        players.addCardForDealer(cards);

        List<Card> result = players.getDealer().getHand().getCards().getCards();

        assertThat(result).contains(ACE);
    }

    @Test
    @DisplayName("참가자에게 카드를 추가한다")
    void 참가자에게_카드를_추가한다() {
        Gambler gambler = new Gambler("비타", 0);
        Players players = new Players(new Dealer(), List.of(gambler));

        Cards cards = new Cards(List.of(ACE));
        players.addCardForGambler(gambler, cards);

        List<Card> result = players.getGamblers().getFirst().getHand().getCards().getCards();

        assertThat(result).contains(ACE);
    }

    @Test
    @DisplayName("딜러가 히트인 경우 TRUE 를 반환한다")
    void the_dealers_card_is_less_than_16_or_less() {
        Players players = new Players(new Dealer(), List.of());

        boolean result = players.isDealerHit();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("최종 수익을 종합해 반환한다")
    void calculateFinalEarnings() {
        Gambler gambler = new Gambler("비타", 0);
        Players players = new Players(new Dealer(), List.of(gambler));

        GameResult gameResult = players.createGameResult();

        assertThat(gameResult).isNotNull();
    }

    @Test
    @DisplayName("플레이어의 이름은 중복 될 수 없다")
    void the_player_s_name_is_duplicate___no() {
        assertThatThrownBy(() -> new Players(new Dealer(), List.of(new Gambler("두리", 0), new Gambler("두리", 0))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복 될 수 없습니다.");
    }
}
