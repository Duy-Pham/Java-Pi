package unittest.com.pi.applicationcore;

import com.pi.di.PiDI;

//@Test
public class BaseTest {
    protected PiDI piDI;

    public BaseTest(){
        piDI = new PiDI();
    }
}
