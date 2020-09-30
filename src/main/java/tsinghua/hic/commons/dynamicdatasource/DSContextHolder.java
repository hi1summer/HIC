package tsinghua.hic.commons.dynamicdatasource;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bytebuddy.asm.Advice.This;

public class DSContextHolder {
    private static final ThreadLocal<DSNames> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(0);

    private static final Logger log = LoggerFactory.getLogger(This.class);

    public static void set(DSNames dsType) {
        contextHolder.set(dsType);
    }

    public static DSNames get() {
        return contextHolder.get();
    }

    public static void master() {
        log.info("master");
        set(DSNames.MASTER);
    }

    public static void slave() {
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(0);
        }
        if (index == 0) {
            log.info("slave master");
            set(DSNames.MASTER);
        } else {
            log.info("slave slave1");
            set(DSNames.SLAVE1);
        }
    }
}
