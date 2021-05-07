package com.dolap.product.entity.type;

import java.util.Arrays;

public enum ProductStatus {

    REJECTED {
        @Override
        public int getValue() {
            return -1;
        }
    },
    WAITING {
        @Override
        public int getValue() {
            return 0;
        }
    },
    APPROVED {
        @Override
        public int getValue() {
            return 1;
        }
    },
    CLAIMED {
        @Override
        public int getValue() {
            return 2;
        }
    },
    SOLD {
        @Override
        public int getValue() {
            return 3;
        }
    };

    public static ProductStatus findByValue(int value) {
        return Arrays.stream(ProductStatus.values())
                .filter(productStatus -> productStatus.getValue() == value)
                .findFirst()
                .orElseThrow();
    }

    public abstract int getValue();
}
