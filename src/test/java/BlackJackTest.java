import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.BlackJack;
import model.Card;
import model.participant.Dealer;
import model.participant.Participant;
import model.Participants;
import model.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {
    private static final String DEALER_NAME = "딜러";

    BlackJack blackJack;
    Participants participants;

    @BeforeEach
    void setUp() {
        List<Participant> rawParticipants = new ArrayList<>();

        rawParticipants.add(Dealer.of("딜러"));
        rawParticipants.add(Player.of("pobi", 10000));
        rawParticipants.add(Player.of("jason", 20000));
        participants = Participants.of(rawParticipants);

        Participant dealer = participants.getDealer();
        dealer.draw(Card.of("스페이드", 1));
        dealer.draw(Card.of("스페이드", 3));

        Participant player1 = participants.getPlayers().get(0);
        player1.draw(Card.of("하트", 1));
        player1.draw(Card.of("하트", 2));
        Participant player2 = participants.getPlayers().get(1);
        player2.draw(Card.of("하트", 5));
        player2.draw(Card.of("하트", 6));

        blackJack = BlackJack.from(participants);
    }

    @Test
    void 전체_참여자들의_승패를_계산한다() {
        // when
        Map<String, Integer> dealerResult = blackJack.calculateDealerResult();
        Map<String, Boolean> playerResult = blackJack.calculatePlayerResult();

        assertThat(dealerResult.get("승")).isEqualTo(1);
        assertThat(dealerResult.get("패")).isEqualTo(1);

        assertThat(playerResult.get("pobi")).isEqualTo(Boolean.FALSE);
        assertThat(playerResult.get("jason")).isEqualTo(Boolean.TRUE);
    }

    @Test
    void 딜러와_참가자들의_최종_수익을_계산한다() {
        // given
        // when
        Map<String, Integer> calculatedRevenue = blackJack.calculateRevenue();

        // then
        assertThat(calculatedRevenue.get("딜러")).isEqualTo(-10000);
        assertThat(calculatedRevenue.get("pobi")).isEqualTo(-10000);
        assertThat(calculatedRevenue.get("jason")).isEqualTo(20000);
    }

    @Test
    void 딜러와_플레이어가_동시에_블랙잭이면_플레이어는_베팅한_금액을_돌려받는다() {
        // given
        Dealer dealer = Dealer.of(DEALER_NAME);
        dealer.draw(Card.of("스페이드", 10));
        dealer.draw(Card.of("스페이드", 11));
        Player player = Player.of("nuno", 10000);
        player.draw(Card.of("하트", 10));
        player.draw(Card.of("스페이드", 11));

        ArrayList<Participant> playersAndDealer = new ArrayList<>();
        playersAndDealer.add(dealer);
        playersAndDealer.add(player);

        Participants playerAndDealerParticipants = Participants.of(playersAndDealer);
        BlackJack blackJackGame = BlackJack.from(playerAndDealerParticipants);

        // when
        Map<String, Integer> calculateRevenue = blackJackGame.calculateRevenue();

        // then
        assertThat(calculateRevenue.get("nuno")).isEqualTo(0);
    }

    @DisplayName("플레이어가_블랙잭이면서_딜러는_블랙잭이_아니라면_플레이어는_배팅금액의_1.5배를 받는다.")
    @Test
    void onlyPlayerBlackJack() {
        // given
        Dealer dealer = Dealer.of(DEALER_NAME);
        dealer.draw(Card.of("스페이드", 5));
        dealer.draw(Card.of("스페이드", 3));
        Player player = Player.of("nuno", 10000);
        player.draw(Card.of("하트", 10));
        player.draw(Card.of("스페이드", 11));

        ArrayList<Participant> playersAndDealer = new ArrayList<>();
        playersAndDealer.add(dealer);
        playersAndDealer.add(player);

        Participants playerAndDealerParticipants = Participants.of(playersAndDealer);
        BlackJack blackJackGame = BlackJack.from(playerAndDealerParticipants);

        // when
        Map<String, Integer> calculateRevenue = blackJackGame.calculateRevenue();

        // then
        assertThat(calculateRevenue.get("nuno")).isEqualTo(15000);
    }

    @Test
    void 딜러가_버스트면_남아있는_플레이어들은_플레이어의_승패에_상관없이_베팅금액을_받는다() {
        // given
        Dealer dealer = Dealer.of(DEALER_NAME);
        dealer.draw(Card.of("스페이드", 10));
        dealer.draw(Card.of("스페이드", 10));
        dealer.draw(Card.of("스페이드", 10));
        Player player = Player.of("nuno", 10000);
        player.draw(Card.of("하트", 2));
        player.draw(Card.of("스페이드", 3));

        ArrayList<Participant> playersAndDealer = new ArrayList<>();
        playersAndDealer.add(dealer);
        playersAndDealer.add(player);

        Participants playerAndDealerParticipants = Participants.of(playersAndDealer);
        BlackJack blackJackGame = BlackJack.from(playerAndDealerParticipants);

        // when
        Map<String, Integer> calculateRevenue = blackJackGame.calculateRevenue();

        // then
        assertThat(calculateRevenue.get("nuno")).isEqualTo(10000);
        assertThat(calculateRevenue.get(DEALER_NAME)).isEqualTo(-10000);
    }
}
