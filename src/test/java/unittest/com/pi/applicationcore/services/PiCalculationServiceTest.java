package unittest.com.pi.applicationcore.services;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiCalculationService;
import org.junit.Assert;
import org.junit.Test;
import unittest.com.pi.applicationcore.BaseTest;

public class PiCalculationServiceTest extends BaseTest {
    public PiCalculationServiceTest(){
        super();
    }

    @Test
    public void execTestSuccess(){
        PiCalculationService piCalculationService = piFactory.getPiCalculationService();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("123456");
        PiResponseResult piResponseResult = piCalculationService.exec(piRequest);

        Assert.assertEquals( 0.7853961383844696, piResponseResult.getValue(), 16);
    }
}
