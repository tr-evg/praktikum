package price;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceBasedOnDistanceTest {

    @ParameterizedTest(name = "Check price increasing based on distance with correct parameters: {0} km")
    @MethodSource("getCorrectDistances")
    void testPricePositive(BigDecimal distance, BigDecimal expectedPrice) {
        assertThat(PriceCalculatingUtil.getPriceBasedOnDistance(distance))
                .isEqualByComparingTo(expectedPrice);
    }

    private static Stream<Arguments> getCorrectDistances() {
        return Stream.of(
                Arguments.of(new BigDecimal(1), new BigDecimal(50)),
                Arguments.of(new BigDecimal(2), new BigDecimal(100)),
                Arguments.of(new BigDecimal(3), new BigDecimal(100)),
                Arguments.of(new BigDecimal(10), new BigDecimal(200)),
                Arguments.of(new BigDecimal(15), new BigDecimal(200)),
                Arguments.of(new BigDecimal(30), new BigDecimal(300)),
                Arguments.of(new BigDecimal(31), new BigDecimal(300))
        );
    }

    private static Stream<BigDecimal> getIncorrectDistances() {
        return Stream.of(new BigDecimal(0), new BigDecimal(-1), null);
    }

    @ParameterizedTest(name = "Check price increasing based on distance with incorrect parameters: {0} km")
    @MethodSource("getIncorrectDistances")
    void testPriceNegative(BigDecimal distance) {
        assertThatThrownBy(() -> {
            PriceCalculatingUtil.getPriceBasedOnDistance(distance);
        }).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}
