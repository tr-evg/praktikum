package price;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceBasedOnLoadTest {

    private static Stream<Arguments> getLoadCoefficient() {
        return Stream.of(
                Arguments.of(LoadCoefficient.NORMAL, new BigDecimal(10), new BigDecimal(10)),
                Arguments.of(LoadCoefficient.INCREASED, new BigDecimal(10), new BigDecimal(12)),
                Arguments.of(LoadCoefficient.HIGH, new BigDecimal(10), new BigDecimal(14)),
                Arguments.of(LoadCoefficient.EXTREMELY, new BigDecimal(10), new BigDecimal(16))
        );
    }

    @ParameterizedTest(name = "Check price increasing based on load: load = {0}, price without load coefficient = {1}")
    @MethodSource("getLoadCoefficient")
    void testFragileLargeDistance(LoadCoefficient loadCoefficient, BigDecimal priceWithoutLoad, BigDecimal expectedPriceWithLoad) {
        assertThat(PriceCalculatingUtil.getPriceBasedOnLoad(priceWithoutLoad, loadCoefficient))
                .isEqualByComparingTo(expectedPriceWithLoad);
    }

}
