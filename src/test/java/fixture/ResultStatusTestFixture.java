package fixture;

import java.util.stream.Stream;
import model.judgement.ResultStatus;
import org.junit.jupiter.params.provider.Arguments;

public class ResultStatusTestFixture {

    public static Stream<Arguments> 천_원_베팅에_따른_게임_결과별_수익_정보_제공() {
        return Stream.of(
                Arguments.of(ResultStatus.WIN, 1000),       // 수익 금액 그대로
                Arguments.of(ResultStatus.LOSE, -1000),     // 수익 금액 반대 - 손실
                Arguments.of(ResultStatus.DRAW, 0)          // 무승부 시 수익 없음
        );
    }

}
