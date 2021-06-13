package price;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceBasedOnFragileTest {

    private static Stream<Arguments> getFragilePositive() {
        return Stream.of(
                Arguments.of(true, new BigDecimal(10), new BigDecimal(300)),
                Arguments.of(false, new BigDecimal(10), BigDecimal.ZERO)
        );
    }

    @ParameterizedTest(name = "Check price increasing based on fragile with correct params: isFragile = {0}, distance = {1}")
    @MethodSource("getFragilePositive")
    void testPricePositive(boolean isFragile, BigDecimal distance, BigDecimal expectedPrice) {
        assertThat(PriceCalculatingUtil.getPriceBasedOnFragile(isFragile, distance))
                .isEqualByComparingTo(expectedPrice);
    }

    private static Stream<Arguments> getFragile() {
        return Stream.of(
                Arguments.of(true, new BigDecimal(31)),
                Arguments.of(true, new BigDecimal(0)),
                Arguments.of(false, new BigDecimal(0)),
                Arguments.of(true, new BigDecimal(-1)),
                Arguments.of(false, new BigDecimal(-1)),
                Arguments.of(true, null),
                Arguments.of(false, null)
        );
    }

    @ParameterizedTest(name = "Check price increasing based on fragile cargo and incorrect distance: isFragile = {0}, distance = {1}")
    @MethodSource("getFragile")
    void testFragileLargeDistance(boolean isFragile, BigDecimal distance) {
        assertThatThrownBy(() -> {
            PriceCalculatingUtil.getPriceBasedOnFragile(isFragile, distance);
        }).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}
