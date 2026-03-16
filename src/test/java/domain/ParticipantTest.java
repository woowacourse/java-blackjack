package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 플레이어_핸드_점수가_21점_미만이면_계속_받을_수_있다() {
        GameManager gameManager = createCustomManager(Rank.TWO, Rank.THREE, Rank.FOUR);
        Player player = new Player(Name.from("나무"));

        gameManager.dealCard(player);
        gameManager.dealCard(player);
        gameManager.dealCard(player);

        assertThat(player.canReceive()).isTrue();
    }

    @Test
    void 플레이어_핸드_점수가_21점_초과시_자동으로_버스트되어_더_받을_수_없다() {
        GameManager gameManager = createCustomManager(Rank.TEN, Rank.TEN, Rank.TWO);
        Player player = new Player(Name.from("나무"));

        gameManager.dealCard(player);
        gameManager.dealCard(player);
        gameManager.dealCard(player);

        assertThat(player.canReceive()).isFalse();
    }

    @Test
    void 딜러_핸드_점수가_16점_이하이면_더_받을_수_있다() {
        // 10 + 6 = 16점
        GameManager gameManager = createCustomManager(Rank.TEN, Rank.SIX);
        Dealer dealer = new Dealer();

        gameManager.dealCard(dealer);
        gameManager.dealCard(dealer);

        assertThat(dealer.canReceive()).isTrue();
    }

    @Test
    void 딜러_핸드_점수가_16점_초과면_규칙에_따라_더_받을_수_없다() {
        // 10 + 7 = 17점
        GameManager gameManager = createCustomManager(Rank.TEN, Rank.SEVEN);
        Dealer dealer = new Dealer();

        gameManager.dealCard(dealer);
        gameManager.dealCard(dealer);

        assertThat(dealer.canReceive()).isFalse();
    }

    // 헬퍼 메서드: 테스트용 카드 배치를 위한 매니저 생성
    private GameManager createCustomManager(Rank... ranks) {
        Deck deck = Deck.create();
        deck.shuffle(cards -> {
            cards.clear();
            for (Rank rank : ranks) {
                cards.add(new Card(Suit.SPADE, rank));
            }
        });
        return new GameManager(deck);
    }
}
