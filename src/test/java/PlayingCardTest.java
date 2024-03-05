import domain.card.PlayingCard;
import domain.card.PlayingCardShape;
import domain.card.PlayingCardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.card.PlayingCardShape.HEART;
import static domain.card.PlayingCardValue.FIVE;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayingCardTest {
    
    @DisplayName("카드의 모양과 숫자를 입력하면 인스턴스가 생성된다.")  
    @Test        
    void createPlayingCardTest() {
        // Given
        PlayingCardShape playingCardShape = HEART;
        PlayingCardValue playingCardValue = FIVE;
                
        // When
        PlayingCard playingCard = new PlayingCard(playingCardShape, playingCardValue);
        
        // Then
        assertThat(playingCard).isNotNull();
    }
}
