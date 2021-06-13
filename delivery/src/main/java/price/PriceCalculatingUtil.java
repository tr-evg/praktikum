package price;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL64;

public class PriceCalculatingUtil {

    public static BigDecimal getTotalPrice(BigDecimal distance, boolean isBig, boolean isFragile, LoadCoefficient load) {
        BigDecimal totalPrice = getPriceBasedOnDistance(distance)
                .add(getPriceBasedOnSize(isBig))
                .add(getPriceBasedOnFragile(isFragile, distance));

        totalPrice = getPriceBasedOnLoad(totalPrice, load);
        return totalPrice.compareTo(new BigDecimal(400)) < 0 ? new BigDecimal(400) : totalPrice;
    }

    public static BigDecimal getPriceBasedOnSize(boolean isBig) {
        return isBig ? new BigDecimal(200) : new BigDecimal(100);
    }

    public static BigDecimal getPriceBasedOnFragile(boolean isFragile, BigDecimal distance) {
        if (distance == null) {
            throw new IllegalArgumentException("Distance can't be null");
        }

        if (distance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Distance can't be <= 0");
        }

        if (isFragile && distance.compareTo(new BigDecimal(30)) > 0) {
            throw new IllegalArgumentException("it isn't possible to deliver fragile cargo to distance more than 30 km");
        }

        return isFragile ? new BigDecimal(300) : BigDecimal.ZERO;
    }

    public static BigDecimal getPriceBasedOnDistance(BigDecimal distance) {
        if (distance == null) {
            throw new IllegalArgumentException("Distance can't be null");
        }

        if (distance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Distance can't be <= 0");
        }

        if (distance.compareTo(new BigDecimal(2)) < 0) {
            return new BigDecimal(50);
        }

        if (distance.compareTo(new BigDecimal(10)) < 0) {
            return new BigDecimal(100);
        }

        if (distance.compareTo(new BigDecimal(30)) < 0) {
            return new BigDecimal(200);
        }

        return new BigDecimal(300);
    }

    public static BigDecimal getPriceBasedOnLoad(BigDecimal price, LoadCoefficient load) {
        if (price == null) {
            throw new IllegalArgumentException("Price can't be null");
        }

        if (load == null) {
            throw new IllegalArgumentException("Load can't be null");
        }

        return price.multiply(load.getLoadCoefficient(), DECIMAL64);
    }

}
