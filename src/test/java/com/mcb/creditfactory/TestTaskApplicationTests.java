package com.mcb.creditfactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mcb.creditfactory.controller.AssessController;
import com.mcb.creditfactory.controller.CollateralController;
import com.mcb.creditfactory.controller.CollateralTypeController;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTaskApplicationTests {
    @Autowired
    private AssessController assessController;
    @Autowired
    private CollateralController collateralController;
    @Autowired
    private CollateralTypeController collateralTypeController;

    @Test
    public void contextLoads() {
    }
    
    

}
