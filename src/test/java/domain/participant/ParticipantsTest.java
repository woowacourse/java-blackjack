package domain.participant;

import domain.BettingAmount;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("참가자 리스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ParticipantsTest {

    @Test
    void 딜러를_반환한다() {
        ParticipantName nameOfPlayer = new ParticipantName("player");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cardsOfPlayer = new Cards(List.of(new Card(Suit.SPADE, Rank.FOUR)));
        Participant player = new Player(nameOfPlayer, bettingAmount, cardsOfPlayer);

        Cards cardsOfDealer = new Cards(List.of(new Card(Suit.HEART, Rank.FOUR)));
        Participant dealer = new Dealer(cardsOfDealer);

        Participants participants = new Participants(List.of(player, dealer));

        assertThat(participants.findDealer()).isEqualTo(dealer);
    }

    @Test
    void 딜러가_존재하지_않으면_예외가_발생한다() {
        ParticipantName nameOfPlayer = new ParticipantName("player");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cardsOfPlayer = new Cards(List.of(new Card(Suit.SPADE, Rank.FOUR)));
        Participant player = new Player(nameOfPlayer, bettingAmount, cardsOfPlayer);

        Participants participants = new Participants(List.of(player));

        assertThatThrownBy(participants::findDealer)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 딜러가 존재하지 않는 상태입니다.");
    }

    @Test
    void 플레이어들을_반환한다() {
        ParticipantName nameOfPlayer = new ParticipantName("player");
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cardsOfPlayer = new Cards(List.of(new Card(Suit.SPADE, Rank.FOUR)));
        Participant player = new Player(nameOfPlayer, bettingAmount, cardsOfPlayer);

        Cards cardsOfDealer = new Cards(List.of(new Card(Suit.HEART, Rank.FOUR)));
        Participant dealer = new Dealer(cardsOfDealer);

        Participants participants = new Participants(List.of(player, dealer));

        assertThat(participants.findPlayers()).isEqualTo(List.of(player));
    }

    @Test
    void 플레이어별_이름과_카드리스트의_총합을_반환한다() {
        ParticipantName nameOfDrago = new ParticipantName("drago");
        BettingAmount bettingAmountOfDrago = new BettingAmount(10000);
        Cards cardsOfDrago = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Participant drago = new Player(nameOfDrago, bettingAmountOfDrago, cardsOfDrago);

        ParticipantName nameOfDuei = new ParticipantName("duei");
        BettingAmount bettingAmountOfDuei = new BettingAmount(20000);
        Cards cardsOfDuei = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.TWO)));
        Participant duei = new Player(nameOfDuei, bettingAmountOfDuei, cardsOfDuei);

        Participants participants = new Participants(List.of(drago, duei));
        Map<Participant, Integer> expected = Map.of(drago, 22, duei, 20);

        assertThat(participants.getTotalRankSumByPlayer()).isEqualTo(expected);
    }
}
