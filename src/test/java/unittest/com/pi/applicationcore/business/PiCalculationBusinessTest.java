package unittest.com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiCalculationBusinessLocal;
import org.junit.Assert;
import org.junit.Test;
import unittest.com.pi.applicationcore.BaseTest;

import java.util.concurrent.ExecutionException;

public class PiCalculationBusinessTest extends BaseTest {

    public PiCalculationBusinessTest(){
        super();
    }

    @Test
    public void execTest() throws ExecutionException, InterruptedException {
        PiCalculationBusinessLocal piCalculationBusinessLocal = piDI.getPiBusiness();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("0");
        PiResponseResult piResponseResult = piCalculationBusinessLocal.executeCalculate(piRequest);

        double res = piResponseResult.getValue();
        Assert.assertEquals( 1.0, piResponseResult.getValue(), 0.000000000001);

        piRequest.setRawNumber("1000");
        piResponseResult = piCalculationBusinessLocal.executeCalculate(piRequest);
        res = piResponseResult.getValue();
        Assert.assertEquals( 0.7856479135848861, piResponseResult.getValue(), 0.000000000001);

        piRequest.setRawNumber("123456789");
        piResponseResult = piCalculationBusinessLocal.executeCalculate(piRequest);
        res = piResponseResult.getValue();
        Assert.assertEquals( 0.7853981613723504, piResponseResult.getValue(), 0.000000000001);
    }
}
