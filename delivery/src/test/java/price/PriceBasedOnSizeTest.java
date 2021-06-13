package price;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceBasedOnSizeTest {

    private static Stream<Arguments> getSize() {
        return Stream.of(
                Arguments.of(true, new BigDecimal(200)),
                Arguments.of(false, new BigDecimal(100))
        );
    }

    @ParameterizedTest(name = "Check price increasing based on size: isBig = {0}")
    @MethodSource("getSize")
    void testPrice(boolean isBig, BigDecimal expectedPrice) {
        assertThat(PriceCalculatingUtil.getPriceBasedOnSize(isBig))
                .isEqualByComparingTo(expectedPrice);
    }

}
