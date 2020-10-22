package unittest.com.pi.applicationcore.validation;

import com.pi.applicationcore.dto.PiRequest;
import com.pi.applicationcore.dto.PiResponseResult;
import com.pi.applicationcore.interfaces.PiValidationLocal;
import org.junit.Assert;
import org.junit.Test;
import unittest.com.pi.applicationcore.BaseTest;

public class PiValidationTest extends BaseTest {
    public PiValidationTest(){
        super();
    }

    @Test
    public void validateTestFail(){
        PiValidationLocal piValidationLocal = piDI.getPiValidation();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("123456s");
        PiResponseResult piResponseResult = piValidationLocal.validate(piRequest);

        Assert.assertEquals( "Your input is not correct.", piResponseResult.getError().getMessage());
    }

    @Test
    public void validateTestFailForNegative(){
        PiValidationLocal piValidationLocal = piDI.getPiValidation();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("-10");
        PiResponseResult piResponseResult = piValidationLocal.validate(piRequest);

        Assert.assertEquals( "Your input is not correct.", piResponseResult.getError().getMessage());
    }

    @Test
    public void validateTestSuccess(){
        PiValidationLocal piValidationLocal = piDI.getPiValidation();

        PiRequest piRequest = new PiRequest();
        piRequest.setRawNumber("123456");
        PiResponseResult piResponseResult = piValidationLocal.validate(piRequest);

        Assert.assertEquals( false, piResponseResult.hasError());
    }

}
