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

@DisplayName("플레이어 리스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayersTest {

    @Test
    void 플레이어의_총인원이_4명이상이면_예외가_발생한다() {
        BettingAmount bettingAmount = new BettingAmount(10000);
        Cards cardsOfPlayer = new Cards(List.of(new Card(Suit.SPADE, Rank.FOUR)));
        Player duei = new Player(new ParticipantName("duei"), bettingAmount, cardsOfPlayer);
        Player drago = new Player(new ParticipantName("drago"), bettingAmount, cardsOfPlayer);
        Player brown = new Player(new ParticipantName("brown"), bettingAmount, cardsOfPlayer);
        Player neo = new Player(new ParticipantName("neo"), bettingAmount, cardsOfPlayer);
        Player parang = new Player(new ParticipantName("parang"), bettingAmount, cardsOfPlayer);

        assertThatThrownBy(() -> new Players(List.of(duei, drago, brown, neo, parang)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어는 총 4명까지만 참여 가능합니다.");
    }

    @Test
    void 플레이어별_카드리스트의_총합을_반환한다() {
        ParticipantName nameOfDrago = new ParticipantName("drago");
        BettingAmount bettingAmountOfDrago = new BettingAmount(10000);
        Cards cardsOfDrago = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Player drago = new Player(nameOfDrago, bettingAmountOfDrago, cardsOfDrago);

        ParticipantName nameOfDuei = new ParticipantName("duei");
        BettingAmount bettingAmountOfDuei = new BettingAmount(20000);
        Cards cardsOfDuei = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.TWO)));
        Player duei = new Player(nameOfDuei, bettingAmountOfDuei, cardsOfDuei);

        Players players = new Players(List.of(drago, duei));
        Map<Player, Integer> expected = Map.of(drago, 22, duei, 20);

        assertThat(players.getTotalRankSumByPlayer()).isEqualTo(expected);
    }
}
