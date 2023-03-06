package domain;

import static domain.CardInfo.A;
import static domain.CardInfo.TEN;
import static domain.Shape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/06
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    Player 우가 = new Player(new Name("우가"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.A), new Card(Shape.HEART, CardInfo.TWO))));
    Player 빙봉 = new Player(new Name("빙봉"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.A), new Card(Shape.HEART, CardInfo.TEN))));

    @Test
    void 참가자들의_이름들을_반환한다() {
        Dealer dealer = readDealer();
        Players players = readPlayers();
        Participants participants = new Participants(dealer, players);

        assertThat(participants.getNames()).containsExactly("딜러", "우가", "빙봉");
    }

    @Test
    void 참가자들이_가진_카드뭉치를_반환한다() {
        Dealer dealer = readDealer();
        Players players = readPlayers();
        Participants participants = new Participants(dealer, players);

        assertThat(participants.getCards())
                .extracting(Cards::cardsToString)
                .containsExactly(List.of("A하트", "10하트"), List.of("A하트", "2하트"), List.of("A하트", "10하트"));
    }

    @Test
    void 참가자들중_플레이어를_반환한다() {
        Dealer dealer = readDealer();
        Players players = readPlayers();
        Participants participants = new Participants(dealer, players);

        assertThat(participants.getPlayers()).contains(우가, 빙봉);
    }

    @Test
    void 참가자들중_딜러를_반환한다() {
        Dealer dealer = readDealer();
        Players players = readPlayers();
        Participants participants = new Participants(dealer, players);

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    void 참가자들끼리의_우승결과를_반환한다() {
        Dealer dealer = readDealer();
        Participants participants = new Participants(dealer, new Players(List.of(우가, 빙봉)));

        assertThat(participants.getWinningResult()).containsExactly(1, 0);
    }

    Dealer readDealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(HEART, A));
        cards.add(new Card(HEART, TEN));

        return new Dealer(new CardBox(), new Cards(cards));
    }

    Players readPlayers() {
        return new Players(List.of(우가, 빙봉));
    }
}
