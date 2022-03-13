package study;

import static org.assertj.core.api.Assertions.assertThat;

import fuel.Car;
import fuel.Sonata;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LambdaTest {

    @DisplayName("내부 클래스는 build/classes에 별도의 클래스를 생성한다. (IntelliJ 내부에서는 숨겨져 있음)")
    @Nested
    class ClassBuildTest {

        @DisplayName("내부 로컬클래스는 별도의 클래스를 생성한다.")
        @Test
        void localInnerClassTest() {
            class InnerCar extends Car {
                protected InnerCar(String name, int fuelEfficiency, int distance) {
                    super(name, fuelEfficiency, distance);
                }
            }

            assertThat(InnerCar.class).isNotNull();
            // System.out.println(InnerCar.class); // class study.LambdaTest$1InnerCar
        }

        @DisplayName("익명클래스는 별도의 클래스를 생성한다.")
        @Test
        void anonymousClassTest() {
            Car anonymousClass = new Car("익명클래스1", 10, 10) {
                /* nothing to implement */
            };

            assertThat(anonymousClass.getClass()).isNotNull();
            // System.out.println(anonymousClass.getClass()); // class study.LambdaTest$1
        }

        @DisplayName("람다는 함수형 인터페이스의 인스턴스와 같지만 별도의 클래스를 선언하지는 않는다. (.class 파일을 생성하지 않음)")
        @Test
        void lambdaTest() {
            Runnable lambda = () -> new Sonata(10);

            assertThat(lambda.getClass()).isNotNull();
            // System.out.println(lambda.getClass()); // class study.LambdaTest$$Lambda$336/0x0000000800174040
        }
    }

    @DisplayName("Function 인터페이스는 디폴트 메서드 andThen과 compose를 통해 복수의 함수를 연결할 수 있다.")
    @Test
    void functionDefaultMethods_andThenVsCompose() {
        Function<Integer, Integer> add = a -> a + 10;
        Function<Integer, Integer> mul = b -> b * 10;
        Function<Integer, Integer> addAndThenMul = add.andThen(mul); // mul(add(x))
        Function<Integer, Integer> mulAndThenAdd = add.compose(mul); // add(mul(x))

        assertThat(addAndThenMul.apply(1)).isEqualTo(110); // (1 + 10) * 10 = 110
        assertThat(mulAndThenAdd.apply(1)).isEqualTo(20); // (1 * 10) + 10 = 20
    }

    @DisplayName("람다 내부에서 외부의 변수를 사용하는 경우, 해당 스코프에서의 재할당만을 컴파일러가 방지한다.")
    @Nested
    class LambdaCapturingTest {

        private int instanceField = 100;
        private int instanceFieldToBeChanged = 0;
        private int instanceFieldToBeChangedByParallel = 0;

        @DisplayName("람다 내부에서 사용되는 지역변수는 기존 지역변수의 복제품이다. 그러므로 해당 변수의 할당이 해제되어도 유지된다.")
        @Test
        void lambdaCapturing() {
            Supplier<Integer> lambda = getLambdaCapturingLocalVariable();

            assertThat(lambda.get()).isEqualTo(1000);
        }

        private Supplier<Integer> getLambdaCapturingLocalVariable() {
            int localVariable = 1000;

            return () -> localVariable;
        }

        @DisplayName("람다 내부에서 사용되는 지역변수는 불변이어야 하며, 재할당하려는 경우 컴파일 에러가 발생한다. (지역변수는 스택에 존재하기 때문)")
        @Test
        void lambdaCapturing_localVarMustBeFinal() {
            int withOutFinal = 1000;
            final int withFinal = 1001;

            Runnable r1 = () -> System.out.println(withOutFinal);
            Runnable r2 = () -> System.out.println(withFinal);

            // 람다 내부에서 재할당 시도시, 컴파일 에러: Variable used in lambda expression should be final or effectively final
            // Runnable r3 = () -> withOutFinal = 1001;

            // 람다 외부에서 재할당 시도시, 컴파일 에러: Variable used in lambda expression should be final or effectively final
            // withOutFinal = 1002;
        }

        @DisplayName("람다 내부에서 사용되는 인스턴스 필드의 값이 재할당되는 경우 컴파일 에러가 발생하지 않으며, side effect를 유발한다.")
        @Test
        void lambdaCapturing_fieldDoesNotHaveToBeFinal() {
            Supplier<Integer> r = () -> this.instanceField;

            assertThat(r.get()).isEqualTo(100);

            this.instanceField = 200; // 컴파일 에러가 발생하지 않으며, 예상치 못한 부작용 유발.
            assertThat(r.get()).isEqualTo(200);
        }

        @DisplayName("람다 내부에서 인스턴스 필드의 값을 직접 재할당할 수 있다. (인스턴스 변수는 쓰레드가 공유하는 힙에 존재하므로 제약이 없음)")
        @Test
        void lambdaCapturing_canChangeFieldValueFromInsideOfLambda() {
            Consumer<Integer> changeFieldValue = (a) -> this.instanceFieldToBeChanged += a;

            IntStream.rangeClosed(1, 100)
                    .forEach(changeFieldValue::accept);
            int expected = IntStream.rangeClosed(1, 100).sum();

            assertThat(this.instanceFieldToBeChanged).isEqualTo(expected);

            this.instanceFieldToBeChanged = -100;
        }

        @DisplayName("병렬 스트림으로 인스턴스 필드의 값을 수정하는 람다를 호출하는 경우, 호출 순서에 의존하는 로직에서는 부작용이 발생한다.")
        @Test
        void lambdaCapturing_parallelStreamCanCauseSideEffects() {
            Consumer<Integer> changeFieldValue = (a) -> this.instanceFieldToBeChangedByParallel = a;

            IntStream.rangeClosed(1, 100)
                    .parallel()
                    .forEach(changeFieldValue::accept);
            int expectedToBeCalledAtFinal = 100;

            assertThat(this.instanceFieldToBeChangedByParallel)
                    .isNotEqualTo(expectedToBeCalledAtFinal);
        }
    }
}
