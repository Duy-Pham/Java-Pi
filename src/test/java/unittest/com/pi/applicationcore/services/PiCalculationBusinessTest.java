package unittest.com.pi.applicationcore.services;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiCalculationBusiness;
import org.junit.Assert;
import org.junit.Test;
import unittest.com.pi.applicationcore.BaseTest;

public class PiCalculationBusinessTest extends BaseTest {
    public PiCalculationBusinessTest(){
        super();
    }

    @Test
    public void execTestSuccess(){
        PiCalculationBusiness piCalculationBusiness = piDI.getPiCalculationService();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("123456");
        PiResponseResult piResponseResult = piCalculationBusiness.exec(piRequest);

        Assert.assertEquals( 0.7853961383844696, piResponseResult.getValue(), 16);
    }
}
