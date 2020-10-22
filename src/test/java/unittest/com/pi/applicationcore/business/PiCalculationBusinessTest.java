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
        piRequest.setRawNumber("1234567899");
        PiResponseResult piResponseResult = piCalculationBusinessLocal.executeCalculate(piRequest);

        Assert.assertEquals( 0.7853961383844696, piResponseResult.getValue(), 16);
    }
}
