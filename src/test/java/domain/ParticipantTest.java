package domain;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 플레이어의_핸드_점수가_21을_초과하면_카드를_더_받을_수_없다() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6 순서 고정
        Player player = new Player(Name.from("나무"));

        gameManager.dealCard(player); // A
        gameManager.dealCard(player); // K
        gameManager.dealCard(player); // Q
        gameManager.dealCard(player); // J

        Assertions.assertThat(player.canReceive()).isFalse();
    }

    @Test
    void 플레이어의_핸드_점수가_21_이하면_카드를_더_받을_수_있다() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6 순서 고정
        Player player = new Player(Name.from("나무"));

        gameManager.dealCard(player); // A
        gameManager.dealCard(player); // K
        gameManager.dealCard(player); // Q

        Assertions.assertThat(player.canReceive()).isTrue();
    }

    @Test
    void 딜러의_핸드_점수가_16_이하면_카드를_더_받을_수_있다() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6 순서 고정
        Dealer dealer = new Dealer();

        gameManager.dealCard(dealer); // A
        gameManager.dealCard(dealer); // K

        Assertions.assertThat(dealer.canReceive()).isFalse();
    }

    @Test
    void 딜러의_핸드_점수가_17_이상이면_카드를_더_받을_수_없다() {
        GameManager gameManager = new GameManager(Deck.create());

        // A K Q J 10 9 8 7 6 순서 고정
        Dealer dealer = new Dealer();

        gameManager.dealCard(dealer); // A
        gameManager.dealCard(dealer); // K
        gameManager.dealCard(dealer); // Q

        Assertions.assertThat(dealer.canReceive()).isFalse();
    }

    @Test
    void 이름이_같으면_동일한_참가자로_판단한다() {
        Participant participant1 = new Player(Name.from("나무"));
        Participant participant2 = new Player(Name.from("나무"));

        Assertions.assertThat(participant1).isEqualTo(participant2);
    }

    @Test
    void 이름이_다르면_다른_참가자로_판단한다() {
        Participant participant1 = new Player(Name.from("나무"));
        Participant participant2 = new Player(Name.from("고래"));

        Assertions.assertThat(participant1).isNotEqualTo(participant2);
    }
}
