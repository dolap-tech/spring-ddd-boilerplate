package com.dolap.product.base.unit;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public abstract class BaseMockitoTest {

    private final Mockito2MocksCollector mockito2MocksCollector = new Mockito2MocksCollector();

    @AfterEach
    void after() {
        Object[] allMocks = mockito2MocksCollector.getAllMocks();
        if (ArrayUtils.isNotEmpty(allMocks)) {
            Stream.of(allMocks).forEach(Mockito::verifyNoMoreInteractions);
        }
        mockito2MocksCollector.close();
    }
}
