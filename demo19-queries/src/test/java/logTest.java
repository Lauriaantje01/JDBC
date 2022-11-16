import org.apache.logging.log4j.core.Logger;
import org.junit.Test;
import org.junit.platform.commons.logging.LoggerFactory;

public class logTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(logTest.class);

    @Test
    void persistBookWithoutGeneratedValue() {
        logger.info("Start persist book");
        //something I persist
        logger.info("End persist book");
    }
}
