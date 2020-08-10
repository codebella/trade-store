import com.sample.trade.WebApplication;
import com.sample.trade.controller.TradeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebApplication.class)
public class WebApplicationTest {
    @Autowired
    private TradeController controller;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(controller);
    }

}
