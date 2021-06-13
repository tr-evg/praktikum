package price;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTotalTest {

    private static Stream<Arguments> getSize() {
        return Stream.of(
                Arguments.of(new BigDecimal(1), false, false, LoadCoefficient.NORMAL, new BigDecimal(400)),
                Arguments.of(new BigDecimal(100), true, false, LoadCoefficient.INCREASED, new BigDecimal(600))
        );
    }

    @ParameterizedTest(name = "Check price increasing based on size: isBig = {0}")
    @MethodSource("getSize")
    void testPrice(BigDecimal distance, boolean isBig, boolean isFragile, LoadCoefficient load, BigDecimal expectedPrice) {
        assertThat(PriceCalculatingUtil.getTotalPrice(distance, isBig, isFragile, load))
                .isEqualByComparingTo(expectedPrice);
    }

}
