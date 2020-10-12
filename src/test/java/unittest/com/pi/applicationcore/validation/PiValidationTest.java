package unittest.com.pi.applicationcore.validation;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiValidation;
import org.junit.Assert;
import org.junit.Test;
import unittest.com.pi.applicationcore.BaseTest;

public class PiValidationTest extends BaseTest {
    public PiValidationTest(){
        super();
    }

    @Test
    public void validateTestFail(){
        PiValidation piValidation = piDI.getPiValidation();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("123456s");
        PiResponseResult piResponseResult = piValidation.validate(piRequest);

        Assert.assertEquals( "Your input is not correct.", piResponseResult.getError().getMessage());
    }

    @Test
    public void validateTestSuccess(){
        PiValidation piValidation = piDI.getPiValidation();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("123456");
        PiResponseResult piResponseResult = piValidation.validate(piRequest);

        Assert.assertEquals( null, piResponseResult.getError());
    }

}
