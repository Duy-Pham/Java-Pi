//package unittest.com.pi.applicationcore.services;
//
//import com.pi.applicationcore.dto.PiRequest;
//import com.pi.applicationcore.dto.PiResponseResult;
//import org.junit.Assert;
//import org.junit.Test;
//import unittest.com.pi.applicationcore.BaseTest;
//
//public class PiCalculationBusinessTest extends BaseTest {
//    public PiCalculationBusinessTest(){
//        super();
//    }
//
//    @Test
//    public void execTestSuccess(){
//        PiCalculationBusinessLocal piCalculationBusinessLocal = piDI.getPiCalculationService();
//
//        PiRequest piRequest = new PiRequest();
//        piRequest.setValue(123456);
//        PiResponseResult piResponseResult = piCalculationBusinessLocal.exec(piRequest);
//
//        Assert.assertEquals( 0.7853961383844696, piResponseResult.getValue(), 16);
//    }
//}
