package unittest.com.pi.applicationcore.business;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiBusinessLocal;
import org.junit.Assert;
import org.junit.Test;
import unittest.com.pi.applicationcore.BaseTest;

public class PiBusinessTest extends BaseTest {

    public PiBusinessTest(){
        super();
    }

    @Test
    public void execTest(){
        PiBusinessLocal piBus = piDI.getPiBusiness();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("1234567899");
        PiResponseResult piResponseResult = piBus.exec(piRequest);

        Assert.assertEquals( 0.7853961383844696, piResponseResult.getValue(), 16);
    }
}
