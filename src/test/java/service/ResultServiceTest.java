package service;

import static factory.DrawnCardsFactory.createEmptyCards;
import static factory.DrawnCardsFactory.createStayCards;
import static factory.PlayerFactory.createPlayer;
import static factory.PlayerFactory.createPlayerWithEmptyCards;
import static factory.PlayerFactory.createPlayerWithStayCards;
import static factory.StatusFactory.createStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.Dealer;
import domain.DrawnCards;
import domain.Player;
import domain.Players;
import dto.DrawnCardsInfo;
import dto.ParticipantAccountResult;
import dto.ParticipantResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultServiceTest {

    private final ResultService blackJackResultService = new ResultService();

    @Test
    @DisplayName("플레이어와 딜러의 카드 정보를 반환한다.")
    void returns_players_and_dealer_cards_infos() {
        // given
        Dealer dealer = new Dealer(createEmptyCards());
        Player player = createPlayerWithEmptyCards("jay");
        Players players = new Players(List.of(player));

        // when
        List<DrawnCardsInfo> result = blackJackResultService.makeAfterSplitsInfos(players, dealer);

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 뽑은 카드의 정보를 반환해준다.")
    void returns_player_card_infos() {
        // given
        DrawnCards drawnCards = createStayCards();
        Player player = createPlayer(createStatus("choonsik", 1000), drawnCards);

        // when
        DrawnCardsInfo result = blackJackResultService.drawCards(player);

        // then
        assertThat(result.getName()).isEqualTo(player.getName());
    }

    @Test
    @DisplayName("플레이어와 딜러의 카드 정보 및 점수를 반환해준다.")
    void returns_players_and_dealer_cards_and_score() {
        // given
        String givenName = "choonsik";
        Player player = createPlayerWithStayCards(givenName);
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer(createEmptyCards());

        // when
        List<ParticipantResult> result = blackJackResultService.getParticipantsCardsResults(players, dealer);

        // then
        assertSoftly(softly -> {
            softly.assertThat(result.size()).isEqualTo(2);
            softly.assertThat(result.get(0).getScore()).isEqualTo(0);
            softly.assertThat(result.get(1).getName()).isEqualTo(givenName);
        });
    }

    @Test
    @DisplayName("플레이어와 딜러의 잔액을 반환한다.")
    void returns_players_and_dealer_account_infos() {
        // given
        Player player = createPlayerWithStayCards("choonsik");
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer(createEmptyCards());

        // when
        List<ParticipantAccountResult> result = blackJackResultService.getParticipantAccountResults(players, dealer);

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}
