package si.test.backend.coding.challenge.service.impl;

import si.test.backend.coding.challenge.service.impl.FileManager;

import java.util.FormatFlagsConversionMismatchException;
import java.util.concurrent.atomic.AtomicLong;

public class RequestCounterService {
    private static final AtomicLong addInt;

    static {
        addInt = FileManager.readLastCounter();
    }

    public static long get() {
        return addInt.get();
    }

    public static long add() {
        long value = addInt.incrementAndGet();
        FileManager.writeRequestCounterToFile(value);
        return value;
    }

}
