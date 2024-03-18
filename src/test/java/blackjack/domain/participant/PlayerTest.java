package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.BlackjackCardsFactory;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Score;
import blackjack.domain.state.HitParticipantState;
import blackjack.domain.state.InitialParticipantState;
import blackjack.domain.state.StandParticipantState;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어를 생성하면 초기 상태를 가진다")
    @Test
    public void create() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));

        assertThat(player.getState()).isInstanceOf(InitialParticipantState.class);
    }

    @DisplayName("플레이어는 카드를 뽑으면 새로운 상태를 가진 플레이어를 반환한다")
    @Test
    public void draw() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);

        Player newPlayer = player.draw(deck);

        assertThat(player).isNotSameAs(newPlayer);
    }

    @DisplayName("플레이어의 상태가 종료되지 않았다면 true를 반환한다")
    @Test
    public void canDrawTrue() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));

        assertThat(player.canDraw()).isTrue();
    }

    @DisplayName("플레이어의 상태가 종료되었다면 false를 반환한다")
    @Test
    public void canDrawFalse() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        player = player.draw(deck); // 2장 초기화 KING, QUEEN -> 20

        Player newPlayer = player.draw(deck); // KING, QUEEN, JACK -> 30

        assertThat(newPlayer.canDraw()).isFalse();
    }

    @DisplayName("플레이어가 스탠드를 하면 새로운 상태를 가진 플레이어를 반환한다")
    @Test
    public void stand() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        player = player.draw(deck);

        Player newPlayer = player.stand();

        assertThat(newPlayer.getState()).isInstanceOf(StandParticipantState.class);
    }

    @DisplayName("플레이어의 스코어를 계산한다")
    @Test
    public void calculate() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);
        player = player.draw(deck);

        assertThat(player.calculateHand()).isEqualTo(Score.from(20));
    }

    @DisplayName("카드를 더 받겠다고 입력받고 카드를 더 드로우할 수 있다면 카드를 드로우한다")
    @Test
    public void decideHitOrStandDraw() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));
        Deck deck = Deck.of(new BlackjackCardsFactory(), cards -> cards);

        Player newPlayer = player.decideHitOrStand(true, deck);

        assertThat(newPlayer.getState()).isInstanceOf(HitParticipantState.class);
    }

    @DisplayName("카드를 더 드로우할 수 있어도, 스탠드하겠다고 입력받으면 스탠드한다")
    @Test
    public void decideHitOrStandStand() {
        Player player = Player.createInitialStatePlayer(new Name("이상"));
        Deck deck = Deck.of(() -> List.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                        CardFixture.fromSuitCloverWith(Denomination.KING), CardFixture.fromSuitCloverWith(Denomination.JACK)),
                cards -> cards);
        player = player.draw(deck);

        Player newPlayer = player.decideHitOrStand(false, deck);

        assertThat(newPlayer.getState()).isInstanceOf(StandParticipantState.class);
    }
}
