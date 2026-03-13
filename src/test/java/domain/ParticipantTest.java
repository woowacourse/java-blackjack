package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 플레이어의_핸드_점수가_21을_초과하면_카드를_더_받을_수_없다() {
        Player player = new Player(Name.from("고래"));
        player.add(new Card(Suit.SPADE, Rank.TWO));
        player.add(new Card(Suit.SPADE, Rank.KING));
        player.add(new Card(Suit.DIAMOND, Rank.KING));

        Assertions.assertThat(player.canReceive()).isFalse();
    }

    @Test
    void 플레이어의_핸드_점수가_21_이하면_카드를_더_받을_수_있다() {
        Player player = new Player(Name.from("나무"));
        player.add(new Card(Suit.SPADE, Rank.ACE));
        player.add(new Card(Suit.SPADE, Rank.KING));

        Assertions.assertThat(player.canReceive()).isTrue();
    }

    @Test
    void 딜러의_핸드_점수가_16_이하면_카드를_더_받을_수_있다() {
        Dealer dealer = new Dealer();
        dealer.add(new Card(Suit.SPADE, Rank.TEN));
        dealer.add(new Card(Suit.SPADE, Rank.SIX));

        Assertions.assertThat(dealer.canReceive()).isTrue();
    }

    @Test
    void 딜러의_핸드_점수가_17_이상이면_카드를_더_받을_수_없다() {
        Dealer dealer = new Dealer();
        dealer.add(new Card(Suit.SPADE, Rank.TEN));
        dealer.add(new Card(Suit.SPADE, Rank.SEVEN));

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
