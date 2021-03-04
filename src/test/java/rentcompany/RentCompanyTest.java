package rentcompany;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RentCompanyTest {
    
    private static final String NEWLINE = System.getProperty("line.separator");
    
    @ParameterizedTest(name = "주어진 차량 여행거리를 통해 연비를 계산, 보고서 생성")
    @MethodSource("generateReport_testcase")
    void generateReport(List<Car> cars, String expectedReport) {
        RentCompany company = RentCompany.create();
        for (Car car : cars) {
            company.addCar(car);
        }
        
        String report = company.generateReport();
        assertThat(report).isEqualTo(expectedReport);
    }
    
    private static Stream<Arguments> generateReport_testcase() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Sonata(150), new K5(260), new Sonata(120),
                                new Avante(300), new K5(390)
                        ), "Sonata : 15리터" + NEWLINE + "K5 : 20리터" + NEWLINE + "Sonata : 12리터" + NEWLINE + "Avante : 20리터" + NEWLINE + "K5 : 30리터" + NEWLINE),
                
                Arguments.of(
                        Arrays.asList(
                                new Sonata(100), new K5(130), new Avante(150)
                        ), "Sonata : 10리터" + NEWLINE + "K5 : 10리터" + NEWLINE + "Avante : 10리터" + NEWLINE));
    }
}
