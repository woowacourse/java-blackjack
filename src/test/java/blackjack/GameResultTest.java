package blackjack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class GameResultTest {

    @ParameterizedTest
    @EnumSource(GameResult.class)
    void 게임결과는_승_무_패_만있다(
            GameResult gameResult
    ){
        assertTrue( gameResult ==GameResult.WIN
                || gameResult ==GameResult.TIE
                || gameResult ==GameResult.LOSE

        );
    }


}
