package price;

import java.math.BigDecimal;

public enum LoadCoefficient {
    NORMAL(BigDecimal.ONE),
    INCREASED(new BigDecimal(1.2)),
    HIGH(new BigDecimal(1.4)),
    EXTREMELY(new BigDecimal(1.6));

    LoadCoefficient(BigDecimal loadCoefficient) {
        this.loadCoefficient = loadCoefficient;
    }

    public BigDecimal loadCoefficient;

    public BigDecimal getLoadCoefficient() {
        return loadCoefficient;
    }
}
